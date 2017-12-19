
package bnw.abm.intg.synthesis;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import bnw.abm.intg.filemanager.csv.CSVWriter;
import bnw.abm.intg.synthesis.models.*;

/**
 * @author Bhagya N. Wickramasinghe
 *
 */

public class Survey {
    private static final List<String> HOUSEHOLDS_OUTPUT_COLS = new ArrayList<>(Arrays.asList("GroupId", "PrimaryFamilyType", "Members",
            "FamilyIds", "CensusHouseholdSize", "SA2_MAINCODE_2011"));

    private static final List<String> PERSONS_OUTPUT_COLS = new ArrayList<>(Arrays.asList("AgentId", "Age", "Gender", "GroupId", "PartnerId",
            "MotherId", "FatherId", "ChildrenIds", "RelativeIds", "RelationshipStatus"));

    private static final List<String> FAMILIES_OUTPUT_COLS = new ArrayList<>(Arrays.asList("FamilyId", "FamilyType", "FamilySize", "Members",
            "HouseholdId"));

    static EnumMap<AgeRange, Integer> ageDistribution(List<IndRecord> records) {
        EnumMap<AgeRange, Integer> agedist = new EnumMap(AgeRange.class);
        for (IndRecord rec : records) {
            if (agedist.containsKey(rec.ageRange)) {
                int count = agedist.get(rec.ageRange);
                agedist.put(rec.ageRange, count + rec.indCount);
            } else {
                agedist.put(rec.ageRange, rec.indCount);
            }
        }
        return agedist;
    }

    static void saveHouseholdSummary2csvShrinked(List<Household> allHouseholds) throws IOException {

        Map<Integer, Map<String, Map<String, Integer>>> sizeLevelMap = new LinkedHashMap<>();
        for (Household household : allHouseholds) {
            Map<String, Map<String, Integer>> familyCountLevelMap;
            Map<String, Integer> primaryFamilyLevelMap;
            if (sizeLevelMap.containsKey(household.currentSize())) {
                familyCountLevelMap = sizeLevelMap.get(household.currentSize());
            } else {
                familyCountLevelMap = new LinkedHashMap<>();
                sizeLevelMap.put(household.currentSize(), familyCountLevelMap);
            }
            if (familyCountLevelMap.containsKey(household.familyCount() + " Family")) {
                primaryFamilyLevelMap = familyCountLevelMap.get(household.familyCount() + " Family");
            } else {
                primaryFamilyLevelMap = new LinkedHashMap<>();
                familyCountLevelMap.put(household.familyCount() + " Family", primaryFamilyLevelMap);
            }
            String primaryFamilyDescription = household.getFamilies().get(0).getType().name();
            if (primaryFamilyLevelMap.containsKey(primaryFamilyDescription)) {
                int hhcount = primaryFamilyLevelMap.get(primaryFamilyDescription);
                primaryFamilyLevelMap.put(primaryFamilyDescription, hhcount + 1);

            } else {
                primaryFamilyLevelMap.put(primaryFamilyDescription, 1);

            }

        }

        CSVWriter csvWriter = new CSVWriter();
        csvWriter.maptoCsv(Files.newBufferedWriter(Paths.get("TestOutput.csv")), sizeLevelMap);

    }

    static void saveHouseholdSummary2csv(List<HhRecord> hhrecs, List<Household> allHouseholds, Path csvFilePath) throws IOException {

        Map<String, List<Household>> householdsByType = groupHouseholdsByHouseholdType(allHouseholds);

        List<List<String>> fullHhSummary = new ArrayList<>();
        List<String> record1 = new ArrayList<>();
        record1.add("NofPersons");
        record1.add("NofFamilies");
        record1.add("PrimaryFamily");
        record1.add("NofHouseholds");
        fullHhSummary.add(record1);
        for (HhRecord hhrec : hhrecs) {
            List<String> record = new ArrayList<>();
            record.add(String.valueOf(hhrec.numOfPersonsPerHh));

            String nofFamilies = String.valueOf(hhrec.familyCountPerHousehold) + " Family";
            record.add(nofFamilies);

            record.add(hhrec.primaryFamilyType.description());
            int nofHhs = 0;
            List<Household> hhs = householdsByType.get(hhrec.numOfPersonsPerHh + ":" + hhrec.familyCountPerHousehold + ":"
                    + hhrec.primaryFamilyType);
            if (hhs != null) {
                nofHhs = hhs.size();
            }
            record.add(String.valueOf(nofHhs));

            fullHhSummary.add(record);
        }

        CSVWriter csvWriter = new CSVWriter();
        csvWriter.writeAsCsv(Files.newBufferedWriter(csvFilePath), fullHhSummary);
    }

    static void savePersonsSummary2csv(List<IndRecord> indrecs, List<Household> allHouseholds, Path csvFilePath) throws IOException {
        new LinkedHashMap<>();

        Map<String, Integer> map = new LinkedHashMap<>();
        for (IndRecord inrec : indrecs) {
            String key = inrec.relationshipStatus + "," + inrec.sex + "," + inrec.ageRange;
            map.put(key, 0);
        }

        for (Household household : allHouseholds) {
            for (Family family : household.getFamilies()) {
                for (Person person : family.getMembers()) {
                    String searchKey = person.getRelationshipStatus() + "," + person.getSex() + "," + person.getAgeRange();
                    int currentCount = map.get(searchKey);
                    map.put(searchKey, currentCount + 1);
                }
            }
        }

        List<List<String>> fullPersonSummary = new ArrayList<>();
        List<String> rec0 = new ArrayList<>();
        rec0.add("Relationship");
        rec0.add("Sex");
        rec0.add("Age");
        rec0.add("Persons");
        fullPersonSummary.add(rec0);

        for (String key : map.keySet()) {
            List<String> record = new ArrayList<>(Arrays.asList(key.split(",")));
            record.add(String.valueOf(map.get(key)));
            fullPersonSummary.add(record);
        }

        CSVWriter csvWriter = new CSVWriter();
        csvWriter.writeAsCsv(Files.newBufferedWriter(csvFilePath), fullPersonSummary);
    }

    static Map<String, List<Household>> groupHouseholdsByHouseholdType(List<Household> allHouseholds) throws IOException {

        Map<String, List<Household>> householdsByType = new HashMap<>();

        for (Household household : allHouseholds) {
            Family primaryFamily = household.getFamilies().get(0);
            String key = household.currentSize() + ":" + household.familyCount() + ":" + primaryFamily.getType();
            if (householdsByType.containsKey(key)) {
                householdsByType.get(key).add(household);
            } else {
                householdsByType.put(key, new ArrayList<>(Arrays.asList(household)));
            }
        }
        return householdsByType;
    }

    static void saveAllHouseholds(Path outputCsvFile, List<Household> allHouseholds) throws IOException {
        List<List<String>> csvReadyHholds = householdAsList(allHouseholds);
        CSVWriter csvWriter = new CSVWriter();
        csvWriter.writeAsCsv(Files.newBufferedWriter(outputCsvFile), csvReadyHholds);
    }

    static void saveAllFamilies(Path outputCsvFile, List<Household> allHouseholds) throws IOException {
        List<List<String>> csvReadyFamilies = familiesAsList(allHouseholds);
        CSVWriter csvWriter = new CSVWriter();
        csvWriter.writeAsCsv(Files.newBufferedWriter(outputCsvFile), csvReadyFamilies);
    }

    static void saveAllPersons(Path outputCsvFile, List<Household> allHouseholds) throws IOException {
        List<List<String>> csvReadyPersons = personsAsList(allHouseholds);
        CSVWriter csvWriter = new CSVWriter();
        csvWriter.writeAsCsv(Files.newBufferedWriter(outputCsvFile), csvReadyPersons);
    }

    private static List<List<String>> personsAsList(List<Household> allHouseholds) {
        List<List<String>> outputPersons = new ArrayList<>();
        outputPersons.add(PERSONS_OUTPUT_COLS);
        for (Household household : allHouseholds) {
            if (household.getFamilies().get(0).getType() == FamilyType.OTHER_FAMILY) {// FIXME: add relatives back
                continue;
            }
            for (Family family : household.getFamilies()) {
                if (family.getType() == FamilyType.OTHER_FAMILY) {// FIXME: add relatives back
                    continue;
                }
                for (Person person : family.getMembers()) {
                    if (person.getRelationshipStatus() == RelationshipStatus.RELATIVE) {// FIXME: add relatives back
                        continue;
                    }
                    List<String> pdata = new ArrayList<>();
                    // AgentId
                    pdata.add(String.valueOf(person.getID()));
                    // Age
                    pdata.add(String.valueOf(person.getAge()));
                    // Gender
                    pdata.add(String.valueOf(person.getSex()));
                    // GroupId
                    pdata.add(household.getID());
                    // PartnerId
                    if (person.getPartner() != null) {
                        pdata.add(String.valueOf(person.getPartner().getID()));
                    } else {
                        pdata.add(null);
                    }
                    // MotherId
                    if (person.getMother() != null) {
                        pdata.add(String.valueOf(person.getMother().getID()));
                    } else {
                        pdata.add(null);
                    }
                    // FatherId
                    if (person.getFather() != null) {
                        pdata.add(String.valueOf(person.getFather().getID()));
                    } else {
                        pdata.add(null);
                    }
                    // ChildrenIds
                    if (person.getChildren() != null) {
                        List<String> childrenIds = person.getChildren().stream().map(p -> p.getID()).collect(Collectors.toList());
                        pdata.add(childrenIds.toString());
                    } else {
                        pdata.add(null);
                    }
                    // RelativeIds
                    pdata.add(null); // FIXME: add relatives back
                    // if (person.getRelatives() != null) {
                    // pdata.add(person.getRelatives().stream().map(p -> p.getID()).collect(Collectors.toList()).toString());
                    // } else {
                    // pdata.add(null);
                    // }

                    // RelationshipStatus
                    pdata.add(String.valueOf(person.getRelationshipStatus()));

                    outputPersons.add(pdata);
                }
            }
        }
        return outputPersons;
    }

    private static List<List<String>> familiesAsList(List<Household> allHouseholds) {
        List<List<String>> outputFamilies = new ArrayList<>();
        outputFamilies.add(FAMILIES_OUTPUT_COLS);
        for (Household household : allHouseholds) {
            if (household.getFamilies().get(0).getType() == FamilyType.OTHER_FAMILY) { // FIXME: add relatives back
                continue;
            }
            for (Family family : household.getFamilies()) {
                if (family.getType() == FamilyType.OTHER_FAMILY) { // FIXME: add relatives back
                    continue;
                }
                List<String> fdata = new ArrayList<>();
                fdata.add(String.valueOf(family.getID()));
                fdata.add(String.valueOf(family.getType()));
                fdata.add(String.valueOf(family.size()));
                // List<String> memberIds = family.getMembers().stream().map(p -> p.getID()).collect(Collectors.toList());
                List<String> memberIds = family.getMembers().stream().filter(p -> p.getRelationshipStatus() != RelationshipStatus.RELATIVE).map(p -> p.getID())
                        .collect(Collectors.toList());// FIXME: add relatives back
                fdata.add(memberIds.toString());
                fdata.add(household.getID());
                outputFamilies.add(fdata);
            }
        }
        return outputFamilies;
    }

    private static List<List<String>> householdAsList(List<Household> allHouseholds) {
        // Map<String,String> sa2CodeMap = ABSStatisticalAreaCodeConverter.
        List<List<String>> outputHouseholds = new ArrayList<>();
        outputHouseholds.add(HOUSEHOLDS_OUTPUT_COLS);
        for (Household household : allHouseholds) {
            if (household.getFamilies().get(0).getType() == FamilyType.OTHER_FAMILY) {// FIXME: add relatives back
                continue;
            }
            List<String> hhData = new ArrayList<>();
            // GroupId
            hhData.add(household.getID());
            // PrimaryFamilyType
            hhData.add(String.valueOf(household.getFamilies().get(0).getType()));
            // Members
            // List<String> memberIds = household.getMembers().stream().map((p) -> p.getID()).collect(Collectors.toList());
            List<String> memberIds = household.getMembers().stream().filter(m -> m.getRelationshipStatus() != RelationshipStatus.RELATIVE).map((p) -> p.getID())
                    .collect(Collectors.toList()); // FIXME: add relatives back
            hhData.add(memberIds.toString());
            // FamilyIds
            // List<String> familyIds = household.getFamilies().stream().map((f) -> f.getID()).collect(Collectors.toList());
            List<String> familyIds = household.getFamilies().stream().filter(f -> f.getType() != FamilyType.OTHER_FAMILY).map((f) -> f.getID())
                    .collect(Collectors.toList());// FIXME: add relatives back
            hhData.add(familyIds.toString());
            // HHSize
            hhData.add(String.valueOf(household.getMembers().size()));
            // SA2_MAINCODE_2011
            hhData.add(household.getSA2MainCode());
            outputHouseholds.add(hhData);
        }

        return outputHouseholds;
    }

    static void saveSA1Households(Path csvFilesLocation, Map<String, List<Household>> householdsBySA1) throws IOException {

        for (String sa1 : householdsBySA1.keySet()) {
            List<Household> households = householdsBySA1.get(sa1);
            List<List<String>> outputHouseholds = new ArrayList<>();
            List<String> titles = new ArrayList<>(Arrays.asList("GroupId", "GroupType", "GroupSize", "Members", "Bedrooms", "DwellingStructure",
                    "FamilyIncome", "Tenure&Landlord", "FamilyIds", "SA1_7DIG"));
            outputHouseholds.add(titles);
            for (Household household : households) {
                if (household.getFamilies().get(0).getType() == FamilyType.OTHER_FAMILY) {// FIXME: add relatives back
                    continue;
                }
                List<String> hhData = new ArrayList<>();
                hhData.add(household.getID());
                hhData.add(String.valueOf(household.TARGETSIZE));
                hhData.add(String.valueOf(household.currentSize()));
                // List<String> memberIds = household.getMembers().stream().map((p) -> p.getID()).collect(Collectors.toList());
                List<String> memberIds = household.getMembers().stream().filter(m -> m.getRelationshipStatus() != RelationshipStatus.RELATIVE).map((p) -> p.getID())
                        .collect(Collectors.toList());// FIXME: add relatives back
                hhData.add(memberIds.toString());
                hhData.add(null);
                hhData.add(null);
                hhData.add(null);
                hhData.add(household.getTenlld());
                // List<String> familyIds = household.getFamilies().stream().map((f) -> f.getID()).collect(Collectors.toList());
                List<String> familyIds = household.getFamilies().stream().filter(f -> f.getType() != FamilyType.OTHER_FAMILY)
                        .map((f) -> f.getID()).collect(Collectors.toList());// FIXME: add relatives back
                hhData.add(familyIds.toString());
                hhData.add(household.getSA1Code());
                outputHouseholds.add(hhData);
            }

            Path sa1dir = Paths.get(csvFilesLocation + File.separator + sa1);
            Files.createDirectories(sa1dir);
            Path outputFile = Paths.get(sa1dir + File.separator + "HouseholdData.csv");
            CSVWriter csvWriter = new CSVWriter();
            csvWriter.writeAsCsv(Files.newBufferedWriter(outputFile), outputHouseholds);
        }
    }

    static void saveSA1Persons(Path csvFilesLocation, Map<String, List<Household>> householdsBySA1) throws IOException {
        for (String sa1 : householdsBySA1.keySet()) {
            List<List<String>> outputPersons = new ArrayList<>();
            List<String> titles = new ArrayList<>(Arrays.asList("AgentId", "PartnerId", "MotherId", "FatherId", "ChildrenIds", "RelativeIds",
                    "RelationshipStatus", "Gender", "GroupSize", "Age", "GroupId", "Travel2Work", "Destination", "PersonalIncome"));
            outputPersons.add(titles);
            for (Household household : householdsBySA1.get(sa1)) {
                if (household.getFamilies().get(0).getType() == FamilyType.OTHER_FAMILY) {// FIXME: add relatives back
                    continue;
                }
                // if (!household.validate()) {
                // throw new Error("Validation failed");
                // }
                for (Family family : household.getFamilies()) {
                    if (family.getType() == FamilyType.OTHER_FAMILY) { // FIXME: add relatives back
                        continue;
                    }
                    // if (!family.validate()) {
                    // throw new Error("Validation failed");
                    // }
                    for (Person person : family.getMembers()) {
                        if (person.getRelationshipStatus() == RelationshipStatus.RELATIVE) {// FIXME: add relatives back
                            continue;
                        }
                        // if (!person.validate()) {
                        // throw new Error("Validation failed");
                        // }
                        List<String> pdata = new ArrayList<>();
                        pdata.add(String.valueOf(person.getID()));
                        if (person.getPartner() != null) {
                            pdata.add(String.valueOf(person.getPartner().getID()));
                        } else {
                            pdata.add(null);
                        }
                        if (person.getMother() != null) {
                            pdata.add(String.valueOf(person.getMother().getID()));
                        } else {
                            pdata.add(null);
                        }
                        if (person.getFather() != null) {
                            pdata.add(String.valueOf(person.getFather().getID()));
                        } else {
                            pdata.add(null);
                        }
                        if (person.getChildren() != null) {
                            List<String> childrenIds = person.getChildren().stream().map(p -> p.getID()).collect(Collectors.toList());
                            pdata.add(childrenIds.toString());
                        } else {
                            pdata.add(null);
                        }
                        pdata.add(null);
                        // FIXME: add relatives back
                        // if (person.getRelatives() != null) {
                        // List<String> relativeIds = person.getRelatives().stream().map(p -> p.getID()).collect(Collectors.toList());
                        // pdata.add(relativeIds.toString());
                        // } else {
                        // pdata.add(null);
                        // }

                        pdata.add(String.valueOf(person.getRelationshipStatus()));
                        pdata.add(String.valueOf(person.getSex()));
                        pdata.add(String.valueOf(household.currentSize()));
                        pdata.add(String.valueOf(person.getAge()));
                        pdata.add(household.getID());
                        outputPersons.add(pdata);
                    }
                }
            }
            Path sa1dir = Paths.get(csvFilesLocation + File.separator + sa1);
            Files.createDirectories(sa1dir);
            Path outputFile = Paths.get(sa1dir + File.separator + "Persons.csv");
            CSVWriter csvWriter = new CSVWriter();
            csvWriter.writeAsCsv(Files.newBufferedWriter(outputFile), outputPersons);
        }
    }

    static void saveSA1Families(Path csvFilesLocation, Map<String, List<Household>> householdsBySA1) throws IOException {
        for (String sa1 : householdsBySA1.keySet()) {
            List<List<String>> outputFamilies = new ArrayList<>();
            List<String> titles = new ArrayList<>(Arrays.asList("FamilyId", "FamilyType", "FamilySize", "Members", "HouseholdId"));
            outputFamilies.add(titles);
            for (Household household : householdsBySA1.get(sa1)) {
                if (household.getFamilies().get(0).getType() == FamilyType.OTHER_FAMILY) {// FIXME: add relatives back
                    continue;
                }
                for (Family family : household.getFamilies()) {
                    if (family.getType() == FamilyType.OTHER_FAMILY) { // FIXME: add relatives back
                        continue;
                    }
                    List<String> fdata = new ArrayList<>();
                    fdata.add(String.valueOf(family.getID()));
                    fdata.add(String.valueOf(family.getType()));
                    fdata.add(String.valueOf(family.size()));
                    // List<String> memberIds = family.getMembers().stream().map(p -> p.getID()).collect(Collectors.toList());
                    List<String> memberIds = family.getMembers().stream().filter(m -> m.getRelationshipStatus() != RelationshipStatus.RELATIVE).map(p -> p.getID())
                            .collect(Collectors.toList());// FIXME: add relatives back
                    fdata.add(memberIds.toString());
                    fdata.add(household.getID());
                    outputFamilies.add(fdata);
                }
            }
            Path sa1dir = Paths.get(csvFilesLocation + File.separator + sa1);
            Files.createDirectories(sa1dir);
            Path outputFile = Paths.get(sa1dir + File.separator + "FamilyData.csv");
            CSVWriter csvWriter = new CSVWriter();
            csvWriter.writeAsCsv(Files.newBufferedWriter(outputFile), outputFamilies);
        }
    }

}