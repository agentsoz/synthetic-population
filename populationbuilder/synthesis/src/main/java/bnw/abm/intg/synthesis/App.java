package bnw.abm.intg.synthesis;

import bnw.abm.intg.filemanager.csv.abs.StatisticalAreaCodeReader;
import bnw.abm.intg.synthesis.models.Household;
import bnw.abm.intg.util.BNWProperties;
import bnw.abm.intg.util.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Bhagya N. Wickramasinghe
 */
public class App {

    public static void main(String[] args) {
        Log.createLogger("Synthesis", "PopulationSynthesis.log");
        BNWProperties props = null;
        try {
            props = new BNWProperties(args[0]);
        } catch (Exception e) {
            Log.info("Property file error", e);
        }

        // Read in the config properties
        Path inputDirectory = props.readFileOrDirectoryPath("InputDirectory");
        Path outputDirectory = props.readFileOrDirectoryPath("OutputDirectory");
        String[] sa2List = props.readCommaSepProperties("SA2List");

        long randomSeed = Long.parseLong(props.getProperty("RandomSeed"));
        Path saCodesZip = props.readFileOrDirectoryPath("SACodesZip");
        String saCodesFileInSACodesZip = props.getProperty("SACodesCsvInSACodesZip");
        String referenceColumnHeader = props.getProperty("ReferenceColumnHeader");
        String targetColumnHeader = props.getProperty("TargetColumnHeader");
        boolean enableSummaryReports = props.getProperty("EnableSummaryReports").trim().toLowerCase().equals("true");
        double sexRatio = Double.parseDouble(props.getProperty("SexRatio"));
        double relativesProbability = Double.parseDouble(props.getProperty("RelativesProbability"));
        double maleLoneParentProbability = Double.parseDouble(props.getProperty("MaleLoneParentProbability"));
        double nonPrimaryCoupleWithChildProbability = Double.parseDouble(props.getProperty(
                "NonPrimaryCoupleWithChildProbability"));
        Map<String, String> ageDistributionParams = props.readKeyValuePairs("AgeDistributionFile");

        Map<String, String> sa1HhDistCsvProperties = props.readKeyValuePairs("SA1HhDistFileProperties");
        Path sa1OutputDirectory = props.readFileOrDirectoryPath("SA1OutputLocation");

        long startTime = System.currentTimeMillis();
        try {
            Random rand = new Random(randomSeed);
            List<Household> allHouseholds = new ArrayList<>();

            for (String sa2 : sa2List) {
                Log.info("Starting SA2: " + sa2);
                Path hhFile = Paths.get(inputDirectory + File.separator + "SA2" + File.separator + sa2 + File
                        .separator + "preprocessed/household_types.csv.gz");
                Path indFile = Paths.get(inputDirectory + File.separator + "SA2" + File.separator + sa2 + File
                        .separator + "preprocessed/person_types.csv.gz");

                GroupMaker groupMaker = new GroupMaker(sexRatio, relativesProbability, maleLoneParentProbability);

                /* Data fields */
                Map<String, List<HhRecord>> hhRecs = null;
                Map<String, List<IndRecord>> indRecs = null;

                // Read input CSVs
                hhRecs = DataReader.readHouseholdRecords(hhFile);
                indRecs = DataReader.readPersonRecords(indFile);

                // Group persons into households considering person, household and family types
                List<Household> householdsOfSA2 = groupMaker.makePopulation(hhRecs.get(sa2),
                                                                            indRecs.get(sa2),
                                                                            rand,
                                                                            sa2,
                                                                            nonPrimaryCoupleWithChildProbability);

                // Link the persons in each household
                PersonPropertiesHandler.buildRelationships(householdsOfSA2, rand);

                // Read in populations overall age distribution
                Map<Integer, Double> ageDistribution = DataReader.readAgeDistribution(ageDistributionParams);
                // Assign actual ages to persons based on their age category and
                // overall age distribution
                PersonPropertiesHandler.assignAge(householdsOfSA2, ageDistribution, rand);

                convertToSA2MAINCODE(allHouseholds,
                                     saCodesZip,
                                     saCodesFileInSACodesZip,
                                     referenceColumnHeader,
                                     targetColumnHeader);
                assignSA1sToHouseholds(sa2,
                                       sa1HhDistCsvProperties,
                                       inputDirectory,
                                       sa1OutputDirectory,
                                       householdsOfSA2,
                                       rand);

                Log.info("Writing output files to: " + outputDirectory);
                Path outputSA2Location = Paths.get(outputDirectory + File.separator + "SA2" + File.separator + sa2 +
                                                           File.separator + "population");
                Files.createDirectories(outputSA2Location);
                DataWriter.saveHouseholds(Paths.get(outputSA2Location + File.separator + "households.csv.gz"),
                                          householdsOfSA2);
                DataWriter.saveFamilies(Paths.get(outputSA2Location + File.separator + "families.csv.gz"),
                                        householdsOfSA2);
                DataWriter.savePersons(Paths.get(outputSA2Location + File.separator + "persons.csv.gz"),
                                       householdsOfSA2);

                if (enableSummaryReports) {

                    DataWriter.saveHouseholdSummary(hhRecs.get(sa2),
                                                    householdsOfSA2,
                                                    Paths.get(outputSA2Location + File.separator +
                                                                      "output_household_types.csv.gz"));
                    DataWriter.savePersonsSummary(indRecs.get(sa2),
                                                  householdsOfSA2,
                                                  Paths.get(outputSA2Location + File.separator + "output_person_types" +
                                                                    ".csv.gz"));

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

    private static void convertToSA2MAINCODE(List<Household> allHouseholds,
                                             Path zipWithCodesCsv,
                                             String csvFileInZip,
                                             String referenceColTitle,
                                             String targetColTitle) throws IOException {
        Map<String, String> sa2CodeMap = StatisticalAreaCodeReader.loadCsvAndCreateMapWithAreaCode
                (zipWithCodesCsv, csvFileInZip, referenceColTitle, targetColTitle);
        for (Household household : allHouseholds) {
            household.setSA2MainCode(sa2CodeMap.get(household.getSA2Name()));
        }
    }

    private static void assignSA1sToHouseholds(String thisSA2,
                                               Map<String, String> sa1HhDistCsvProperties,
                                               Path inputLocation,
                                               Path sa1outputLocation,
                                               List<Household> householdsOfSA2,
                                               Random rand) throws IOException {
        Path sa1HouseholdsFile = Paths.get(inputLocation + File.separator + "SA2" + File.separator + thisSA2 + File
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
}
