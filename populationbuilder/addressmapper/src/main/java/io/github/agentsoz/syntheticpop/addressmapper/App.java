package io.github.agentsoz.syntheticpop.addressmapper;

import io.github.agentsoz.syntheticpop.filemanager.zip.Zip;
import io.github.agentsoz.syntheticpop.geo.FeatureProcessor;
import io.github.agentsoz.syntheticpop.geo.ShapefileGeoFeatureReader;
import io.github.agentsoz.syntheticpop.geo.ShapefileGeoFeatureWriter;
import io.github.agentsoz.syntheticpop.util.ConfigProperties;
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
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

/**
 * @author wniroshan 16 Apr 2018
 */
public class App {
    private static Path saFilePath = null, tempOutputDir = null, outputFile = null;
    private static String addressLookupKey = null, saLookupKey = null, saFilterKey = null, addressShapeFilePattern = null,
            saShapeFileName = null, duplicatesCheckKey = null;

    private static Map<String, String> newAttributesToAddress = null;
    private static String[] saFilterValues = null, addressFilePathStr = null;

    private static FeatureProcessor featProcessor;
    private static ShapefileGeoFeatureReader shapesReader;
    private static HashSet<String> checkedAddresses = new HashSet<>();

    private static int ADDR_AREA_NAME_OFFSET = 3;

    private static void usage() {
        System.out.println("Usage: java -jar addressmapper.jar <properties file>");
        System.out.println(
                "This program maps mesh blocks and the corresponding SA1s in shape files obtained from Australian Bureau of Statistics to" +
                        " the addresses obtained from Vicmaps\n");
        System.exit(0);
    }

    public static void main(String args[]) {
        System.out.println("Starting addresses mapping");
        Log.createLogger("AddressMapper", "AddressMapper.log");

        boolean deleteTempShapeFiles = true;
        /* Read all the properties */
        if (args.length > 0) {
            ConfigProperties props = null;
            try {
                props = new ConfigProperties(args[0]);
            } catch (IOException e) {
                Log.error("When reading properties file", e);
                usage();
            }
            assert props != null;
            addressFilePathStr = props.readCommaSepProperties("AddressesShapeFileZip");
            addressShapeFilePattern = props.getProperty("AddressesShapeFileNamePattern");
            saFilePath = props.readFileOrDirectoryPath("SAShapeFileZip");
            saShapeFileName = props.getProperty("SAShapeFileName");

            addressLookupKey = props.getProperty("AddressLookupKey");
            duplicatesCheckKey = props.getProperty("DuplicatesCheckKey");
            saLookupKey = props.getProperty("SALookupKey");
            newAttributesToAddress = props.readKeyValuePairs("NewAttributesToAddress");
            tempOutputDir = props.getProperty("TemporaryOutputDirectory").trim().equals("system")
                            ? Paths.get(System.getProperty("java.io.tmpdir"))
                            : props.readFileOrDirectoryPath("TemporaryOutputDirectory");
            saFilterKey = props.getProperty("SAFilterPropertyName").trim();
            saFilterValues = props.readCommaSepProperties("SAFilterPropertyValues");
            outputFile = props.readFileOrDirectoryPath("OutputFile");
            deleteTempShapeFiles = Boolean.parseBoolean(props.getOrDefault("DeleteTemporaryShapeFiles", "true").toString());

        } else {
            usage();
        }

        shapesReader = new ShapefileGeoFeatureReader();
        featProcessor = new FeatureProcessor();

        List<Path> addressShapeFiles = new ArrayList<>();
        SimpleFeatureCollection meshBlocks = null;

        for (String addressFile : addressFilePathStr) {
            try {
                Log.info("Locating " + addressShapeFilePattern + " in " + addressFile);
                addressShapeFiles.addAll(Zip.findFiles(Paths.get(addressFile), addressShapeFilePattern));
            } catch (IOException ex) {
                Log.errorAndExit("Unable to read " + addressShapeFilePattern + " file", ex, GlobalConstants.ExitCode.USERINPUT);
            }
        }
        Log.info("Located " + addressShapeFiles.size() + " address shape files");


        Log.info("Locating " + saShapeFileName + " in " + saFilePath);
        try {
            meshBlocks = loadSAMeshBlocks();
            Log.debug("Loading SA mesh block features where " + saFilterKey + " is " + Arrays.stream(saFilterValues)
                                                                                             .collect(Collectors.toList()));
        } catch (IOException ex) {
            Log.errorAndExit("Unable to read " + saShapeFileName + " file", ex, GlobalConstants.ExitCode.USERINPUT);
        }

        for (Path addressShape : addressShapeFiles) {

            SimpleFeatureCollection newFeatureCollection = new DefaultFeatureCollection();

            Log.info("Loading address features in " + addressShape.toUri());

            Matcher m = new Matcher(featProcessor, saLookupKey, addressLookupKey);
            SimpleFeatureCollection addresses = null;

            try {
                addresses = loadAddresses(addressShape);
            } catch (IOException e) {
                Log.errorAndExit("Addresses loading failed", e, GlobalConstants.ExitCode.USERINPUT);
            }

            assert addresses != null;
            int totalAddresses = addresses.size(), processed = 0, duplicates = 0, logi = 1;

            SimpleFeature addressFeat = null;
            try (SimpleFeatureIterator addressFeatItr = addresses.features()) {
                Log.info("Matching address " + addressLookupKey + " IDs to SA mesh block " + saLookupKey + " IDs");

                while (addressFeatItr.hasNext()) {

                    addressFeat = addressFeatItr.next(); //Address feature

                    if (!isDuplicate(addressFeat)) {
                        assert meshBlocks != null;
                        SimpleFeature mb = m.findSAMeshBlock(addressFeat, meshBlocks);
                        if (mb != null) {
                            updateAddress(addressFeat, mb, newFeatureCollection);
                        }
                    } else {
                        duplicates++;
                    }

                    processed++;
                    if (processed == logi) {
                        Log.info("Processed " + processed + " / " + totalAddresses + " duplicates: " + duplicates);
                        logi += logi;
                    }

                }
                Log.info("Processed " + processed + " / " + totalAddresses + " duplicates: " + duplicates);
                Log.info(m.getStats());

            } catch (IOException e) {
                Log.debug(addressFeat.getAttributes().toString());
                Log.errorAndExit("Mesh block area loading failed", e, GlobalConstants.ExitCode.USERINPUT);
            } catch (DataFormatException | CQLException e) {
                Log.debug(addressFeat.getAttributes().toString());
                Log.errorAndExit("Mesh block area loading failed", e, GlobalConstants.ExitCode.DATA_ERROR);
            }

            Path tempLocation = tempOutputDir.resolve(getAreaName(addressShape).toString());
            try {
                Path saved = saveToTempShapeFile(newFeatureCollection, tempLocation);
                Log.debug("Temporary files saved to: " + saved.toString());
            } catch (IOException e) {
                Log.errorAndExit("Writing to temp file location failed: " + tempLocation, e, GlobalConstants.ExitCode.DATA_ERROR);
            }
        }

        try {
            zipShapeFiles(addressShapeFiles.stream().map(App::getAreaName).toArray(Path[]::new),
                          tempOutputDir,
                          outputFile,
                          deleteTempShapeFiles);
        } catch (IOException e) {
            Log.errorAndExit("Writing updated addresses shapefile failed", e, GlobalConstants.ExitCode.IOERROR);
        } catch (URISyntaxException e) {
            Log.errorAndExit("Creating a zip with updated addresses shapefile failed", e, GlobalConstants.ExitCode.IOERROR);
        }

        Log.info("Updated addresses zip saved to: " + outputFile);
        System.out.println("Updated addresses zip saved to: " + outputFile + "\nDone!");
    }

    private static Path saveToTempShapeFile(SimpleFeatureCollection featCollection,
                                            Path tempFileLoc) throws IOException {
        Path tempOutDir = tempFileLoc.toAbsolutePath();
        Files.createDirectories(tempOutDir);

        return new ShapefileGeoFeatureWriter().writeFeatures(DataUtilities.simple(featCollection),
                                                             tempOutDir);

    }

    private static void zipShapeFiles(Path[] areaNames,
                                      Path tempFileLoc,
                                      Path outputLoc,
                                      boolean deleteTempFiles) throws IOException, URISyntaxException {
        List<Path> filesToZip = new ArrayList<>();
        for (Path areaName : areaNames) {
            filesToZip.add(tempFileLoc.toAbsolutePath().resolve(areaName.toString()));
        }

        Zip.archiveDirectories(outputLoc.toAbsolutePath(), filesToZip, deleteTempFiles);
    }

    private static SimpleFeatureCollection loadSAMeshBlocks() throws IOException {
        FeatureSource<SimpleFeatureType, SimpleFeature> ftSource = shapesReader.getFeatureSource(saFilePath, saShapeFileName);
        return (SimpleFeatureCollection) shapesReader.loadFeaturesByProperty(ftSource, saFilterKey, saFilterValues);
    }

    private static SimpleFeatureCollection loadAddresses(Path addressShape) throws IOException {
        FeatureSource<SimpleFeatureType, SimpleFeature> addressFeatSrc = shapesReader.getFeatureSource(addressShape);
        return (SimpleFeatureCollection) shapesReader.loadFeatures(addressFeatSrc);

    }

    private static Path getAreaName(Path addressShapeFile) {
        return addressShapeFile.getName(addressShapeFile.getNameCount() - ADDR_AREA_NAME_OFFSET);
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

    private static boolean isDuplicate(SimpleFeature address) {
        return !checkedAddresses.add(address.getAttribute(duplicatesCheckKey).toString());
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
