package io.github.agentsoz.syntheticpop.synthesis;

import io.github.agentsoz.syntheticpop.filemanager.FileUtils;
import io.github.agentsoz.syntheticpop.filemanager.csv.CSVReader;
import io.github.agentsoz.syntheticpop.filemanager.csv.CSVWriter;
import io.github.agentsoz.syntheticpop.filemanager.json.JSONReadable;
import io.github.agentsoz.syntheticpop.filemanager.json.JSONWriter;
import io.github.agentsoz.syntheticpop.filemanager.json.JacksonJSONReader;
import io.github.agentsoz.syntheticpop.geo.CoordinateConversion;
import io.github.agentsoz.syntheticpop.geo.FeatureProcessing;
import io.github.agentsoz.syntheticpop.geo.ShapefileGeoFeatureReader;
import io.github.agentsoz.syntheticpop.util.ConfigProperties;
import io.github.agentsoz.syntheticpop.util.ConsoleProgressBar;
import io.github.agentsoz.syntheticpop.util.GlobalConstants;
import io.github.agentsoz.syntheticpop.util.Log;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import org.geotools.feature.FeatureIterator;
import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.population.Activity;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.api.core.v01.population.PopulationFactory;
import org.matsim.core.api.internal.MatsimWriter;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.scenario.ScenarioUtils;
import org.opengis.feature.Feature;
import org.opengis.feature.simple.SimpleFeature;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 */
public class Traffic {


    public static void main(String[] args) {

        Log.createLogger("Traffic", "Traffic.log");
        Traffic ap = new Traffic();
        Path addressJsonZip = null, popHome = null, rawDatHome = null, sa2Shape = null, hhAddrJson = null,
                hhOutLoc = null, trafficXml = null;
        String hHoldRegex = null, agentRegex = null;
        String[] jsonPathToSA1code = null;
        int randomSeed = 0;
        /*
         * Read properties
         */
        if (args.length > 0) {
            ConfigProperties props = null;
            try {

                props = new ConfigProperties(args[0]);
            } catch (IOException e) {
                Log.error("When reading the innertraffic.properties file", e);
            }
            popHome = props.readFileOrDirectoryPath("PopulationHome");
            hHoldRegex = props.getProperty("HholdFileRegex");
            agentRegex = props.getProperty("AgentFileRegex");
            addressJsonZip = props.readFileOrDirectoryPath("InputAddressJson");
            hhAddrJson = props.readFileOrDirectoryPath("HhMappedAddressJson");
            jsonPathToSA1code = props.getProperty("JsonPathToSA1Code").trim().split("\\\\");
            rawDatHome = props.readFileOrDirectoryPath("RawDataHome");
            sa2Shape = props.readFileOrDirectoryPath("SA2ShapeFile");
            trafficXml = props.readFileOrDirectoryPath("TrafficPlan");
            hhOutLoc = props.readFileOrDirectoryPath("UPDATEDHholdFile");
            randomSeed = Integer.parseInt(props.getProperty("RandomSeed"));
        } else {
            System.err.println("Give path to config.properties as the first argument");
        }
        /*
         * Get all household data csvs
         */
        List<Path> hHoldFiles = FileUtils.find(popHome, hHoldRegex);

        /* attribute titles in AgentsList csvs */
        String[] agentAttributes = {"AgentId", "AgentType", "PartnerId", "MotherId", "FatherId", "RelationshipStatus",
                                    "ChildrenIds", "Gender", "GroupSize", "Age", "GroupId", "CareNeedLevel",
                                    "Travel2Work",
                                    "PersonalIncome"};
        /* attribute titles in Household data csvs */
        String[] hholdAttributes = {"GroupId", "GroupType", "GroupSize", "Members", "Bedrooms", "DwellingStructure",
                                    "FamilyIncome", "Tenure&Landlord"};

        try {
            Random random = new Random(randomSeed);
            CSVReader csvr = new CSVReader();
            JSONReadable jsonR = new JacksonJSONReader();
            Map addressMap = jsonR.readJSONGz(addressJsonZip);

            // Group addresses by SA1. Addresses in addressesBySA1 still refer
            // to instances in addressMap. So any change to addressesBySA1
            // elements automatically reflected in addressMap
            Map<String, List<Map>> addressesBySA1 = ap.groupAddressesBySA1(addressMap, jsonPathToSA1code);

            Map<String, ArrayList<LinkedHashMap<String, Object>>> allAgents = new HashMap<>();
            allAgents.put("Agents", new ArrayList<>());
            Map<String, ArrayList<LinkedHashMap<String, Object>>> allHholds = new HashMap<>();
            allHholds.put("Households", new ArrayList<>());
            HashMap<String, LinkedHashMap<String, Object>> agents;
            ArrayList<LinkedHashMap<String, Object>> hHolds;

            // Initialise MATSim population construction
            Scenario matsimScenario = ScenarioUtils.createScenario(ConfigUtils.createConfig());
            PopulationFactory populationFactory = matsimScenario.getPopulation().getFactory();

            /*
             * We do several things in this loop 1. Get the Household file of
             * each SA1 and allocate a building-address (dwelling) to each
             * household 2. Record starting address geometry location as
             * starting coordinate in matsim plan
             */
            int filecount = 0;
            for (Path hHoldFile : hHoldFiles) {
                String sa1id = ap.getSA1code(hHoldFile);
                Log.info("Starting Household Data file: " + sa1id);
                final Reader hholdReader = new InputStreamReader(new FileInputStream(hHoldFile.toFile()), "UTF-8");
                hHolds = csvr.readCsvGroupByRow(hholdReader, hholdAttributes);

                allHholds.get("Households").addAll(hHolds);

                /*
                 * 1. Allocate an address to each randomly selected household in
                 * this SA1
                 */
                Collections.shuffle(hHolds);
                for (int addressIndex = 0, hhIndex = 0; hhIndex < hHolds.size(); hhIndex++, addressIndex++) {
                    if (!addressesBySA1.containsKey(sa1id)) {
                        break;
                    }
                    if (addressesBySA1.get(sa1id).size() == addressIndex) {
                        int diff = hHolds.size() - addressesBySA1.get(sa1id).size();
                        Log.warn(
                                "Not enough buildings in " + sa1id + ": Total Households:" + hHolds.size()
                                        + " Total Buildings:" + addressesBySA1.get(sa1id).size());
                        Log.warn("Not enough buildings in " + sa1id + ": " + " Assigning a second household to " +
                                         diff + " already occupied buildings");
                        addressIndex = 0; // Start assigning a second household to already occupied building.
                        // Shuffling the order of buildings in SA1.
                        Collections.shuffle(addressesBySA1.get(sa1id), random);
                    }


                    ((ArrayList) addressesBySA1.get(sa1id)
                            .get(addressIndex)
                            .computeIfAbsent("HOUSEHOLD_ID", v -> new ArrayList<>())).add(hHolds.get(hhIndex)
                                                                                                  .get("GroupId"));
                    hHolds.get(hhIndex).put("Address",
                                            ((Map) addressesBySA1.get(sa1id)
                                                    .get(addressIndex)
                                                    .get("properties")).get(
                                                    "EZI_ADD"));

                }
                ++filecount;
                Log.info("Completed Household Data file: " + sa1id);
                System.out.println("Data file: " + sa1id + "                                                       ");
                ConsoleProgressBar.updateProgress("Completed ",
                                                  filecount / (float) hHoldFiles.size(),
                                                  filecount + "/" + hHoldFiles.size() + "\r");
            }

            // Finally, write this population to file
            MatsimWriter popWriter = new org.matsim.api.core.v01.population.PopulationWriter(
                    matsimScenario.getPopulation(), matsimScenario.getNetwork());
            popWriter.write(trafficXml.toAbsolutePath().toString());

            new JSONWriter().writeToJsonGzFile(addressMap, hhAddrJson);

            CSVWriter csvWriter = new CSVWriter();
            csvWriter.writeLinkedMapAsCsv(Files.newBufferedWriter(hhOutLoc), allHholds.get("Households"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void assignTravel2WorkOriginAndDestinations(Map<String, List<Map>> addrssesBySA1,
                                                        String sa1id,
                                                        Map<String, Object> hHold,
                                                        HashMap<String, LinkedHashMap<String, Object>> agents,
                                                        List<Object> addrCoords,
                                                        Scenario matsimScenario,
                                                        Map<String, Feature> sa2map,
                                                        PopulationFactory populationFactory,
                                                        Random random) throws Exception {

        List<String> members = null;
        if (hHold.get("Members") instanceof List) {
            members = (List<String>) hHold.get("Members");
        } else if (hHold.get("Members") instanceof String) {
            members = Arrays.asList((String) hHold.get("Members"));
        } else {
            throw new Exception("Unexpected value");
        }
        for (String memId : members) {

            String travelMode = (String) agents.get(memId).get("Travel2Work");
            if (travelMode.equals("CarAsDriver")) {
                // List a = addr.get("geometry").get("coordinates");
                double easting = (double) addrCoords.get(0);
                double northing = (double) addrCoords.get(1);
                Coord startCoord = matsimScenario.createCoord(easting, northing);
                String sa2name = (String) agents.get(memId).get("Destination");
                Coord endCoord = this.getCoordinates(matsimScenario, sa2map, sa2name, random);
                Person person = this.createPersonWithPlan(populationFactory, startCoord, endCoord, memId, "car");
                if (matsimScenario.getPopulation().getPersons().containsKey(memId)) {
                    System.out.println(matsimScenario.getPopulation().getPersons().get(memId));
                }
                matsimScenario.getPopulation().addPerson(person);

            }
        }
    }

    /**
     * Group the residential addresses by SA1
     *
     * @param addressMap        The map of all addresses
     * @param jsonMatchProperty The path to SA1 code in the json file
     * @return A map of addresses by SA1 codes
     * @throws IOException When reading addresses json file
     */
    private Map<String, List<Map>> groupAddressesBySA1(Map addressMap, String[] jsonMatchProperty) throws IOException {

        /*
         * We are expecting a list of elements after root element
         */
        if (!(addressMap.get(jsonMatchProperty[0]) instanceof List)) {
            System.err.println("Root element must have an array of address elements, but this not an array");
            Log.errorAndExit("Root element must have an array of address elements, but this not an array",
                             GlobalConstants.ExitCode.USERINPUT);
        }
        List featuresList = (List) addressMap.get(jsonMatchProperty[0]);

        if (!(featuresList.get(0) instanceof Map)) {
            String errorStr = "Expecting a map after " + jsonMatchProperty[0] + "\\" + jsonMatchProperty[1]
                    + " but you have something else in addressMap";
            System.err.println(errorStr);
            Log.errorAndExit(errorStr, GlobalConstants.ExitCode.USERINPUT);
        }
        /*
         * Store all the residential addresses in JSON grouped by their SA1
         */
        Map<String, List<Map>> addrssesBySA1 = new HashMap();
        for (Map<String, Map> feature : (List<Map<String, Map>>) featuresList) {
            if (feature.get("properties").get("BUILDING_TYPE").equals("RESIDENTIAL")) {
                String said = (String) ((HashMap) feature.get(jsonMatchProperty[1])).get(jsonMatchProperty[2]);

                if (said != null) {
                    if (addrssesBySA1.containsKey(said)) {
                        addrssesBySA1.get(said).add(feature);
                    } else {
                        addrssesBySA1.put(said, new ArrayList<>(Arrays.asList(feature)));
                    }
                }
            }

        }
        return addrssesBySA1;
    }

    /**
     * Generates a coordinate for a person
     *
     * @param matsimScenario
     * @param sa2map         Map of SA2s
     * @param area           The area code
     * @return coordinate
     */
    private Coord getCoordinates(Scenario matsimScenario, Map<String, Feature> sa2map, String area, Random random) {
        FeatureProcessing fp = new FeatureProcessing();
        Feature workSA = sa2map.get(area);
        if (workSA == null) { // No SA2 for - POW Capital city undefined
            // (Greater Melbourne). Taking random SA2
            int indx = (int) Math.random() * (sa2map.size() - 1);
            area = (String) sa2map.keySet().toArray()[indx];
            workSA = sa2map.get(area);
        }
        Geometry geom = fp.getRandomPointIn(sa2map.get(area), random);
        Coordinate coord = geom.getCoordinate();
        CoordinateConversion cc = new CoordinateConversion();
        Map utm = cc.latLon2UTM(coord.y, coord.x);
        return matsimScenario.createCoord((double) utm.get("easting"), (double) utm.get("northing"));
    }

    /**
     * Gets SA1 code using Population directory names. The first directory that has a number as name is taken as SA1
     * code
     *
     * @param hHoldFile path to the file
     * @return The SA1 code
     */
    private String getSA1code(Path hHoldFile) {

        Pattern p = Pattern.compile("(?<=\\/)(.[0-9]+)(?=\\/)");

        Matcher m = p.matcher(hHoldFile.toString());
        if (m.find()) {
            return m.group(0);
        }

        return null;
    }

    /**
     * Copied from outer traffic
     *
     * @param actType
     * @return
     */
    private double activityEndTime(String actType) {
        double endTime = 0.0;
        if (actType.equals("work")) {
            /*
             * Allow people to leave work between 16.45 and 17.10
             */
            endTime = 60300 + (60 * 25 * Math.random());
            return endTime;
        }
        return 21600;
    }

    /**
     * Creates a person with a plan for MATSim
     *
     * @param populationFactory
     * @param homeCoord
     * @param workCoord
     * @param personId
     * @param travelMode
     * @return
     */
    private Person createPersonWithPlan(PopulationFactory populationFactory,
                                        Coord homeCoord,
                                        Coord workCoord,
                                        String personId,
                                        String travelMode) {

        Plan plan = populationFactory.createPlan();

        // Create a new activity with the end time and add it to the plan
        // Sleeping at home
        Activity act = populationFactory.createActivityFromCoord("home", homeCoord);
        act.setEndTime(this.activityEndTime("home"));
        plan.addActivity(act);

        // Go to work
        plan.addLeg(populationFactory.createLeg(travelMode));

        // Do work
        act = populationFactory.createActivityFromCoord("work", workCoord);
        act.setEndTime(this.activityEndTime("work"));
        plan.addActivity(act);

        // Go home
        plan.addLeg(populationFactory.createLeg(travelMode));

        // Go to sleep
        act = populationFactory.createActivityFromCoord("home", homeCoord);
        plan.addActivity(act);

        Person person = populationFactory.createPerson(Id.createPersonId(personId));
        person.addPlan(plan);
        return person;
    }

    /**
     * Read SA2 areas from shapefile to a map
     *
     * @param sa2File The shape file location
     * @param sa2list The list of SA2 areas to read
     * @return A map of SA2 id and features in each SA2
     * @throws IOException When reading shapefiles
     */
    private HashMap<String, Feature> getSA2Areas(Path sa2File, List<String> sa2list) throws IOException {

        HashMap<String, Feature> sa2map = new HashMap<>(sa2list.size());
        for (String loc : sa2list) {
            sa2map.put(loc, null);
        }

        String[] sa2names = sa2map.keySet().toArray(new String[0]);
        String property = "SA2_NAME11";
        ShapefileGeoFeatureReader geoReader = new ShapefileGeoFeatureReader();
        geoReader.loadFeaturesByProperty(sa2File, property, sa2names);

        try (FeatureIterator<Feature> featItr = geoReader.getFeatures().features()) {
            while (featItr.hasNext()) {
                SimpleFeature feature = (SimpleFeature) featItr.next();
                sa2map.put((String) feature.getAttribute("SA2_NAME11"), feature);
            }
        }
        return sa2map;
    }

}
