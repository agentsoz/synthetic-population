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

        // Read all the properties
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

        // Find the address.shp files in the specified address zip files
        for (String addressFile : addressFilePathStr) {
            try {
                Log.info("Locating " + addressShapeFilePattern + " in " + addressFile);
                addressShapeFiles.addAll(Zip.findFiles(Paths.get(addressFile), addressShapeFilePattern));
            } catch (IOException ex) {
                Log.errorAndExit("Unable to read " + addressShapeFilePattern + " file", ex, GlobalConstants.ExitCode.USERINPUT);
            }
        }
        Log.info("Located " + addressShapeFiles.size() + " address shape files");


        //Load the mesh blocks
        Log.info("Locating " + saShapeFileName + " in " + saFilePath);
        try {
            meshBlocks = loadSAMeshBlocks();
            Log.debug("Loading SA mesh block features where " + saFilterKey + " is " + Arrays.stream(saFilterValues)
                                                                                             .collect(Collectors.toList()));
        } catch (IOException ex) {
            Log.errorAndExit("Unable to read " + saShapeFileName + " file", ex, GlobalConstants.ExitCode.USERINPUT);
        }

        //Locate the SA id of addresses in each address shapefile
        Iterator<Path> addressShapesItr = addressShapeFiles.iterator();
        while (addressShapesItr.hasNext()) {
            Path addressShape = addressShapesItr.next();
            SimpleFeatureCollection updatedFeatureCollection = mapAddressesToSAMeshBlocks(addressShape, meshBlocks);


            if (!updatedFeatureCollection.isEmpty()) {
                Path tempLocation = tempOutputDir.resolve(getAreaName(addressShape).toString());
                try {
                    Path saved = saveToTempShapeFile(updatedFeatureCollection, tempLocation);
                    Log.debug("Temporary files saved to: " + saved.toString());
                } catch (IOException e) {
                    Log.errorAndExit("Writing to temp file location failed: " + tempLocation, e, GlobalConstants.ExitCode.DATA_ERROR);
                }
            } else {
                addressShapesItr.remove(); //Remove because we don't save the empty feature collection. Otherwise below zip creation code fails.
                Log.warn("Empty processed addresses collection - "+getAreaName(addressShape)+". It seems no addresses belong to any of the mesh blocks (statistical areas)");
            }
        }

        //Create a zip with all the updated address shape files
        try {
            Log.info("Creating a zip file with all shape files ... ");
            zipShapeFiles(addressShapeFiles.stream().map(App::getAreaName).toArray(Path[]::new),
                          tempOutputDir,
                          outputFile,
                          deleteTempShapeFiles);
        } catch (IOException e) {
            Log.errorAndExit("Writing updated addresses shapefiles to zip failed", e, GlobalConstants.ExitCode.IOERROR);
        } catch (URISyntaxException e) {
            Log.errorAndExit("Creating a zip with updated addresses shapefile failed", e, GlobalConstants.ExitCode.IOERROR);
        }

        Log.info("Updated addresses zip saved to: " + outputFile);
        System.out.println("Updated addresses zip saved to: " + outputFile + "\nDone!");
    }

    /**
     * Finds the address that belong to one of the specified mesh blocks. The addresses that are in the mesh blocks are copied to a new
     * FeatureCollection instance, and mesh block id and SA1 ids are added as new attributes. This does not alter the address instances in
     * original FeatureCollection.
     *
     * @param addressShapeFile The addresses shape file
     * @param meshBlocks       The mesh blocks that addresses are expecte do to belong to
     * @return New collection of addresses that are in one of the mesh blocks with the corresponding mesh block id and SA1 id.
     */
    private static SimpleFeatureCollection mapAddressesToSAMeshBlocks(Path addressShapeFile, SimpleFeatureCollection meshBlocks) {

        SimpleFeatureCollection newFeatureCollection = new DefaultFeatureCollection();

        Log.info("Loading address features in " + addressShapeFile.toUri());

        Matcher m = new Matcher(featProcessor, saLookupKey, addressLookupKey);
        SimpleFeatureCollection addresses = null;

        try {
            addresses = loadAddresses(addressShapeFile);
        } catch (IOException e) {
            Log.errorAndExit("Addresses loading failed", e, GlobalConstants.ExitCode.USERINPUT);
        }

        assert addresses != null;
        int totalAddresses = addresses.size(), processed = 0, duplicates = 0, logi = 1;

        SimpleFeature addressFeat = null;
        try (SimpleFeatureIterator addressFeatItr = addresses.features()) {
            Log.info("Matching address " + addressLookupKey + " IDs to SA mesh block " + saLookupKey + " IDs");

            while (addressFeatItr.hasNext() && processed < 3) {

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
        return newFeatureCollection;
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
}
