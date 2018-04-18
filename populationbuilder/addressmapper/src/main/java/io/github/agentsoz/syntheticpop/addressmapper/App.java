package io.github.agentsoz.syntheticpop.addressmapper;

import io.github.agentsoz.syntheticpop.filemanager.FileUtils;
import io.github.agentsoz.syntheticpop.filemanager.zip.Zip;
import io.github.agentsoz.syntheticpop.geo.FeatureProcessing;
import io.github.agentsoz.syntheticpop.geo.ShapefileGeoFeatureReader;
import io.github.agentsoz.syntheticpop.geo.ShapefileGeoFeatureWriter;
import io.github.agentsoz.syntheticpop.util.ConfigProperties;
import io.github.agentsoz.syntheticpop.util.ConsoleProgressBar;
import io.github.agentsoz.syntheticpop.util.GlobalConstants;
import io.github.agentsoz.syntheticpop.util.Log;
import org.geotools.data.DataUtilities;
import org.geotools.data.FeatureSource;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.filter.text.cql2.CQLException;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

/**
 * @author wniroshan 16 Apr 2018
 */
public class App {
    private static Path addressFilePath = null, saFilePath = null, tempOutputDir = null, outputFile = null;
    private static String addressLookupKey = null, saLookupKey = null, saFilterKey = null, addressShapeFilePattern = null, saShapeFileName = null;

    private static Map<String, String> newAttributesToAddress = null;
    private static String[] saFilterValues = null;

    private static FeatureProcessing featProcessor;
    private static ShapefileGeoFeatureReader shapesReader;

    private static void usage() {
        System.out.println("Usage: java -jar addressmapper.jar <properties file>");
        System.out.println(
                "This program maps mesh blocks and the corresponding SA1s in shape files obtained from Australian Bureau of Statistics to the addresses obtained from Vicmaps\n");
        System.exit(0);
    }

    public static void main(String args[]) {
        Log.createLogger("AddressMapper", "AddressMapper.log");

        /* Read all the properties */
        if (args.length > 0) {
            ConfigProperties props = null;
            try {
                props = new ConfigProperties(args[0]);
            } catch (IOException e) {
                Log.error("When reading properties file", e);
                usage();
            }
            addressFilePath = props.readFileOrDirectoryPath("AddressesShapeFileZip");
            addressShapeFilePattern = props.getProperty("AddressesShapeFileNamePattern");
            saFilePath = props.readFileOrDirectoryPath("SAShapeFileZip");
            saShapeFileName = props.getProperty("SAShapeFileName");

            addressLookupKey = props.getProperty("AddressLookupKey");
            saLookupKey = props.getProperty("SALookupKey");
            newAttributesToAddress = props.readKeyValuePairs("NewAttributesToAddress");
            tempOutputDir = props.getProperty("TemporaryOutputDirectory").trim().equals("system")
                            ? Paths.get(System.getProperty("java.io.tmpdir"))
                            : props.readFileOrDirectoryPath("TemporaryOutputDirectory");
            saFilterKey = props.getProperty("SAFilterPropertyName").trim();
            saFilterValues = props.readCommaSepProperties("SAFilterPropertyValues");
            outputFile = props.readFileOrDirectoryPath("OutputFile");

        } else {
            usage();
        }

        shapesReader = new ShapefileGeoFeatureReader();
        featProcessor = new FeatureProcessing();

        Matcher m = new Matcher(featProcessor, saLookupKey, addressLookupKey);
        SimpleFeatureCollection newFeatureCollection = new DefaultFeatureCollection();

        List<Path> addressShapeFiles = null;
        SimpleFeatureCollection meshBlocks = null;
        ;
        try {

            Log.info("Locating " + addressShapeFilePattern + " in " + addressFilePath);
            addressShapeFiles = Zip.findFiles(addressFilePath, addressShapeFilePattern);
            Log.info("Located " + addressShapeFiles.size() + " in " + addressFilePath);
        } catch (IOException ex) {
            Log.errorAndExit("Unable to read " + saShapeFileName + " file", ex, GlobalConstants.ExitCode.USERINPUT);
        }

        try {
            meshBlocks = loadSAMeshBlocks();
        } catch (IOException ex) {
            Log.errorAndExit("Unable to read " + saShapeFileName + " file", ex, GlobalConstants.ExitCode.USERINPUT);
        }
        try {

            assert addressShapeFiles != null;
            for (Path addressShape : addressShapeFiles) {
                Log.info("Loading address features in " + addressShape);
                SimpleFeatureCollection addresses = loadAddresses(addressShape);
                int totalAddresses = addresses.size(), processed = 0;

                SimpleFeatureIterator addressFeatItr = addresses.features();

                Log.info("Matching address " + addressLookupKey + " IDs to SA mesh block " + saLookupKey + " IDs");
                while (addressFeatItr.hasNext()) {
                    SimpleFeature addressFeat = addressFeatItr.next(); //Address feature

                    assert meshBlocks != null;
                    SimpleFeature mb = m.findSAMeshBlock(addressFeat, meshBlocks);
                    if (mb != null) {
                        updateAddress(addressFeat, mb, newFeatureCollection);
                    }

                    processed++;
                    System.out.print("Processed "+processed+" / "+totalAddresses+"              \r");
                }
                m.printStats();
            }

            saveToFile(newFeatureCollection, tempOutputDir, outputFile);

        } catch (IOException e) {
            Log.error("Mesh block area loading failed", e);
        } catch (DataFormatException | CQLException e) {
            e.printStackTrace();
        }
    }

    private static void saveToFile(SimpleFeatureCollection featCollection, Path tempFileLoc, Path outputLoc) {
        // Save the updated addresses in zipped shapefile
        Path outFile = null;
        try {
            outFile = new ShapefileGeoFeatureWriter().writeFeatures(DataUtilities.simple(featCollection),
                                                                    tempFileLoc.toAbsolutePath());
        } catch (IOException e) {
            Log.error("When writing updated addresses shape file", e);
            e.printStackTrace();
        }
        String outFileName = outFile.getFileName().toString();
        List<Path> filesToZip = FileUtils.find(tempFileLoc.toAbsolutePath(), outFileName.split("\\.")[0] + ".*");
        try {
            Zip.create(outputLoc.toAbsolutePath(), filesToZip);
            Log.info("Completed addresses zip saved to: " + outFile.toString());
        } catch (Throwable e) {
            Log.error("When creating " + outFile.toString() + " file", e);
            e.printStackTrace();
        }
        System.out.println();
    }

    private static SimpleFeatureCollection loadSAMeshBlocks() throws IOException {
        Log.info("Obtaining the Feature Source for " + saShapeFileName + " in " + saFilePath);
        FeatureSource<SimpleFeatureType, SimpleFeature> ftSource = shapesReader.getFeatureSource(saFilePath, saShapeFileName);

        Log.debug("Loading SA mesh block features where " + saFilterKey + " is " + saFilterValues);
        return (SimpleFeatureCollection) shapesReader.loadFeaturesByProperty(ftSource, saFilterKey, saFilterValues);
    }

    private static SimpleFeatureCollection loadAddresses(Path addressShape) throws IOException {
        FeatureSource<SimpleFeatureType, SimpleFeature> addressFeatSrc = shapesReader.getFeatureSource(addressShape);
        return (SimpleFeatureCollection) shapesReader.loadFeatures(addressFeatSrc);

    }

    /**
     * Updates a copy of the address with new information and add it to a new collection
     *
     * @param address              The address feature
     * @param saMeshBlock          The matching mesh block feature
     * @param newAddressCollection The feature collection to which address is added
     * @throws IOException If processing failed
     */
    private static void updateAddress(SimpleFeature address,
                                      SimpleFeature saMeshBlock,
                                      SimpleFeatureCollection newAddressCollection) throws IOException {
        SimpleFeature copyAddressFeat = featProcessor.addNewAttributeType(address,
                                                                          newAttributesToAddress.keySet(),
                                                                          String.class);
        for (Map.Entry<String, String> entry : newAttributesToAddress.entrySet()) {
            copyAddressFeat.setAttribute(entry.getKey(), saMeshBlock.getAttribute(entry.getValue()));
        }
        ((DefaultFeatureCollection) newAddressCollection).add(copyAddressFeat);
    }

    //    /**
    //     * Filters and loads SA1 geo feature polygons from a shape file based on a property and an array of values that the property
    // can have.
    //     * It also saves the selected SA1 features in a zip file.
    //     *
    //     * @param sa1Path                      The path of the SA1 shape file
    //     * @param sa1FilterProp                The name of the property
    //     * @param sa1FilterVals                The array of values that the sa1FilterProp can take
    //     * @param crs                          Coordinate reference system of the shape file
    //     * @param dirToSaveSelectedSA1Features The location to save selected SA1 geo feature polygons
    //     * @return a list of SA1 geo features polygons
    //     * @throws IOException        When reading SA1 geo features from the SA1 shape file
    //     * @throws URISyntaxException When creating a zip file with selected SA1 geo features
    //     */
    //    private static SimpleFeatureCollection getSA1Collection(Path sa1Path,
    //                                                            String sa1FilterProp,
    //                                                            String[] sa1FilterVals,
    //                                                            CoordinateReferenceSystem crs,
    //                                                            String dirToSaveSelectedSA1Features) throws IOException,
    // URISyntaxException {
    //        SimpleFeatureCollection sa1Collection = null;
    //        ShapefileGeoFeatureReader geoFeatReader = new ShapefileGeoFeatureReader();
    //
    //        geoFeatReader.loadFeatures(sa1Path, sa1FilterProp, sa1FilterVals, crs);
    //        sa1Collection = DataUtilities.simple(geoFeatReader.getFeatures());
    //        new ShapefileGeoFeatureWriter().writeFeatures(sa1Collection, tempOutputDir.toAbsolutePath());
    //        String featureName = ((SimpleFeatureCollection) sa1Collection).getSchema().getName().toString();
    //        List<Path> filesToZip = FileUtils.find(tempOutputDir.toAbsolutePath(), featureName + ".*");
    //
    //        Path sa1OutLoc = Paths.get(dirToSaveSelectedSA1Features + featureName + "_Selected_SA1s.zip").toAbsolutePath();
    //        Zip.create(sa1OutLoc, filesToZip);
    //        Log.info("Saved selected SA1s to: " + sa1OutLoc.toString());
    //
    //        return sa1Collection;
    //    }
}
