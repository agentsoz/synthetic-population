package io.github.agentsoz.syntheticpop.addressmapper;

import io.github.agentsoz.syntheticpop.filemanager.json.JSONWriter;
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
import java.util.zip.DataFormatException;

/**
 * @author wniroshan 01 May 2018
 */
public class StatisticalArea2AddressMapper {

    private final FeatureProcessor featProcessor;
    private final ShapefileGeoFeatureReader shapesReader;

    private final Path saFilePath, tempOutputDir, outputFile;
    private final String addressLookupKey, saLookupKey, saFilterKey, addressShapeFilePattern, saShapeFileName, duplicatesCheckKey,
            sa1ToLgaMapJsonName;

    private final Map<String, String> newAttributesToAddress;
    private final String[] saFilterValues, addressFilePathStr;

    private final boolean deleteTempShapeFiles;
    private final int areaNameDirIndex;

    private final HashSet<String> checkedAddresses = new HashSet<>();
    private final Map<String, Set<String>> sa1ToLgaMap = new HashMap<>();

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
        sa1ToLgaMapJsonName = props.getProperty("SA1toAddressesFileMapJsonName");

    }

    void mapAddressesToStatisticalAreas() {
        // Find the address.shp files in the specified address zip files
        List<Path> addressShapeFiles = findAddressShapeFiles(addressFilePathStr, addressShapeFilePattern);
        Log.info("Located " + addressShapeFiles.size() + " address shape files");


        //Load the mesh blocks
        Log.info("Locating " + saShapeFileName + " in " + saFilePath);
        SimpleFeatureCollection meshBlocks = loadSAMeshBlocks();

        Map<String, Set<String>> sa1ToLgaMap = new HashMap<>();//Tells which lga files have addresses of an SA1

        //Locate the SA id of addresses in each address shapefile
        Iterator<Path> addressShapeFilesItr = addressShapeFiles.iterator();
        while (addressShapeFilesItr.hasNext()) {
            Path addressShape = addressShapeFilesItr.next();
            SimpleFeatureCollection updatedFeatureCollection = mapAddressesToSAMeshBlocks(addressShape, meshBlocks, sa1ToLgaMap);

            if (!updatedFeatureCollection.isEmpty()) {
                Path tempLocation = tempOutputDir.resolve(getAreaName(addressShape).toString());
                try {
                    Path saved = saveToTempShapeFile(updatedFeatureCollection, tempLocation);
                    Log.debug("Temporary files saved to: " + saved.toString());
                } catch (IOException e) {
                    Log.errorAndExit("Writing to temp file location failed: " + tempLocation, e, GlobalConstants.ExitCode.DATA_ERROR);
                }

            } else {
                //Remove because we don't save the empty feature collection. Otherwise below zip creation code fails.
                addressShapeFilesItr.remove();
                Log.warn("Empty processed addresses collection - " + getAreaName(addressShape) + ". It seems no addresses belong to any " +
                                 "of the mesh blocks (statistical areas)");
            }
        }

        Path sa1ToLgaMapJson = tempOutputDir.resolve(sa1ToLgaMapJsonName);
        try {
            JSONWriter.writeListToJsonFile(sa1ToLgaMap, sa1ToLgaMapJson);
        } catch (IOException e) {
            Log.error("Writing SA1toLGAmap.json file failed", e);
        }

        //Create a zip with all the updated address shape files
        zipAllUpdatedShapeFiles(addressShapeFiles, sa1ToLgaMapJson);
        System.out.println("Updated addresses zip saved to: " + outputFile);
    }

    private void zipAllUpdatedShapeFiles(List<Path> addressShapeFiles, Path sa1ToLgaMapJson) {
        try {
            Log.info("Creating a zip file with all shape files ... ");

            List<Path> filesToZip = new ArrayList<>();
            filesToZip.add(sa1ToLgaMapJson);

            Path[] areaNames = addressShapeFiles.stream().map(this::getAreaName).toArray(Path[]::new);
            for (Path areaName : areaNames) {
                filesToZip.add(tempOutputDir.toAbsolutePath().resolve(areaName.toString()));
            }

            Zip.archiveDirectories(outputFile.toAbsolutePath(), filesToZip, deleteTempShapeFiles);

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
     * @param sa1ToLgaMap      Map giving which LGA shape files (values) contains addresses of a SA1 (keys)
     * @return New collection of addresses that are in one of the mesh blocks with the corresponding mesh block id and SA1 id.
     */
    private SimpleFeatureCollection mapAddressesToSAMeshBlocks(Path addressShapeFile,
                                                               SimpleFeatureCollection meshBlocks,
                                                               Map<String, Set<String>> sa1ToLgaMap) {

        SimpleFeatureCollection newFeatureCollection = new DefaultFeatureCollection();

        Log.info("Loading address features in " + addressShapeFile.toUri());

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
        try (SimpleFeatureIterator addressFeatItr = addresses.features()) {
            Log.info("Matching address " + addressLookupKey + " IDs to SA mesh block " + saLookupKey + " IDs");

            while (addressFeatItr.hasNext()) {

                addressFeat = addressFeatItr.next(); //Address feature

                if (!isDuplicate(addressFeat)) {
                    assert meshBlocks != null;
                    SimpleFeature mb = m.findSAMeshBlock(addressFeat, meshBlocks);
                    if (mb != null) {
                        SimpleFeature updatedAddr = updateAddress(addressFeat, mb);
                        ((DefaultFeatureCollection) newFeatureCollection).add(updatedAddr);
                        updateLga2SaMap(sa1ToLgaMap, updatedAddr, addressShapeFile);

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

    private void updateLga2SaMap(Map<String, Set<String>> sa1ToLgaMap, SimpleFeature address, Path addressFile) {
        Map.Entry<String, String> entry = newAttributesToAddress.entrySet().stream().findFirst().orElse(null);
        assert entry != null;
        sa1ToLgaMap.computeIfAbsent((String) address.getAttribute(entry.getKey()), v -> new HashSet<>())
                   .add(getAreaName(addressFile).toString());
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
