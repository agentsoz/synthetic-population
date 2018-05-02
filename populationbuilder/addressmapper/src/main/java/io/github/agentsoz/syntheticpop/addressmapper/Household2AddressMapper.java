package io.github.agentsoz.syntheticpop.addressmapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.agentsoz.syntheticpop.addressmapper.models.Address;
import io.github.agentsoz.syntheticpop.filemanager.FileUtils;
import io.github.agentsoz.syntheticpop.filemanager.csv.CSVWriter;
import io.github.agentsoz.syntheticpop.filemanager.zip.Zip;
import io.github.agentsoz.syntheticpop.geo.ShapefileGeoFeatureReader;
import io.github.agentsoz.syntheticpop.util.ConfigProperties;
import io.github.agentsoz.syntheticpop.util.GlobalConstants;
import io.github.agentsoz.syntheticpop.util.Log;
import org.geotools.data.FeatureSource;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.filter.text.cql2.CQLException;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

/**
 * @author wniroshan 01 May 2018
 */
public class Household2AddressMapper {

    private final Path householdsHome;
    private final String householdFilePattern;
    private final ShapefileGeoFeatureReader shapesReader;
    private final Path updatedAddressShapesFile;
    private final String addressShapeFilePattern;
    private final String saAttributeName;
    private final String sa1ToLgaMapJsonName;
    private List<String> saList;
    private Random rand;


    Household2AddressMapper(ConfigProperties props, ShapefileGeoFeatureReader shapesReader) {
        this.shapesReader = shapesReader;
        householdsHome = props.readFileOrDirectoryPath("PopulationHomeDirectory");
        updatedAddressShapesFile = props.readFileOrDirectoryPath("UpdatedAddressShapeFile");
        householdFilePattern = props.getProperty("HouseholdFileNamePattern");
        addressShapeFilePattern = props.getProperty("AddressesShapeFileNamePattern");
        saAttributeName = props.readKeyValuePairs("NewAttributesToAddress").keySet().stream().findFirst().orElse(null);
        sa1ToLgaMapJsonName = props.getProperty("SA1toAddressesFileMapJsonName");
        try {
            saList = props.getSAList("SAList", householdsHome);
        } catch (IOException e) {
            Log.errorAndExit("Reading SA list failed", e, GlobalConstants.ExitCode.USERINPUT);
        }
        rand = new Random(Integer.parseInt(props.getProperty("RandomSeed")));
    }

    void assignHouseholdsToAddresses() {
        Map<String, Set<String>> sa1ToLgaMap = getSA1ToLgaMap(updatedAddressShapesFile);
        for (String sa : saList) {
            Path hhFile = FileUtils.find(householdsHome.resolve(sa), householdFilePattern).get(0);
            Reader r = null;
            Map<String, List<LinkedHashMap<String, Object>>> hhsBySA1 = null;
            try {
                r = new InputStreamReader(new GZIPInputStream(new BufferedInputStream(Files.newInputStream(hhFile))));
                hhsBySA1 = HouseholdUtil.readHouseholds(r);
            } catch (IOException e) {
                Log.errorAndExit("Failed to read  " + hhFile, e, GlobalConstants.ExitCode.USERINPUT);
            }

            for (String sa1 : hhsBySA1.keySet()) {
                Set<String> lgaNames = sa1ToLgaMap.get(sa1);
                if (lgaNames != null) {
                    List<Address> addressesOfSA1 = loadAddressesBySA(lgaNames, sa1);
                    allocateHouseholdsToAddresses(hhsBySA1.get(sa1), addressesOfSA1, rand);
                } else {
                    Log.debug("LGA of SA1:" + sa1 + " is not found in data");
                }
            }

            try {
                CSVWriter csvWriter = new CSVWriter();
                List<LinkedHashMap<String, Object>> households = hhsBySA1.values()
                                                                         .stream()
                                                                         .flatMap(List::stream)
                                                                         .collect(Collectors.toList());
                csvWriter.writeLinkedMapAsCsv(Files.newBufferedWriter(hhFile), households);
            } catch (IOException e) {
                Log.errorAndExit("Failed to write " + hhFile, e, GlobalConstants.ExitCode.IOERROR);
            }


        }
    }

    private List<Address> loadAddressesBySA(Collection<String> lgas, String saCode) {
        List<Address> saAddresses = new ArrayList<>();
        for (String lga : lgas) {
            try {
                URI lgaShapesUri = URI.create("jar:file:" + updatedAddressShapesFile.toAbsolutePath() + "!" + File.separator + lga + File
                        .separator + addressShapeFilePattern);
                FeatureSource<SimpleFeatureType, SimpleFeature> ftSrc = shapesReader.getFeatureSource(lgaShapesUri.toURL());

                saAddresses.addAll(AddressUtil.map2POJO((SimpleFeatureCollection) shapesReader.loadFeaturesByProperty(ftSrc,
                                                                                                                      saAttributeName,
                                                                                                                      saCode)));

            } catch (IOException | CQLException e) {
                Log.errorAndExit("Failed to read " + addressShapeFilePattern + " file in " + updatedAddressShapesFile,
                                 e,
                                 GlobalConstants.ExitCode.IOERROR);
            }
        }

        return saAddresses;
    }

    private void allocateHouseholdsToAddresses(List<LinkedHashMap<String, Object>> households, List<Address> addresses, Random rand) {
        Collections.shuffle(addresses, rand);

        int size = addresses.size();
        int hhCount = households.size();
        int aIdx = 0;
        for (int hIdx = 0; hIdx < hhCount; hIdx++, aIdx++) {
            aIdx = aIdx % size; //If we reach the last address we add multiple households to the same address
            Map<String, Object> h = households.get(hIdx);
            Address a = addresses.get(aIdx);

            h.put("EZI_ADD", a.getProperties().getEZI_ADD());
            if (a.getProperties().getHOUSEHOLD_ID() == null) {
                a.getProperties().setHOUSEHOLD_ID(new ArrayList<>(Collections.singletonList((String) h.get("HouseholdId"))));
            } else {
                a.getProperties().getHOUSEHOLD_ID().add((String) h.get("HouseholdId"));
            }
        }
    }

    private Map<String, Set<String>> getSA1ToLgaMap(Path updatedAddressShapesFile) {
        Map<String, Set<String>> jsonObject = null;
        try {
            Reader sa1ToLgaJsonReader = Zip.read(updatedAddressShapesFile, sa1ToLgaMapJsonName);
            TypeReference<Map<String, Set<String>>> typeReference = new TypeReference<Map<String, Set<String>>>() {};

            jsonObject = new ObjectMapper().readValue(sa1ToLgaJsonReader, typeReference);
        } catch (IOException e) {
            Log.errorAndExit("Failed to read " + sa1ToLgaMapJsonName + " in " + updatedAddressShapesFile,
                             e,
                             GlobalConstants.ExitCode.USERINPUT);
        }

        return jsonObject;
    }

}

