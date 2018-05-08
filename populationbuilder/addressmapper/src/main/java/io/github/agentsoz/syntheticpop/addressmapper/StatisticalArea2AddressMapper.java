package io.github.agentsoz.syntheticpop.addressmapper;

import io.github.agentsoz.syntheticpop.addressmapper.models.Address;
import io.github.agentsoz.syntheticpop.filemanager.FileUtils;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

/**
 * @author wniroshan 01 May 2018
 */
public class StatisticalArea2AddressMapper {

    private final FeatureProcessor featProcessor;
    private final ShapefileGeoFeatureReader shapesReader;

    private final Path saFilePath, tempOutputDir, outputFile, sa1ToAddressJson;
    private final String addressLookupKey, saLookupKey, saFilterKey, addressShapeFilePattern, saShapeFileName, duplicatesCheckKey;

    private final Map<String, String> newAttributesToAddress;
    private final String[] saFilterValues, addressFilePathStr;

    private final boolean deleteTempShapeFiles;
    private final int areaNameDirIndex;

    private final HashSet<String> checkedAddresses = new HashSet<>();
    private final Map<String, List<Address>> addressesBySA = new HashMap<>(); //Key: SA1 code, Value: list of addresses in SA1

    StatisticalArea2AddressMapper(ConfigProperties props, FeatureProcessor featProcessor, ShapefileGeoFeatureReader shapesReader) {
        this.featProcessor = featProcessor;
        this.shapesReader = shapesReader;

        assert props != null;
        this.addressFilePathStr = props.readCommaSepProperties("AddressesShapeFileZip");
        this.addressShapeFilePattern = props.getProperty("AddressesShapeFileNamePattern");
        this.saFilePath = props.readFileOrDirectoryPath("SAShapeFileZip");
        this.saShapeFileName = props.getProperty("SAShapeFileName");

        addressLookupKey = props.getProperty("AddressLookupKey");
        duplicatesCheckKey = props.getProperty("DuplicatesCheckKey");
        saLookupKey = props.getProperty("SALookupKey");
        newAttributesToAddress = props.readKeyValuePairs("NewAttributesToAddress");
        tempOutputDir = props.getProperty("TemporaryOutputDirectory").trim().equals("system")
                        ? Paths.get(System.getProperty("java.io.tmpdir"))
                        : props.readFileOrDirectoryPath("TemporaryOutputDirectory");
        saFilterKey = props.getProperty("SAFilterPropertyName").trim();
        saFilterValues = props.readCommaSepProperties("SAFilterPropertyValues");
        outputFile = props.readFileOrDirectoryPath("UpdatedAddressShapeFile");
        deleteTempShapeFiles = Boolean.parseBoolean(props.getOrDefault("DeleteTemporaryShapeFiles", "true").toString());
        areaNameDirIndex = Integer.parseInt(props.getProperty("AreaNameDirIndex"));
        sa1ToAddressJson = props.readFileOrDirectoryPath("SA1toAddressesJsonFile");

    }

    void mapAddressesToStatisticalAreas() {
        // Find the address.shp files in the specified address zip files
        List<Path> addressShapeFiles = findAddressShapeFiles(addressFilePathStr, addressShapeFilePattern);
        Log.info("Located " + addressShapeFiles.size() + " address shape files");


        //Load the mesh blocks
        Log.info("Locating " + saShapeFileName + " in " + saFilePath);
        SimpleFeatureCollection meshBlocks = loadSAMeshBlocks();

        //Locate the SA id of addresses in each address shapefile and save them to a new temporary shape file
        Iterator<Path> addressShapeFilesItr = addressShapeFiles.iterator();
        List<Path> tempShapeFiles = new ArrayList<>(); //The list of newly created temporary shape files
        while (addressShapeFilesItr.hasNext()) {
            //Get a shape file and find the SA1s of addresses in them
            Path addressShape = addressShapeFilesItr.next();
            SimpleFeatureCollection updatedFeatureCollection = mapAddressesToSAMeshBlocks(addressShape, meshBlocks);

            //Save a copy of addresses collection in a new temporary file. (Created one for each address shape file.
            if (!updatedFeatureCollection.isEmpty()) {
                Path tempLocation = tempOutputDir.resolve(getAreaName(addressShape).toString());
                try {
                    Path saved = saveToTempShapeFile(updatedFeatureCollection, tempLocation);
                    tempShapeFiles.add(saved);
                    Log.debug("Temporary files saved to: " + saved.toString());
                } catch (IOException e) {
                    Log.errorAndExit("Writing to temp file location failed: " + tempLocation, e, GlobalConstants.ExitCode.DATA_ERROR);
                }

            } else {
                Log.warn("Empty processed addresses collection - " + getAreaName(addressShape) + ". It seems no addresses belong to any " +
                                 "of the mesh blocks (statistical areas)");
            }
        }

        //Save the addresses grouped by the SA1 code in a json (text) file. So we don't have to process shape files after this.
        try {
            AddressUtil.saveAsJSONFile(addressesBySA, meshBlocks.getSchema().getCoordinateReferenceSystem(), sa1ToAddressJson);
            Log.info("Summary addresses file saved to " + sa1ToAddressJson);
        } catch (IOException e) {
            Log.errorAndExit("Writing " + sa1ToAddressJson + " file failed", e, GlobalConstants.ExitCode.IOERROR);
        }

        //Create a zip with all the updated address shape files
        zipAllUpdatedShapeFiles(tempShapeFiles, sa1ToAddressJson);
        System.out.println("Updated addresses zip saved to: " + outputFile);
    }

    private void zipAllUpdatedShapeFiles(List<Path> addressShapeFiles, Path sa1ToLgaMapJson) {
        try {
            Log.info("Creating a zip file with all shape files ... ");

            List<Path> filesToZip = addressShapeFiles.stream().map(Path::getParent).collect(Collectors.toList());
            filesToZip.add(sa1ToLgaMapJson);

            Zip.archiveDirectories(outputFile.toAbsolutePath(), filesToZip);

            if (deleteTempShapeFiles) {
                FileUtils.delete(addressShapeFiles);
            }


        } catch (IOException e) {
            Log.errorAndExit("Writing updated addresses shapefiles to zip failed", e, GlobalConstants.ExitCode.IOERROR);
        }

        Log.info("Updated address shapefiles zip saved to: " + outputFile);
    }

    private List<Path> findAddressShapeFiles(String[] addressFilePathStr, String addressShapeFilePattern) {
        List<Path> addressShapeFiles = new ArrayList<>();
        for (String addressFile : addressFilePathStr) {
            try {
                Log.info("Locating " + addressShapeFilePattern + " in " + addressFile);
                addressShapeFiles.addAll(Zip.findFiles(Paths.get(addressFile), addressShapeFilePattern));
            } catch (IOException ex) {
                Log.errorAndExit("Unable to read " + addressShapeFilePattern + " file", ex, GlobalConstants.ExitCode.USERINPUT);
            }
        }
        return addressShapeFiles;
    }

    /**
     * Finds the address that belong to one of the specified mesh blocks. The addresses that are in the mesh blocks are copied to a new
     * FeatureCollection instance, and mesh block id and SA1 ids are added as new attributes. This does not alter the address instances in
     * original FeatureCollection.
     *
     * @param addressShapeFile The addresses shape file
     * @param meshBlocks       The mesh blocks that addresses are expected to belong to
     * @return New collection of addresses that are in one of the mesh blocks with the corresponding mesh block id and SA1 id.
     */
    private SimpleFeatureCollection mapAddressesToSAMeshBlocks(Path addressShapeFile,
                                                               SimpleFeatureCollection meshBlocks) {

        SimpleFeatureCollection newFeatureCollection = new DefaultFeatureCollection();

        Log.info("Loading address features in " + addressShapeFile.toUri());

        //The addresses are grouped by different values of this key. This is an attribute in the address feature typically SA1 code.
        String groupingKey = newAttributesToAddress.entrySet().stream().findFirst().orElse(null).getKey();

        Matcher m = new Matcher(featProcessor, saLookupKey, addressLookupKey);
        SimpleFeatureCollection addresses = null;

        try {
            addresses = loadAddresses(addressShapeFile);
        } catch (IOException e) {
            Log.errorAndExit("AddressUtil loading failed", e, GlobalConstants.ExitCode.USERINPUT);
        }


        assert addresses != null;
        int totalAddresses = addresses.size(), processed = 0, duplicates = 0, logi = 1;
        SimpleFeature addressFeat = null;

        //Iterate all addresses finding their mesh blocks.
        try (SimpleFeatureIterator addressFeatItr = addresses.features()) {
            Log.info("Matching address " + addressLookupKey + " IDs to SA mesh block " + saLookupKey + " IDs");

            while (addressFeatItr.hasNext()) {

                addressFeat = addressFeatItr.next(); //Address feature

                if (!isDuplicate(addressFeat)) {//Have we encountered an address feature with the same street address (EZI_ADD)
                    assert meshBlocks != null;
                    SimpleFeature mb = m.findSAMeshBlock(addressFeat, meshBlocks);
                    if (mb != null) {
                        //Add SA1 and ABS mesh block id to address feature
                        SimpleFeature updatedAddr = updateAddress(addressFeat, mb);
                        ((DefaultFeatureCollection) newFeatureCollection).add(updatedAddr);

                        //Record addresses grouping by their SA1
                        addressesBySA.computeIfAbsent((String) updatedAddr.getAttribute(groupingKey), v -> new ArrayList<>())
                                     .add(AddressUtil.map2POJO(updatedAddr));

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

    private Path saveToTempShapeFile(SimpleFeatureCollection featCollection,
                                     Path tempFileLoc) throws IOException {
        Path tempOutDir = tempFileLoc.toAbsolutePath();
        Files.createDirectories(tempOutDir);

        return new ShapefileGeoFeatureWriter().writeFeatures(DataUtilities.simple(featCollection),
                                                             tempOutDir);

    }

    private SimpleFeatureCollection loadSAMeshBlocks() {
        SimpleFeatureCollection meshBlocks = null;
        try {
            FeatureSource<SimpleFeatureType, SimpleFeature> ftSource = shapesReader.getFeatureSource(saFilePath, saShapeFileName);
            meshBlocks = (SimpleFeatureCollection) shapesReader.loadFeaturesByProperty(ftSource,
                                                                                       saFilterKey,
                                                                                       saFilterValues);
            Log.debug("Loading SA mesh block features where " + saFilterKey + " is " + Arrays.stream(saFilterValues));
        } catch (IOException ex) {
            Log.errorAndExit("Unable to read " + saShapeFileName + " file", ex, GlobalConstants.ExitCode.USERINPUT);
        }
        return meshBlocks;
    }

    private SimpleFeatureCollection loadAddresses(Path addressShape) throws IOException {
        FeatureSource<SimpleFeatureType, SimpleFeature> addressFeatSrc = shapesReader.getFeatureSource(addressShape);
        return (SimpleFeatureCollection) shapesReader.loadFeatures(addressFeatSrc);

    }

    private Path getAreaName(Path addressShapeFile) {
        return addressShapeFile.getName(addressShapeFile.getNameCount() - areaNameDirIndex);
    }

    /**
     * Updates a copy of the address with new information and returns the copy
     *
     * @param address     The address feature
     * @param saMeshBlock The matching mesh block feature
     * @throws IOException If processing failed
     */
    private SimpleFeature updateAddress(SimpleFeature address,
                                        SimpleFeature saMeshBlock) throws IOException {
        SimpleFeature copyAddressFeat = featProcessor.addNewAttributeType(address,
                                                                          newAttributesToAddress.keySet(),
                                                                          String.class);
        for (Map.Entry<String, String> entry : newAttributesToAddress.entrySet()) {
            copyAddressFeat.setAttribute(entry.getKey(), saMeshBlock.getAttribute(entry.getValue()));
        }

        return copyAddressFeat;
    }

    private boolean isDuplicate(SimpleFeature address) {
        return !checkedAddresses.add(address.getAttribute(duplicatesCheckKey).toString());
    }
}
