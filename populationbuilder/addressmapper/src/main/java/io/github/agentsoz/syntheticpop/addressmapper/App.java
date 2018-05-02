package io.github.agentsoz.syntheticpop.addressmapper;

import io.github.agentsoz.syntheticpop.geo.FeatureProcessor;
import io.github.agentsoz.syntheticpop.geo.ShapefileGeoFeatureReader;
import io.github.agentsoz.syntheticpop.util.ConfigProperties;
import io.github.agentsoz.syntheticpop.util.GlobalConstants;
import io.github.agentsoz.syntheticpop.util.Log;

/**
 * @author wniroshan 16 Apr 2018
 */
public class App {

    private static void usage() {
        System.out.println("Usage: java -jar addressmapper.jar <properties file> [Options]");
        System.out.println(
                "This program maps mesh blocks and the corresponding SA1s in shape files obtained from Australian Bureau of Statistics to" +
                        " the addresses obtained from Vicmaps\n");
        System.out.println("Options:");
        System.out.println("   -s=BOOLEAN");
        System.out.println("       Set this flag to map addresses to SA1s [Default = fales]");
        System.out.println("   -h=BOOLEAN");
        System.out.println("       Set this flag to map households to addresses [Default = false]");
        System.exit(0);
    }

    public static void main(String args[]) {
        System.out.println("Starting addresses mapping");
        Log.createLogger("AddressMapper", "AddressMapper.log");

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

        FeatureProcessor featProcessor = new FeatureProcessor();
        ShapefileGeoFeatureReader shapesReader = new ShapefileGeoFeatureReader();

        assert props != null;
        if (mapAddressToSA1s) {
            StatisticalArea2AddressMapper saAdrMapper = new StatisticalArea2AddressMapper(props, featProcessor, shapesReader);
            saAdrMapper.mapAddressesToStatisticalAreas();
        }

        if (mapHouseholdsToAddresses) {
            Household2AddressMapper hh2AddressMapper = new Household2AddressMapper(props, shapesReader);
            try {
                hh2AddressMapper.assignHouseholdsToAddresses();
            } catch (Exception e) {
                Log.errorAndExit("Mapping households to addresses failed", e, GlobalConstants.ExitCode.UNDEF);
            }
        }
    }


}
