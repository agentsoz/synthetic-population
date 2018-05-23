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

        int totalSA1s = 0;
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
                totalSA1s++;
            }

            //Update the households.csv.gz with EZI_ADD (street address) and Geographical location.
            //We can't save the WKT of the geographical location coordinates in the csv because it will break the structures of the csv
            try {
                HouseholdUtil.saveUpdatedHouseholds(hhsBySA1, hhFile);
            } catch (IOException e) {
                Log.errorAndExit("Failed to write " + hhFile, e, GlobalConstants.ExitCode.IOERROR);
            }
        }
        Log.info("Processed "+totalSA1s+" SA1s contained in "+saList.size()+" SA2s");
    }

    private void allocateHouseholdsToAddresses(List<LinkedHashMap<String, Object>> households, List<Address> addresses, Random rand) {
        if ((addresses == null || addresses.isEmpty()) && (households != null || !households.isEmpty())) {
            Log.warn("SA: " + HouseholdUtil.getSA1MainCode(households.get(0)) + " " + households.size() + " households but 0 addresses");
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

