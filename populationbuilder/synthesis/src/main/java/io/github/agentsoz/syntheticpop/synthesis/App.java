package io.github.agentsoz.syntheticpop.synthesis;

import io.github.agentsoz.syntheticpop.filemanager.csv.abs.StatisticalAreaCodeReader;
import io.github.agentsoz.syntheticpop.synthesis.models.*;
import io.github.agentsoz.syntheticpop.util.ConfigProperties;
import io.github.agentsoz.syntheticpop.util.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Bhagya N. Wickramasinghe
 */
public class App {

    private static void usage() {
        System.out.println("Usage: java -jar synthesis.jar <properties file> [Options]");
        System.out.println("Options:");
        System.out.println("   -p=FLAG");
        System.out.println("       Set this flag to generate only the persons instances");
        System.exit(0);
    }

    public static void main(String[] args) {
        Log.createLogger("Synthesis", "PopulationSynthesis.log");

        //Read command line arguments
        boolean personsOnly = false;
        ConfigProperties props = null;
        if (args.length == 1) {
            try {
                props = new ConfigProperties(args[0]);
            } catch (Exception e) {
                usage();
            }
        } else if (args.length == 2) {
            if (args[1].equals("-p"))
                personsOnly = true;
            else
                usage();
        } else {
            usage();
        }

        // Read in the config properties
        Path inputDirectory = props.readFileOrDirectoryPath("InputDirectory");
        Path outputDirectory = props.readFileOrDirectoryPath("OutputDirectory");
        String sa2s = props.getProperty("SA2List");

        long randomSeed = Long.parseLong(props.getProperty("RandomSeed"));
        Path saCodesZip = props.readFileOrDirectoryPath("SACodesZip");
        String referenceColumnHeader = props.getProperty("ReferenceColumnHeader");
        String targetColumnHeader = props.getProperty("TargetColumnHeader");
        boolean enableSummaryReports = props.getProperty("EnableSummaryReports").trim().toLowerCase().equals("true");
        double nonPrimaryCoupleWithChildProbability = Double.parseDouble(props.getProperty(
                "NonPrimaryCoupleWithChildProbability"));
        Map<String, String> ageDistributionParams = props.readKeyValuePairs("AgeDistributionFile");

        Map<String, String> sa1HhDistCsvProperties = props.readKeyValuePairs("SA1HhDistFileProperties");

        long startTime = System.currentTimeMillis();

        try {
            List<String> sa2List = getSA2List(sa2s, inputDirectory);
            Map<String, String> sa2CodeMap = StatisticalAreaCodeReader.loadCsvAndCreateMapWithAreaCode(saCodesZip,
                                                                                                       referenceColumnHeader,
                                                                                                       targetColumnHeader);
            Random rand = new Random(randomSeed);

            //Read exact age distributions
            Map<String, List<Double>> ageDistribution = DataReader.readAgeDistribution(ageDistributionParams);

            for (String sa2 : sa2List) {
                sa2 = sa2.trim();
                Log.info("Starting SA2: " + sa2);
                Path hhFile = Paths.get(inputDirectory + File.separator + sa2 + File
                        .separator + "preprocessed/household_types.csv.gz");
                Path indFile = Paths.get(inputDirectory + File.separator + sa2 + File
                        .separator + "preprocessed/person_types.csv.gz");

                // Read input CSVs
                Map<String, List<HhRecord>> hhRecs = DataReader.readHouseholdRecords(hhFile);
                Map<String, List<IndRecord>> indRecs = DataReader.readPersonRecords(indFile);

                if (hhRecs.isEmpty() || indRecs.isEmpty()) {
                    Log.warn("Skipping " + sa2 + ": No data");
                    continue;
                }

                // Group persons into households considering person, household and family types
                PopulationFactory populationFactory = new PopulationFactory(hhRecs.get(sa2),
                                                                            indRecs.get(sa2),
                                                                            nonPrimaryCoupleWithChildProbability,
                                                                            rand);
                if (personsOnly) {
                    List<Person> personsOfSA2 = populationFactory.makeAllPersons();
                    PersonPropertiesHandler.assignAge(personsOfSA2, ageDistribution.get(sa2), rand);
                    assignUniqueIDs(personsOfSA2, sa2CodeMap.get(sa2), null);
                    Log.info("Writing output files to: " + outputDirectory);
                    Path outputSA2Location = Paths.get(outputDirectory + File.separator + sa2 +
                                                               File.separator + "population");
                    DataWriter.savePersons(Paths.get(outputSA2Location + File.separator + "persons.csv.gz"),
                                           personsOfSA2);
                    if (enableSummaryReports) {

                        DataWriter.savePersonsSummary(indRecs.get(sa2),
                                                      personsOfSA2,
                                                      Paths.get(outputSA2Location + File.separator + "output_person_types" +
                                                                        ".csv.gz"));

                    }

                } else {
                    List<Household> householdsOfSA2 = populationFactory.makePopulation();
                    List<Person> personsOfSA2 = householdsOfSA2.parallelStream()
                                                               .map(Household::getMembers)
                                                               .flatMap(List::stream)
                                                               .collect(Collectors.toList());

                    // Link the persons in each household
                    PersonPropertiesHandler.buildRelationships(householdsOfSA2);

                    // Assign actual ages to persons based on their age category and
                    // overall age distribution
                    PersonPropertiesHandler.assignAge(personsOfSA2, ageDistribution.get(sa2), rand);

                    assignUniqueIDs(householdsOfSA2, sa2CodeMap);
                    //                assignSA1sToHouseholds(sa2,
                    //                                       sa1HhDistCsvProperties,
                    //                                       inputDirectory,
                    //                                       householdsOfSA2,
                    //                                       rand);

                    Log.info("Writing output files to: " + outputDirectory);
                    Path outputSA2Location = Paths.get(outputDirectory + File.separator + sa2 +
                                                               File.separator + "population");
                    Files.createDirectories(outputSA2Location);
                    DataWriter.saveHouseholds(Paths.get(outputSA2Location + File.separator + "households.csv.gz"),
                                              householdsOfSA2);
                    DataWriter.saveFamilies(Paths.get(outputSA2Location + File.separator + "families.csv.gz"),
                                            householdsOfSA2);
                    DataWriter.savePersons(Paths.get(outputSA2Location + File.separator + "persons.csv.gz"),
                                           personsOfSA2);
                    if (enableSummaryReports) {

                        DataWriter.saveHouseholdSummary(hhRecs.get(sa2),
                                                        householdsOfSA2,
                                                        Paths.get(outputSA2Location + File.separator + "output_household_types.csv.gz"));
                        DataWriter.savePersonsSummary(indRecs.get(sa2),
                                                      personsOfSA2,
                                                      Paths.get(outputSA2Location + File.separator + "output_person_types.csv.gz"));

                    }
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        double timeSpent = (System.currentTimeMillis() - startTime) / (double) 1000;
        Log.info("Done!");
        Log.info("Execution time: " + timeSpent + " secs");
    }

    /**
     * Updates properties in all persons, families and households based on SA2 main code.
     *
     * @param allHouseholds Households in the population
     * @param sa2CodeMap    The map of SA2 names and codes
     */
    private static void assignUniqueIDs(List<Household> allHouseholds, Map<String, String> sa2CodeMap) {
        for (Household h : allHouseholds) {
            String sa2MainCode = sa2CodeMap.get(h.getSA2Name());
            h.setSA2MainCode(sa2MainCode);
            h.setID(sa2MainCode + "H" + h.getID());
            for (Family f : h.getFamilies()) {
                f.setID(sa2MainCode + "F" + f.getID());
                assignUniqueIDs(f.getMembers(), sa2MainCode, f.getID());
            }
        }
    }

    private static void assignUniqueIDs(List<Person> persons, String sa2MainCode, String familyId) {
        for (Person p : persons) {
            p.setFamilyID(familyId);
            p.setSA2MainCode(sa2MainCode);
            p.setID(sa2MainCode + "P" + p.getID());
        }
    }

    private static void assignSA1sToHouseholds(String thisSA2,
                                               Map<String, String> sa1HhDistCsvProperties,
                                               Path inputLocation,
                                               List<Household> householdsOfSA2,
                                               Random rand) throws IOException {
        Path sa1HouseholdsFile = Paths.get(inputLocation + File.separator + thisSA2 + File
                .separator + "preprocessed" + File.separator +
                                                   sa1HhDistCsvProperties.get("FileName"));
        int numberOfPersonsColumn = Integer.parseInt(sa1HhDistCsvProperties.get("NumberOfPersonsColumn"));
        int familyHouseholdTypeColumn = Integer.parseInt(sa1HhDistCsvProperties.get("FamilyHouseholdTypeColumn"));

        Map<String, Map<String, Integer>> sa1HhCounts = DataReader.readSA1HouseholdDistribution(sa1HouseholdsFile,
                                                                                                numberOfPersonsColumn,
                                                                                                familyHouseholdTypeColumn);
        Map<String, List<Household>> hhsByType = HouseholdSummary.groupHouseholdsByHouseholdType(householdsOfSA2);

        SA1PopulationMaker.distributePopulationToSA1s(sa1HhCounts, hhsByType, rand);
    }

    /**
     * Reads SA2 names list from user input
     *
     * @param sa2Param       SA2list property
     * @param inputDirectory The input data directory
     * @return List of SA2s
     * @throws IOException File reading
     */
    private static List<String> getSA2List(String sa2Param,
                                           Path inputDirectory) throws IOException {
        List<String> sa2List = null;
        if (sa2Param.equals("*")) {
            sa2List = Files.list(inputDirectory)
                           .map(Path::getFileName)
                           .map(Path::toString)
                           .collect(Collectors.toList());
        } else if (Files.exists(Paths.get(sa2Param))) {
            sa2List = Files.readAllLines(Paths.get(sa2Param));
        } else {
            sa2List = Arrays.asList(sa2Param.split(","));
        }
        return sa2List;
    }

}

