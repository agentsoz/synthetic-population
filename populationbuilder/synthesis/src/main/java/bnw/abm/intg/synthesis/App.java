package bnw.abm.intg.synthesis;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import bnw.abm.intg.filemanager.csv.abs.ABSStatisticalAreaCodeConverter;
import bnw.abm.intg.synthesis.models.Household;
import bnw.abm.intg.util.BNWProperties;
import bnw.abm.intg.util.Log;

/**
 * @author Bhagya N. Wickramasinghe
 *
 */
public class App {

    public static void main(String[] args) {
        Log.createLogger("Synthesis","PopulationSynthesis.log");
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
        Path sa1OutputDirectory = props.readFileOrDirectoryPath("SA1OutputLocation");
        Path sa2InputDirectory = props.readFileOrDirectoryPath("SA2InputDataDirectory");
        Map<String, String> sa1HhDistCsvProperties = props.readKeyValuePairs("SA1HhDistFileProperties");
        long randomSeed = Long.parseLong(props.getProperty("RandomSeed"));
        Path saCodesZip = props.readFileOrDirectoryPath("SACodesZip");
        String SACodesCsvInSACodesZip = props.getProperty("SACodesCsvInSACodesZip");
        String ReferenceColumnHeader = props.getProperty("ReferenceColumnHeader");
        String TargetColumnHeader = props.getProperty("TargetColumnHeader");
        boolean enableSummaryReports = props.getProperty("EnableSummaryReports").trim().toLowerCase().equals("true");
        double sexRatio = Double.parseDouble(props.getProperty("SexRatio"));
        double relativesProbability = Double.parseDouble(props.getProperty("RelativesProbability"));
        double femaleLoneParentProbability = Double.parseDouble(props.getProperty("FemaleLoneParentProbability"));
        Map<String, String> ageDistributionParams = props.readKeyValuePairs("AgeDistributionFile");

        long startTime = System.currentTimeMillis();
        try {
            Random rand = new Random(randomSeed);
            List<Household> allHouseholds = new ArrayList<>();

            for (String sa2 : sa2List) {
                Log.info("Starting SA2: " + sa2);
                Path hhfileinfo = Paths.get(sa2InputDirectory + File.separator + sa2 + File.separator + "Hh.csv");
                Path indfileinfo = Paths.get(sa2InputDirectory + File.separator + sa2 + File.separator + "Indiv.csv");

                GroupMaker grpmaker = new GroupMaker(sexRatio, relativesProbability, femaleLoneParentProbability);

                /* Data fields */
                Map<String, List<HhRecord>> hhrecs = null;
                Map<String, List<IndRecord>> indrec = null;

                // Read input CSVs
                hhrecs = DataReader.readHouseholdRecords(hhfileinfo);
                indrec = DataReader.readPersonRecords(indfileinfo);

                // Build all the households (bar relationships between
                // individuals in the HH)
                List<Household> householdsOfSA2 = grpmaker.makePopulation(hhrecs.get(sa2), indrec.get(sa2), rand, sa2);

                // Link up all the persons in each household
                PersonPropertiesHandler.buildRelationships(householdsOfSA2, rand);

                // Read in populations overall age distribution
                Map<Integer, Double> ageDistribution = DataReader.readAgeDistribution(ageDistributionParams);
                // Assign actual ages to persons based on their age category and
                // overall age distribution
                PersonPropertiesHandler.assignAge(householdsOfSA2, ageDistribution, rand);

                allHouseholds.addAll(householdsOfSA2);

                if (enableSummaryReports) {
                    // Create the output directory for this SA2
                    Path outlocationForThisSA2 = Paths.get(sa2OutputDirectory + File.separator + sa2 + File.separator);
                    Files.createDirectories(outlocationForThisSA2);
                    Survey.saveHouseholdSummary2csv(hhrecs.get(sa2), householdsOfSA2,
                            Paths.get(outlocationForThisSA2 + File.separator + "GroupSummary.csv"));
                    Survey.savePersonsSummary2csv(indrec.get(sa2), householdsOfSA2,
                            Paths.get(outlocationForThisSA2 + File.separator + "AgentSummary.csv"));
                    savePopulationToSA1s(sa2, sa1HhDistCsvProperties, sa2InputDirectory, sa1OutputDirectory, householdsOfSA2, rand);
                }

            }
            convertToSA2MAINCODE(allHouseholds, saCodesZip, SACodesCsvInSACodesZip, ReferenceColumnHeader, TargetColumnHeader);
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
        Log.info("Execution time: " + timeSpent);
    }

    private static void convertToSA2MAINCODE(List<Household> allHouseolds, Path zipWithCodesCsv, String csvFileInZip, String referenceColTitle,
            String targetColTitle) throws IOException {
        Map<String, String> sa2CodeMap = ABSStatisticalAreaCodeConverter.loadCsvAndCreateMapWithAreaCode(zipWithCodesCsv, csvFileInZip,
                referenceColTitle, targetColTitle);
        for (Household household : allHouseolds) {
            household.setSA2MainCode(sa2CodeMap.get(household.getSA2Name()));
        }
    }

    private static void savePopulationToSA1s(String thisSA2, Map<String, String> sa1HhDistCsvProperties, Path inputLocation,
            Path sa1outputLocation, List<Household> householdsOfSA2, Random rand) throws IOException {
        Path sa1HouseholdsFile = Paths.get(inputLocation + File.separator + thisSA2 + File.separator + sa1HhDistCsvProperties.get("FileName"));
        int sa1Row = Integer.parseInt(sa1HhDistCsvProperties.get("SA1Row"));
        int numberOfPersonsColumn = Integer.parseInt(sa1HhDistCsvProperties.get("NumberOfPersonsColumn"));
        int familyHouseholdTypeColumn = Integer.parseInt(sa1HhDistCsvProperties.get("FamilyHouseholdTypeColumn"));
        Map<String, Map<String, Integer>> sa1HouseholdCounts = null;
        Map<String, List<Household>> householdsByType = null;

        sa1HouseholdCounts = DataReader.readSA1HouseholdDistribution(sa1HouseholdsFile, sa1Row, numberOfPersonsColumn,
                familyHouseholdTypeColumn);// FIXME: add relatives back
        householdsByType = Survey.groupHouseholdsByHouseholdType(householdsOfSA2);

        SA1PopulationMaker sa1popMaker = new SA1PopulationMaker(rand);
        Map<String, List<Household>> householdsBySA1 = sa1popMaker.distributePopulationToSA1s(sa1HouseholdCounts, householdsByType, rand);
        // sa1popMaker.assignTENLLDtoHouseholds(tenlldLocation, tenlldFile, householdsBySA1);
        Survey.saveSA1Households(sa1outputLocation, householdsBySA1);
        Survey.saveSA1Families(sa1outputLocation, householdsBySA1);
        Survey.saveSA1Persons(sa1outputLocation, householdsBySA1);
    }
}
