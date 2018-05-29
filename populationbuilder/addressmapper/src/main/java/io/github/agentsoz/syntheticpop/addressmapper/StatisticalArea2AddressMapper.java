package io.github.agentsoz.syntheticpop.addressmapper;

/*-
 * #%L
 * Synthetic Population Construction for Australia
 * %%
 * Copyright (C) 2016 - 2018 by its authors. See AUTHORS file.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import com.vividsolutions.jts.geom.Envelope;
import io.github.agentsoz.syntheticpop.addressmapper.models.Address;
import io.github.agentsoz.syntheticpop.filemanager.FileUtils;
import io.github.agentsoz.syntheticpop.filemanager.zip.Zip;
import io.github.agentsoz.syntheticpop.geo.ShapefileGeoFeatureWriter;
import io.github.agentsoz.syntheticpop.util.ConfigProperties;
import io.github.agentsoz.syntheticpop.util.GlobalConstants;
import io.github.agentsoz.syntheticpop.util.Log;
import org.geotools.data.DataUtilities;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.feature.DefaultFeatureCollection;
import org.opengis.feature.simple.SimpleFeature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wniroshan 01 May 2018
 */
public class StatisticalArea2AddressMapper {

    private final ShapesProcessor shapesProcessor;

    private final Path saFilePath, tempOutputDir, outputFile, sa1ToAddressJson;
    private final String saFilterKey, addressShapeFilePattern, saShapeFileName, duplicatesCheckKey;

    private final Map<String, String> newAttributesToAddress;
    private final String[] saFilterValues, addressFilePathStr;

    private final boolean deleteTempShapeFiles;
    private final int areaNameDirIndex;

    private final HashSet<String> checkedAddresses = new HashSet<>();
    private final Map<String, List<Address>> addressesBySA = new HashMap<>(); //Key: SA1 code, Value: list of addresses in SA1

    StatisticalArea2AddressMapper(ConfigProperties props, ShapesProcessor shapesProcessor) {
        this.shapesProcessor = shapesProcessor;

        assert props != null;
        this.addressFilePathStr = props.readCommaSepProperties("AddressesShapeFileZip");
        this.addressShapeFilePattern = props.getProperty("AddressesShapeFileNamePattern");
        this.saFilePath = props.readFileOrDirectoryPath("SAShapeFileZip");
        this.saShapeFileName = props.getProperty("SAShapeFileName");

        duplicatesCheckKey = props.getProperty("DuplicatesCheckKey");
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


        //Load the statistical areas
        Log.info("Locating " + saShapeFileName + " in " + saFilePath);
        Map<SimpleFeature, Envelope> saFeatures = shapesProcessor.loadStatisticalAreas(saFilePath,
                                                                                       saShapeFileName,
                                                                                       saFilterKey,
                                                                                       saFilterValues);

        //Locate the SA id of addresses in each address shapefile and save them to a new temporary shape file
        Iterator<Path> addressShapeFilesItr = addressShapeFiles.iterator();
        List<Path> tempShapeFiles = new ArrayList<>(); //The list of newly created temporary shape files
        while (addressShapeFilesItr.hasNext()) {
            //Get a shape file and find the SA1s of addresses in them
            Path addressShapeFile = addressShapeFilesItr.next();
            SimpleFeatureCollection updatedFeatureCollection = findContainingSAs(addressShapeFile, saFeatures);

            //Save a copy of addresses collection in a new temporary file. (Created one for each address shape file.
            if (!updatedFeatureCollection.isEmpty()) {
                Path tempLocation = tempOutputDir.resolve(getAreaName(addressShapeFile).toString());
                try {
                    Path saved = saveToTempShapeFile(updatedFeatureCollection, tempLocation);
                    tempShapeFiles.add(saved);
                    Log.debug("Temporary files saved to: " + saved.toString());
                } catch (IOException e) {
                    Log.errorAndExit("Writing to temp file location failed: " + tempLocation, e, GlobalConstants.ExitCode.DATA_ERROR);
                }

            } else {
                Log.warn("Empty processed addresses collection - " + getAreaName(addressShapeFile) + ". It seems no addresses belong to any " +
                                 "of the statistical areas");
            }
        }

        //Save the addresses grouped by the SA1 code in a json (text) file. So we don't have to process shape files after this.
        try {
            AddressUtil.saveAsJSONFile(addressesBySA,
                                       saFeatures.keySet().toArray(new SimpleFeature[]{})[0].getType().getCoordinateReferenceSystem(),
                                       sa1ToAddressJson);
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
     * Finds the SA of each address. The addresses that belong to the specified SAs are copied to a new FeatureCollection instance, and SA
     * ids are added as new attributes. This does not alter the address instances in original FeatureCollection.
     *
     * @param addressShapeFile The addresses shape file
     * @param allSAs           The SAs and their bounding boxes (envelopes) that addresses  are expected to belong to
     * @return New collection of addresses that are in one of the SAs with the corresponding SA id.
     */
    private SimpleFeatureCollection  findContainingSAs(Path addressShapeFile,
                                                      Map<SimpleFeature, Envelope> allSAs) {

        SimpleFeatureCollection newFeatureCollection = new DefaultFeatureCollection();

        Log.info("Loading address features in " + addressShapeFile.toUri());

        //The addresses are grouped by different values of this key, typically the SA1 code.
        String groupingKey = newAttributesToAddress.entrySet().stream().findFirst().orElse(null).getKey();

        SimpleFeatureCollection addresses = null;

        try {
            addresses = shapesProcessor.loadAddresses(addressShapeFile);
        } catch (IOException e) {
            Log.errorAndExit("Addresses loading failed", e, GlobalConstants.ExitCode.USERINPUT);
        }

        assert addresses != null;
        int totalAddresses = addresses.size(), processed = 0, duplicates = 0, logi = 1;
        SimpleFeature addressFeat = null;

        int outside = 0;
        //Iterate all addresses finding their SAs
        try (SimpleFeatureIterator addressFeatItr = addresses.features()) {
            Log.info("Matching addresses to Statistical Areas");

            while (addressFeatItr.hasNext()) {

                addressFeat = addressFeatItr.next(); //Address feature

                if (!isDuplicate(addressFeat)) {//Have we already encountered an address feature with the same street address (EZI_ADD)
                    assert allSAs != null;
                    SimpleFeature sa = shapesProcessor.findStatisticalArea(addressFeat, allSAs);
                    if (sa != null) {
                        //Add SA id to address feature
                        SimpleFeature updatedAddr = shapesProcessor.updateAddress(addressFeat, sa, newAttributesToAddress);
                        ((DefaultFeatureCollection) newFeatureCollection).add(updatedAddr);

                        //Record addresses grouping by their SA1
                        addressesBySA.computeIfAbsent((String) updatedAddr.getAttribute(groupingKey), v -> new ArrayList<>())
                                     .add(AddressUtil.map2POJO(updatedAddr));

                    }else{
                        outside++;
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
            Log.info("Processed " + processed + " / " + totalAddresses + " addresses, duplicates: " + duplicates+", outside interest area: "+outside);

        } catch (IOException e) {
            Log.debug(addressFeat.getAttributes().toString());
            Log.errorAndExit("SA area loading failed", e, GlobalConstants.ExitCode.USERINPUT);
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


    private Path getAreaName(Path addressShapeFile) {
        return addressShapeFile.getName(addressShapeFile.getNameCount() - areaNameDirIndex);
    }


    private boolean isDuplicate(SimpleFeature address) {
        return !checkedAddresses.add(address.getAttribute(duplicatesCheckKey).toString());
    }
}
