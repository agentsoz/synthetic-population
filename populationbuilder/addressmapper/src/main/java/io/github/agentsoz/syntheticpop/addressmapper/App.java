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

import io.github.agentsoz.syntheticpop.geo.FeatureProcessor;
import io.github.agentsoz.syntheticpop.geo.ShapefileGeoFeatureReader;
import io.github.agentsoz.syntheticpop.util.ConfigProperties;
import io.github.agentsoz.syntheticpop.util.GlobalConstants;
import io.github.agentsoz.syntheticpop.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wniroshan 16 Apr 2018
 */
public class App {

    private static void usage() {
        System.out.println("Usage: java -jar addressmapper.jar <properties file> [Options]");
        System.out.println(
                "This program maps SA1s in shape files obtained from Australian Bureau of Statistics to the addresses obtained from " +
                        "Vicmaps and assignes households to addresses.\n");
        System.out.println("Options:");
        System.out.println("   -s=BOOLEAN");
        System.out.println("       Set this flag to map addresses to SAs [Default = false]");
        System.out.println("   -h=BOOLEAN");
        System.out.println(
                "       Set this flag to map households to addresses [Default = false]. The program requires having the addresses already" +
                        " mapped to SA1s (the output of -s step) to run this step.");
        System.exit(0);
    }

    public static void main(String args[]) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd:MM:yy-HH:mm:ss");
        Date date = new Date();
        Log.createLogger("AddressMapper", "AddressMapper" + formatter.format(date) + ".log");
        Log.info("Starting addresses mapper");

        boolean mapAddressToSA1s = false, mapHouseholdsToAddresses = false;
        try {
            if (args[1].contains("s")) {
                mapAddressToSA1s = true;
            }
            if (args[1].contains("h")) {
                mapHouseholdsToAddresses = true;
            }
            if (!(mapAddressToSA1s || mapHouseholdsToAddresses)) {
                usage();
            }
        } catch (Exception e) {
            usage();
        }

        // Read all the properties
        ConfigProperties props = null;
        try {
            props = new ConfigProperties(args[0]);
        } catch (Exception e) {
            Log.error("When reading properties file", e);
            usage();
        }


        //Map addresses to SA polygons
        assert props != null;
        if (mapAddressToSA1s) {
            Log.info("Mapping addresses to SA1s");
            long startTime = System.currentTimeMillis();

            FeatureProcessor featProcessor = new FeatureProcessor();
            ShapefileGeoFeatureReader shapesReader = new ShapefileGeoFeatureReader();

            StatisticalArea2AddressMapper saAdrMapper = new StatisticalArea2AddressMapper(props,
                                                                                          new ShapesProcessor(featProcessor, shapesReader));
            saAdrMapper.mapAddressesToStatisticalAreas();

            double timeSpent = (System.currentTimeMillis() - startTime) / (double) 1000;
            Log.info("Complete");
            Log.info("Execution time: " + timeSpent + " secs");
        }

        //Assign households to addresses based on the SA.
        if (mapHouseholdsToAddresses) {
            Log.info("Mapping households to addresses");
            long startTime = System.currentTimeMillis();

            Household2AddressMapper hh2AddressMapper = new Household2AddressMapper(props);
            try {
                hh2AddressMapper.assignHouseholdsToAddresses();
            } catch (Exception e) {
                Log.errorAndExit("Mapping households to addresses failed", e, GlobalConstants.ExitCode.UNDEF);
            }

            double timeSpent = (System.currentTimeMillis() - startTime) / (double) 1000;
            Log.info("Complete");
            Log.info("Execution time: " + timeSpent + " secs");
        }
    }


}
