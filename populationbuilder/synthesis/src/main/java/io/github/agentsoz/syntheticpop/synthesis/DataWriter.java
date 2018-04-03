package io.github.agentsoz.syntheticpop.synthesis;

import io.github.agentsoz.syntheticpop.filemanager.csv.CSVWriter;
import io.github.agentsoz.syntheticpop.synthesis.models.*;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.GZIPOutputStream;

/**
 * @author wniroshan
 */

public class DataWriter {
    private static final List<String> HOUSEHOLDS_OUTPUT_COLS = new ArrayList<>(Arrays.asList("HouseholdId",
                                                                                             "HouseholdSize",
                                                                                             "FamilyCount",
                                                                                             "PrimaryFamilyType",
                                                                                             "Members",
                                                                                             "FamilyIds",
                                                                                             "CensusHouseholdSize",
                                                                                             "SA2_MAINCODE",
                                                                                             "SA1_7DIGCODE"));

    private static final List<String> PERSONS_OUTPUT_COLS = new ArrayList<>(Arrays.asList("AgentId",
                                                                                          "Age",
                                                                                          "Gender",
                                                                                          "PartnerId",
                                                                                          "MotherId",
                                                                                          "FatherId",
                                                                                          "ChildrenIds",
                                                                                          "RelativeIds",
                                                                                          "RelationshipStatus",
                                                                                          "HouseholdId",
                                                                                          "FamilyId",
                                                                                          "SA2_MAINCODE",
                                                                                          "SA1_7DIGCODE"));

    private static final List<String> FAMILIES_OUTPUT_COLS = new ArrayList<>(Arrays.asList("FamilyId",
                                                                                           "FamilyType",
                                                                                           "FamilySize",
                                                                                           "Members",
                                                                                           "HouseholdId",
                                                                                           "SA2_MAINCODE",
                                                                                           "SA1_7DIGCODE"));

    static void saveHouseholdSummary(List<HhRecord> hhRecs,
                                     List<Household> allHouseholds,
                                     Path csvFilePath) throws IOException {

        Map<String, List<Household>> householdsByType = HouseholdSummary.groupHouseholdsByHouseholdType(allHouseholds);

        List<List<String>> fullHhSummary = new ArrayList<>();
        List<String> record1 = new ArrayList<>();
        record1.add("NofPersons");
        record1.add("NofFamilies");
        record1.add("PrimaryFamily");
        record1.add("NofHouseholds");
        fullHhSummary.add(record1);
        for (HhRecord hhRec : hhRecs) {
            List<String> record = new ArrayList<>();
            record.add(String.valueOf(hhRec.NUM_OF_PERSONS_PER_HH));

            String nofFamilies = String.valueOf(hhRec.getFamilyCountPerHousehold()) + " Family";
            record.add(nofFamilies);

            record.add(hhRec.getPrimaryFamilyType().description());
            int nofHhs = 0;
            List<Household> hhs = householdsByType.get(hhRec.NUM_OF_PERSONS_PER_HH + ":" + hhRec
                    .getFamilyCountPerHousehold() + ":"
                                                               + hhRec.getPrimaryFamilyType());
            if (hhs != null) {
                nofHhs = hhs.size();
            }
            record.add(String.valueOf(nofHhs));

            fullHhSummary.add(record);
        }

        CSVWriter csvWriter = new CSVWriter();
        csvWriter.writeAsCsv(new OutputStreamWriter(new GZIPOutputStream(new BufferedOutputStream(Files.newOutputStream(
                csvFilePath)))), fullHhSummary);
    }

    static void savePersonsSummary(List<IndRecord> indRecs,
                                   List<Household> allHouseholds,
                                   Path csvFilePath) throws IOException {
        new LinkedHashMap<>();

        Map<String, Integer> map = new LinkedHashMap<>();
        for (IndRecord indRec : indRecs) {
            String key = indRec.RELATIONSHIP_STATUS + "," + indRec.SEX + "," + indRec.AGE_RANGE;
            map.put(key, 0);
        }

        for (Household household : allHouseholds) {
            for (Family family : household.getFamilies()) {
                for (Person person : family.getMembers()) {
                    String searchKey = person.getRelationshipStatus() + "," + person.getSex() + "," + person
                            .getAgeRange();
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
        csvWriter.writeAsCsv(new OutputStreamWriter(new GZIPOutputStream(new BufferedOutputStream(Files.newOutputStream(
                csvFilePath)))), fullPersonSummary);
    }

    static void saveHouseholds(Path outputCsvFile, List<Household> allHouseholds) throws IOException {
        List<List<String>> csvReadyHhs = householdAsList(allHouseholds);
        CSVWriter csvWriter = new CSVWriter();
        csvWriter.writeAsCsv(new OutputStreamWriter(new GZIPOutputStream(new BufferedOutputStream(Files.newOutputStream(
                outputCsvFile)))), csvReadyHhs);
    }

    static void saveFamilies(Path outputCsvFile, List<Household> allHouseholds) throws IOException {
        List<List<String>> csvReadyFamilies = familiesAsList(allHouseholds);
        CSVWriter csvWriter = new CSVWriter();
        csvWriter.writeAsCsv(new OutputStreamWriter(new GZIPOutputStream(new BufferedOutputStream(Files.newOutputStream(
                outputCsvFile)))), csvReadyFamilies);
    }

    static void savePersons(Path outputCsvFile, List<Household> allHouseholds) throws IOException {
        List<List<String>> csvReadyPersons = personsAsList(allHouseholds);
        CSVWriter csvWriter = new CSVWriter();
        csvWriter.writeAsCsv(new OutputStreamWriter(new GZIPOutputStream(new BufferedOutputStream(Files.newOutputStream(
                outputCsvFile)))), csvReadyPersons);
    }

    private static List<List<String>> personsAsList(List<Household> allHouseholds) {
        List<List<String>> outputPersons = new ArrayList<>();
        outputPersons.add(PERSONS_OUTPUT_COLS);
        for (Household household : allHouseholds) {
            for (Family family : household.getFamilies()) {
                for (Person person : family.getMembers()) {
                    List<String> pData = new ArrayList<>();
                    // AgentId
                    pData.add(String.valueOf(person.getID()));
                    // Age
                    pData.add(String.valueOf(person.getAge()));
                    // Gender
                    pData.add(String.valueOf(person.getSex()));
                    // GroupId
                    pData.add(household.getID());
                    // PartnerId
                    if (person.getPartner() != null) {
                        pData.add(String.valueOf(person.getPartner().getID()));
                    } else {
                        pData.add(null);
                    }
                    // MotherId
                    if (person.getMother() != null) {
                        pData.add(String.valueOf(person.getMother().getID()));
                    } else {
                        pData.add(null);
                    }
                    // FatherId
                    if (person.getFather() != null) {
                        pData.add(String.valueOf(person.getFather().getID()));
                    } else {
                        pData.add(null);
                    }
                    // ChildrenIds
                    if (person.getChildren() != null) {
                        List<String> childrenIds = person.getChildren()
                                .stream()
                                .map(Person::getID)
                                .collect(Collectors.toList());
                        pData.add(childrenIds.toString());
                    } else {
                        pData.add(null);
                    }
                    // RelativeIds
                    if (person.getRelatives() != null) {
                        pData.add(person.getRelatives()
                                          .stream()
                                          .map(Person::getID)
                                          .collect(Collectors.toList())
                                          .toString());
                    } else {
                        pData.add(null);
                    }

                    // RelationshipStatus
                    pData.add(String.valueOf(person.getRelationshipStatus()));

                    pData.add(household.getID()); //Household id
                    pData.add(family.getID()); //Family id
                    pData.add(household.getSA2MainCode()); //SA2 main code
                    pData.add(household.getSA1Code()); //SA1 code

                    outputPersons.add(pData);
                }
            }
        }
        return outputPersons;
    }

    private static List<List<String>> familiesAsList(List<Household> allHouseholds) {
        List<List<String>> outputFamilies = new ArrayList<>();
        outputFamilies.add(FAMILIES_OUTPUT_COLS);
        for (Household household : allHouseholds) {
            for (Family family : household.getFamilies()) {
                List<String> fData = new ArrayList<>();
                fData.add(String.valueOf(family.getID()));
                fData.add(String.valueOf(family.getType()));
                fData.add(String.valueOf(family.size()));
                List<String> memberIds = family.getMembers().stream().map(Person::getID).collect(Collectors.toList());
                fData.add(memberIds.toString());

                fData.add(household.getID()); //Household id
                fData.add(household.getSA2MainCode()); //SA2 main code
                fData.add(household.getSA1Code()); //SA1 code

                outputFamilies.add(fData);
            }
        }
        return outputFamilies;
    }

    private static List<List<String>> householdAsList(List<Household> allHouseholds) {

        List<List<String>> outputHouseholds = new ArrayList<>();
        outputHouseholds.add(HOUSEHOLDS_OUTPUT_COLS);
        for (Household household : allHouseholds) {
            List<String> hhData = new ArrayList<>();
            // HouseholdID
            hhData.add(household.getID());
            //Household size
            hhData.add(String.valueOf(household.getExpectedSize()));
            //FamilyCount
            hhData.add(String.valueOf(household.getExpectedFamilyCount()));
            // PrimaryFamilyType
            hhData.add(String.valueOf(household.getPrimaryFamilyType()));
            // Members
            List<String> memberIds = household.getMembers().stream().map(Person::getID).collect(Collectors.toList());
            hhData.add(memberIds.toString());
            // FamilyIds
            List<String> familyIds = household.getFamilies()
                    .stream()
                    .map(Family::getID)
                    .collect(Collectors.toList());
            hhData.add(familyIds.toString());
            // HHSize
            hhData.add(String.valueOf(household.getMembers().size()));
            // SA2_MAINCODE_2011
            hhData.add(household.getSA2MainCode());
            //SA1_7DIGCODE
            hhData.add(household.getSA1Code());
            outputHouseholds.add(hhData);
        }

        return outputHouseholds;
    }
}