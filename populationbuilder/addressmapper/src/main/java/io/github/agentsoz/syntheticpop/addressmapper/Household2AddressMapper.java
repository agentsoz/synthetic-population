package io.github.agentsoz.syntheticpop.addressmapper;

import io.github.agentsoz.syntheticpop.addressmapper.models.Address;
import io.github.agentsoz.syntheticpop.filemanager.FileUtils;
import io.github.agentsoz.syntheticpop.util.ConfigProperties;
import io.github.agentsoz.syntheticpop.util.GlobalConstants;
import io.github.agentsoz.syntheticpop.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.zip.GZIPInputStream;

/**
 * @author wniroshan 01 May 2018
 */
public class Household2AddressMapper {

    private final Path householdsHome;
    private final String householdFilePattern;
    private final Path sa1ToAddressesJson;
    private List<String> saList;
    private Random rand;


    Household2AddressMapper(ConfigProperties props) {
        householdsHome = props.readFileOrDirectoryPath("PopulationHomeDirectory");
        householdFilePattern = props.getProperty("HouseholdFileNamePattern");
        sa1ToAddressesJson = props.readFileOrDirectoryPath("SA1toAddressesJsonFile");
        try {
            saList = props.getSAList("SAList", householdsHome);
        } catch (IOException e) {
            Log.errorAndExit("Reading SA list failed", e, GlobalConstants.ExitCode.USERINPUT);
        }
        rand = new Random(Integer.parseInt(props.getProperty("RandomSeed")));
    }

    void assignHouseholdsToAddresses() {
        Map<String, List<Address>> addresses = null;
        try {
            Log.info("Loading SA1 and Addresses JSON: " + sa1ToAddressesJson);
            addresses = AddressUtil.loadAddresses(sa1ToAddressesJson);
        } catch (IOException e) {
            Log.errorAndExit("Reading " + sa1ToAddressesJson + " failed", e, GlobalConstants.ExitCode.IOERROR);
        }

        for (String sa : saList) {
            Log.info("Processing " + sa);

            //Get the households.csv.gz of this SA2
            Path hhFile = FileUtils.find(householdsHome.resolve(sa), householdFilePattern).get(0);
            Map<String, List<LinkedHashMap<String, Object>>> hhsBySA1 = null;
            try {
                Reader hhReader = new InputStreamReader(new GZIPInputStream(new BufferedInputStream(Files.newInputStream(hhFile))));
                //Read the households and group them by SA1 main code.
                hhsBySA1 = HouseholdUtil.readHouseholds(hhReader);

            } catch (IOException e) {
                Log.errorAndExit("Failed to read  " + hhFile, e, GlobalConstants.ExitCode.USERINPUT);
            }

            assert hhsBySA1 != null;
            assert addresses != null;
            for (String sa1 : hhsBySA1.keySet()) {
                allocateHouseholdsToAddresses(hhsBySA1.get(sa1), addresses.get(sa1), rand);
            }

            //Update the households.csv.gz with EZI_ADD (street address) and Geographical location.
            //We can't save the WKT of the geographical location coordinates in the csv because it will break the structures of the csv
            try {
                HouseholdUtil.saveUpdatedHouseholds(hhsBySA1, hhFile);
            } catch (IOException e) {
                Log.errorAndExit("Failed to write " + hhFile, e, GlobalConstants.ExitCode.IOERROR);
            }


        }
    }

    private void allocateHouseholdsToAddresses(List<LinkedHashMap<String, Object>> households, List<Address> addresses, Random rand) {
        if ((addresses == null || addresses.isEmpty()) && (households != null || !households.isEmpty())) {
            Log.errorAndExit("SA: " + HouseholdUtil.getSA1MainCode(households.get(0)) + " " + households.size() + " households but 0 " +
                                     "addresses",GlobalConstants.ExitCode.DATA_ERROR);
        } else {
            //Randomly select addresses and assign households to them. If there are more households than addresses, one address may have
            // multiple households.
            Collections.shuffle(addresses, rand);

            int size = addresses.size();
            int hhCount = households.size();
            int aIdx = 0;
            for (int hIdx = 0; hIdx < hhCount; hIdx++, aIdx++) {
                aIdx = aIdx % size; //If we reach the last address we add multiple households to the same address
                Map<String, Object> h = households.get(hIdx);
                Address a = addresses.get(aIdx);

                h.put("EZI_ADD", a.getProperties().getEZI_ADD());
                h.put("PointGeometry", a.getGeometry().getCoordinates());
            }
        }
    }
}

