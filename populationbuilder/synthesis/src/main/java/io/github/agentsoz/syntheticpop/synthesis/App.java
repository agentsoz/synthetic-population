package io.github.agentsoz.syntheticpop.synthesis;

/*-
 * #%L
 * Synthetic Population Construction for Australia
 * %%
 * Copyright (C) 2016 - 2019 by its authors. See AUTHORS file.
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

import io.github.agentsoz.syntheticpop.filemanager.csv.abs.StatisticalAreaCodeReader;
import io.github.agentsoz.syntheticpop.synthesis.models.*;
import io.github.agentsoz.syntheticpop.util.ConfigProperties;
import io.github.agentsoz.syntheticpop.util.GlobalConstants;
import io.github.agentsoz.syntheticpop.util.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
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
        System.out.println(
                "This program generates the synthetic population for the specified Australian census SA2s in the properties file\n");
        System.out.println("Options:");
        System.out.println("   -p=BOOLEAN");
        System.out.println("       Set True to generate only persons instances");
        System.exit(0);
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Log.createLogger("Synthesis", "PopulationSynthesis.log");

        //Read command line arguments
        boolean personsOnly = false;
        if (args.length > 1) {
            if (args[1].equals("-p")) {
                personsOnly = true;
            } else {
                usage();
            }
        }
        ConfigProperties props = null;
        try {
            props = new ConfigProperties(args[0]);
        } catch (Exception e) {
            usage();
        }

        // Read in the config properties
        Path inputDirectory = props.readFileOrDirectoryPath("InputDirectory");
        Path outputDirectory = props.readFileOrDirectoryPath("OutputDirectory");
        Path analysisDirectory = props.readFileOrDirectoryPath("AnalysisDirectory");

        long randomSeed = Long.parseLong(props.getProperty("RandomSeed"));
        Path saCodesZip = props.readFileOrDirectoryPath("SACodesZip");
        String saReferenceColumnHeader = props.getProperty("SAReferenceColumnHeader");
        String saTargetColumnHeader = props.getProperty("SATargetColumnHeader");
        boolean enableSummaryReports = props.getProperty("EnableSummaryReports").trim().toLowerCase().equals("true");
        double nonPrimaryCoupleWithChildProbability = Double.parseDouble(props.getProperty(
                "NonPrimaryCoupleWithChildProbability"));
        Map<String, String> ageDistributionParams = props.readKeyValuePairs("AgeDistributionFile");

        boolean doSA1 = props.getProperty("DoSA1").trim().toLowerCase().equals("true");

        Map<String, String> sa1HhDistCsvProperties = null;

        if (doSA1) {
            sa1HhDistCsvProperties = props.readKeyValuePairs("SA1HhDistFileProperties");
        }


        try {
            List<String> saList = null;
            try {
                saList = props.getSAList("SAList", inputDirectory);

            } catch (IOException e) {
                Log.errorAndExit("Cannot read SA list from " + System.getProperty("user.dir") + File.separator + props.getProperty("SAList") + " file.",
                                 GlobalConstants.ExitCode.USERINPUT);
            }

            Map<String, String> sa2CodeMap = null;
            try {
                sa2CodeMap = StatisticalAreaCodeReader.loadCsvAndCreateMapWithAreaCode(saCodesZip,
                                                                                       saReferenceColumnHeader,
                                                                                       saTargetColumnHeader);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Random rand = new Random(randomSeed);

            //Read exact age distributions
            Map<String, List<Double>> ageDistribution = DataReader.readAgeDistribution(ageDistributionParams);

            //Map for parent child age gap histogram and populate it with initial 0
            Map<Integer, Integer> parentalAgeGapHistogram = new LinkedHashMap<>();
            for (int i = 0; i < 115; i++) {
                parentalAgeGapHistogram.put(i, 0);
            }

            Map<String, Integer> randomAgeAssignments = new LinkedHashMap<>();
            for (String sa : saList) {
                sa = sa.trim();
                Log.info("Starting: " + sa);
                Path hhFile = Paths.get(inputDirectory + File.separator + sa + File.separator + "preprocessed/household_types.csv.gz");
                Path indFile = Paths.get(inputDirectory + File.separator + sa + File.separator + "preprocessed/person_types.csv.gz");

                // Read input CSVs
                Map<String, List<HhRecord>> hhRecs = DataReader.readHouseholdRecords(hhFile);
                Map<String, List<IndRecord>> indRecs = DataReader.readPersonRecords(indFile);

                if (hhRecs.isEmpty() || indRecs.isEmpty()) {
                    Log.warn("Skipping " + sa + ": No data");
                    continue;
                }

                // Group persons into households considering person, household and family types
                PopulationFactory populationFactory = new PopulationFactory(hhRecs.get(sa),
                                                                            indRecs.get(sa),
                                                                            nonPrimaryCoupleWithChildProbability,
                                                                            rand);

                if (personsOnly) {
                    List<Person> personsOfSA2 = populationFactory.makeAllPersons();
                    int randomAgePersons = PersonPropertiesHandler.assignAge(personsOfSA2, ageDistribution.get(sa), rand);
                    randomAgeAssignments.put(sa, randomAgePersons);

                    assignUniqueIDs(personsOfSA2, sa2CodeMap.get(sa), null);
                    Log.info("Writing output files to: " + outputDirectory);
                    Path outputSA2Location = Paths.get(outputDirectory + File.separator + sa +
                                                               File.separator + "population");
                    DataWriter.savePersons(Paths.get(outputSA2Location + File.separator + "persons.csv.gz"),
                                           personsOfSA2);
                    if (enableSummaryReports) {

                        DataWriter.savePersonsSummary(indRecs.get(sa),
                                                      personsOfSA2,
                                                      Paths.get(outputSA2Location + File.separator + "output_person_types.csv.gz"));

                    }

                } else {
                    List<Household> householdsOfSA2 = populationFactory.makePopulation();
                    Log.info(sa + " household construction complete");

                    // Link the persons in each household
                    PersonPropertiesHandler.buildRelationships(householdsOfSA2);

                    // Assign actual ages to persons based on their age category and
                    // overall age distribution
                    Log.info("Assigning ages");
                    List<Person> personsOfSA2 = householdsOfSA2.parallelStream()
                                                               .map(Household::getMembers)
                                                               .flatMap(List::stream)
                                                               .collect(Collectors.toList());
                    int randomAgePersons = PersonPropertiesHandler.assignAge(personsOfSA2, ageDistribution.get(sa), rand);
                    if (randomAgePersons > 0) {
                        //Track SAs that have persons that were assigned random ages.
                        randomAgeAssignments.put(sa, randomAgePersons);
                    }
                    recordAgeGapDistribution(householdsOfSA2, parentalAgeGapHistogram);

                    Log.info("Generating unique IDs");
                    assignUniqueIDs(householdsOfSA2, sa2CodeMap);

                    if (doSA1) {
                        Log.info("Assigning households to SA1s");
                        assignHouseholdsToSA1s(sa,
                                               sa1HhDistCsvProperties,
                                               inputDirectory,
                                               householdsOfSA2,
                                               rand);
                    }

                    Path outputSA2Location = Paths.get(outputDirectory + File.separator + sa + File.separator + "population");
                    Log.info("Writing output files to: " + outputSA2Location);
                    Files.createDirectories(outputSA2Location);
                    DataWriter.saveHouseholds(Paths.get(outputSA2Location + File.separator + "households.csv.gz"), householdsOfSA2);
                    DataWriter.saveFamilies(Paths.get(outputSA2Location + File.separator + "families.csv.gz"), householdsOfSA2);
                    DataWriter.savePersons(Paths.get(outputSA2Location + File.separator + "persons.csv.gz"), personsOfSA2);

                    if (enableSummaryReports) {
                        DataWriter.saveHouseholdSummary(hhRecs.get(sa),
                                                        householdsOfSA2,
                                                        Paths.get(outputSA2Location + File.separator + "output_household_types.csv.gz"));
                        DataWriter.savePersonsSummary(indRecs.get(sa),
                                                      personsOfSA2,
                                                      Paths.get(outputSA2Location + File.separator + "output_person_types.csv.gz"));
                    }
                }


            }
            Log.info("Writing analysis data files to: " + analysisDirectory);
            DataWriter.saveParentChildAgeGapSummary(Paths.get(analysisDirectory + File.separator + "parent_child_age_gap.csv"),
                                                    parentalAgeGapHistogram);
            if (!randomAgeAssignments.isEmpty()) {
                DataWriter.saveRandomAgeAssignedPersons(Paths.get(analysisDirectory + File.separator + "age_randomly_assigned_persons.csv"),
                                                        randomAgeAssignments);
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

    private static void assignHouseholdsToSA1s(String thisSA2,
                                               Map<String, String> sa1HhDistCsvProperties,
                                               Path inputLocation,
                                               List<Household> householdsOfSA2,
                                               Random rand) throws IOException {
        Path sa1HouseholdsFile = Paths.get(inputLocation + File.separator + thisSA2 + File
                .separator + "preprocessed" + File.separator + sa1HhDistCsvProperties.get("FileName"));
        int numberOfPersonsColumn = Integer.parseInt(sa1HhDistCsvProperties.get("NumberOfPersonsColumn"));
        int familyHouseholdTypeColumn = Integer.parseInt(sa1HhDistCsvProperties.get("FamilyHouseholdTypeColumn"));

        Map<String, Map<String, Integer>> sa1HhCounts = DataReader.readSA1HouseholdDistribution(sa1HouseholdsFile,
                                                                                                numberOfPersonsColumn,
                                                                                                familyHouseholdTypeColumn);
        Map<String, List<Household>> hhsByType = HouseholdSummary.groupHouseholdsByHouseholdType(householdsOfSA2);

        SA1PopulationMaker.distributePopulationToSA1s(sa1HhCounts, hhsByType, rand);
    }

    private static void recordAgeGapDistribution(List<Household> householdsOfSA2, Map<Integer, Integer> parentalAgeGapHistogram) {
        for (Household h : householdsOfSA2) {
            for (Person m : h.getMembers()) {
                if (m.isChild()) {
                    if (m.getFather() != null) {
                        int ageGapDad = m.getFather().getAge() - m.getAge();
                        parentalAgeGapHistogram.compute(ageGapDad, (k, v) -> v + 1);
                    }
                    if (m.getMother() != null) {
                        int ageGapMom = m.getMother().getAge() - m.getAge();
                        parentalAgeGapHistogram.compute(ageGapMom, (k, v) -> v + 1);
                    }
                }
            }
        }
    }

}

