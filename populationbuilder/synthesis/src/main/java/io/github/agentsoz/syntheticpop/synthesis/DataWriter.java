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

    /**
     * Computes the number of households in households list by each family household type given in HhRecords list (the marginal
     * distribution) and saves
     * to the specified csv file.
     *
     * @param hhRecs      The list of HhRecords giving the family household types
     * @param households  The list of households in the population
     * @param csvFilePath The file to write
     * @throws IOException If file writing fails
     */
    static void saveHouseholdSummary(List<HhRecord> hhRecs,
                                     List<Household> households,
                                     Path csvFilePath) throws IOException {

        Map<String, List<Household>> householdsByType = HouseholdSummary.groupHouseholdsByHouseholdType(households);

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

    /**
     * Computes the number of persons in households list by each person type given in IndRecords list (the marginal distribution) and saves
     * to the specified csv file.
     *
     * @param indRecs     The list of IndRecords giving the person types
     * @param persons     The list of persons in the population
     * @param csvFilePath The file to write
     * @throws IOException If file writing fails
     */
    static void savePersonsSummary(List<IndRecord> indRecs,
                                   List<Person> persons,
                                   Path csvFilePath) throws IOException {
        Map<String, Integer> map = new LinkedHashMap<>();
        for (IndRecord indRec : indRecs) {
            String key = indRec.RELATIONSHIP_STATUS + "," + indRec.SEX + "," + indRec.AGE_RANGE;
            map.put(key, 0);
        }

        for (Person person : persons) {
            String searchKey = person.getRelationshipStatus() + "," + person.getSex() + "," + person
                    .getAgeRange();
            int currentCount = map.get(searchKey);
            map.put(searchKey, currentCount + 1);
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

    /**
     * Saves all households in households list to the specified csv file
     *
     * @param outputCsvFile The csv file path
     * @param households    The list of households
     * @throws IOException If file writing fails
     */
    static void saveHouseholds(Path outputCsvFile, List<Household> households) throws IOException {
        List<List<String>> csvReadyHhs = householdAsList(households);
        CSVWriter csvWriter = new CSVWriter();
        csvWriter.writeAsCsv(new OutputStreamWriter(new GZIPOutputStream(new BufferedOutputStream(Files.newOutputStream(
                outputCsvFile)))), csvReadyHhs);
    }

    /**
     * Saves all families in households list to the specified csv file
     *
     * @param outputCsvFile The csv file path
     * @param households    The list of households
     * @throws IOException If file writing fails
     */
    static void saveFamilies(Path outputCsvFile, List<Household> households) throws IOException {
        List<List<String>> csvReadyFamilies = familiesAsList(households);
        CSVWriter csvWriter = new CSVWriter();
        csvWriter.writeAsCsv(new OutputStreamWriter(new GZIPOutputStream(new BufferedOutputStream(Files.newOutputStream(
                outputCsvFile)))), csvReadyFamilies);
    }

    /**
     * Saves all persons in the households list to the specified csv file
     *
     * @param outputCsvFile Output csv file path
     * @param persons       The list of persons
     * @throws IOException If file writing fails
     */
    static void savePersons(Path outputCsvFile, List<Person> persons) throws IOException {
        List<List<String>> csvReadyPersons = personsAsList(persons);
        CSVWriter csvWriter = new CSVWriter();
        csvWriter.writeAsCsv(new OutputStreamWriter(new GZIPOutputStream(new BufferedOutputStream(Files.newOutputStream(
                outputCsvFile)))), csvReadyPersons);
    }

    /**
     * Converting persons to a list of array lists to be written as a csv. The order of items in each list is: "AgentId",
     * "Age", "Gender", "RelationshipStatus", "PartnerId", "MotherId", "FatherId", "ChildrenIds", "RelativeIds", "HouseholdId", "FamilyId",
     * "SA2_MAINCODE", "SA1_7DIGCODE"
     *
     * @param persons The list of persons
     * @return Persons as a list of String lists
     */
    private static List<List<String>> personsAsList(List<Person> persons) {
        final List<String> PERSONS_OUTPUT_COLS = new ArrayList<>(Arrays.asList("AgentId",
                                                                               "Age",
                                                                               "Gender",
                                                                               "RelationshipStatus",
                                                                               "PartnerId",
                                                                               "MotherId",
                                                                               "FatherId",
                                                                               "ChildrenIds",
                                                                               "RelativeIds",
                                                                               "SA2_MAINCODE",
                                                                               "SA1_7DIGCODE"));
        List<List<String>> outputPersons = new ArrayList<>();
        outputPersons.add(PERSONS_OUTPUT_COLS);
        for (Person person : persons) {
            List<String> pData = new ArrayList<>();
            // AgentId
            pData.add(String.valueOf(person.getID()));
            // Age
            pData.add(String.valueOf(person.getAge()));
            // Gender
            pData.add(String.valueOf(person.getSex()));
            // RelationshipStatus
            pData.add(String.valueOf(person.getRelationshipStatus()));
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
            pData.add(person.getSA2MainCode()); //SA2 main code
            pData.add(person.getSA1Code()); //SA1 code

            outputPersons.add(pData);
        }
        return outputPersons;
    }

    /**
     * Get the families in the households as a list of list to be written to csv files. Order of items in each list is: "FamilyId",
     * "FamilySize", "FamilyType", "Members", "HouseholdId", "SA2_MAINCODE", "SA1_7DIGCODE"
     *
     * @param households The households
     * @return Families as a list of lists
     */
    private static List<List<String>> familiesAsList(List<Household> households) {
        List<String> FAMILIES_OUTPUT_COLS = new ArrayList<>(Arrays.asList("FamilyId",
                                                                          "FamilySize",
                                                                          "FamilyType",
                                                                          "Members",
                                                                          "HouseholdId",
                                                                          "SA2_MAINCODE",
                                                                          "SA1_7DIGCODE"));
        List<List<String>> outputFamilies = new ArrayList<>();
        outputFamilies.add(FAMILIES_OUTPUT_COLS);
        for (Household household : households) {
            for (Family family : household.getFamilies()) {
                List<String> fData = new ArrayList<>();
                fData.add(String.valueOf(family.getID()));//family id
                fData.add(String.valueOf(family.size())); // family size
                fData.add(String.valueOf(family.getType())); // family type
                List<String> memberIds = family.getMembers().stream().map(Person::getID).collect(Collectors.toList()); //members
                fData.add(memberIds.toString());

                fData.add(household.getID()); //Household id
                fData.add(household.getSA2MainCode()); //SA2 main code
                fData.add(household.getSA1Code()); //SA1 code

                outputFamilies.add(fData);
            }
        }
        return outputFamilies;
    }

    /**
     * Coverts the households to a list of list entries, each list entry representing a household. This is to be used to write households to
     * a csv file. The order of items in each list is: "HouseholdId", "HouseholdSize", "FamilyCount", "PrimaryFamilyType", "Members",
     * "Families", "SA2_MAINCODE", "SA1_7DIGCODE"
     *
     * @param households The list of household instances
     * @return Households as a list of lists
     */
    private static List<List<String>> householdAsList(List<Household> households) {
        final List<String> HOUSEHOLDS_OUTPUT_COLS = new ArrayList<>(Arrays.asList("HouseholdId",
                                                                                  "HouseholdSize",
                                                                                  "FamilyCount",
                                                                                  "PrimaryFamilyType",
                                                                                  "Members",
                                                                                  "Families",
                                                                                  "SA2_MAINCODE",
                                                                                  "SA1_7DIGCODE"));
        List<List<String>> outputHouseholds = new ArrayList<>();
        outputHouseholds.add(HOUSEHOLDS_OUTPUT_COLS);
        for (Household household : households) {
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

            // SA2_MAINCODE_2011
            hhData.add(household.getSA2MainCode());
            //SA1_7DIGCODE
            hhData.add(household.getSA1Code());
            outputHouseholds.add(hhData);
        }

        return outputHouseholds;
    }
}