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
        String[] sa2List = props.readCommaSepProperties("SA2List");
        Path sa2OutputDirectory = props.readFileOrDirectoryPath("SA2OutputLocation");
        Path allHouseholdsCsv = props.readFileOrDirectoryPath("AllHouseholdsCsv");
        Path allFamiliesCsv = props.readFileOrDirectoryPath("AllFamiliesCsv");
        Path allAgentsCsv = props.readFileOrDirectoryPath("AllAgentsCsv");
        Path sa2InputDirectory = props.readFileOrDirectoryPath("SA2InputDataDirectory");
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

        long startTime = System.currentTimeMillis();
        try {
            Random rand = new Random(randomSeed);
            List<Household> allHouseholds = new ArrayList<>();

            for (String sa2 : sa2List) {
                Log.info("Starting SA2: " + sa2);
                Path hhFile = Paths.get(sa2InputDirectory + File.separator + sa2 + File.separator + "Hh.csv");
                Path indFile = Paths.get(sa2InputDirectory + File.separator + sa2 + File.separator + "Indiv.csv");

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

                allHouseholds.addAll(householdsOfSA2);

                if (enableSummaryReports) {
                    // Create the output directory for this SA2
                    Path outputLocationForThisSA2 = Paths.get(sa2OutputDirectory + File.separator + sa2 + File
                            .separator);
                    Files.createDirectories(outputLocationForThisSA2);
                    Survey.saveHouseholdSummary2csv(hhRecs.get(sa2),
                                                    householdsOfSA2,
                                                    Paths.get(outputLocationForThisSA2 + File.separator +
                                                                      "GroupSummary.csv"));
                    Survey.savePersonsSummary2csv(indRecs.get(sa2),
                                                  householdsOfSA2,
                                                  Paths.get(outputLocationForThisSA2 + File.separator + "AgentSummary" +
                                                                    ".csv"));
                }

            }
            convertToSA2MAINCODE(allHouseholds,
                                 saCodesZip,
                                 saCodesFileInSACodesZip,
                                 referenceColumnHeader,
                                 targetColumnHeader);

            Log.info("Writing output files to: " + allHouseholdsCsv.getParent());
            Files.createDirectories(allHouseholdsCsv.getParent());
            Survey.saveAllHouseholds(allHouseholdsCsv, allHouseholds);
            Survey.saveAllFamilies(allFamiliesCsv, allHouseholds);
            Survey.saveAllPersons(allAgentsCsv, allHouseholds);

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
}
