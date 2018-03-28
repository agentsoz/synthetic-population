/**
 *
 */
package bnw.abm.intg.synthesis;

import bnw.abm.intg.filemanager.csv.CSVReader;
import bnw.abm.intg.synthesis.models.*;
import bnw.abm.intg.util.Log;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

/**
 * @author wniroshan
 */
public class DataReader {

    static Map<String, List<HhRecord>> readHouseholdRecords(Path hhFileInfo) throws IOException {

        int titleRow = 0;
        Map<String, List<HhRecord>> hhData = new LinkedHashMap<>();
        // Even though GZIPInputStream has a buffer it reads individual bytes when processing the header, better add
        // a buffer in-between
        try (CSVParser parser = new CSVParser(new InputStreamReader(new GZIPInputStream(new BufferedInputStream(Files.newInputStream(
                hhFileInfo)))),
                                              CSVFormat.EXCEL.withSkipHeaderRecord(false))) {
            int r = -1;
            String sa, hhSize, familyDesc, hhCount, currentSA = null;
            int saCol = 0, hhSizeCol = 1, familyDescriptionCol = 2, hhCountCol = 3;
            List<HhRecord> hhRecList = null;

            for (CSVRecord rec : parser) {
                r++;
                if (r <= titleRow) {
                    continue;
                }

                sa = rec.get(saCol);
                if (!sa.equals(currentSA)) {
                    currentSA = sa;
                    hhRecList = new ArrayList<>();
                    hhData.put(sa, hhRecList);
                }

                hhSize = rec.get(hhSizeCol);
                familyDesc = rec.get(familyDescriptionCol);
                hhCount = rec.get(hhCountCol);
                int nop = parseIntNofPersons(hhSize);
                int nofFamilies = getFamilyCountInHousehold(familyDesc);
                FamilyType primaryFamilyType = getPrimaryFamilyType(familyDesc);
                int nofHhs = Integer.parseInt(hhCount);
                HhRecord hhr = new HhRecord(nop, nofFamilies, primaryFamilyType, nofHhs, sa);
                hhRecList.add(hhr);
            }
        }catch (NoSuchFileException nsfe){
            Log.warn("No data file: "+hhFileInfo.toString());
        }

        return hhData;
    }

    static Map<String, List<IndRecord>> readPersonRecords(Path indFileInfo) throws IOException {
        int titleRow = 0;
        Map<String, List<IndRecord>> indData = new LinkedHashMap<>();
        try (CSVParser parser = new CSVParser(new InputStreamReader(new GZIPInputStream(new BufferedInputStream(Files.newInputStream(
                indFileInfo)))), CSVFormat.EXCEL.withSkipHeaderRecord(false))) {
            int r = -1;
            String sa, ageRangeStr, currentSA = null;
            RelationshipStatus relStatus;
            Sex sex;
            int sacol = 0, relcol = 1, sexcol = 2, agecol = 3, nofagentscol = 4;
            List<IndRecord> indRecList = new ArrayList<>();

            for (CSVRecord rec : parser) {
                r++;
                if (r <= titleRow) {
                    continue;
                }
                sa = rec.get(sacol);
                if (!sa.equals(currentSA)) {
                    currentSA = sa;
                    indRecList = new ArrayList<>();
                    indData.put(sa, indRecList);
                }

                relStatus = getRelationshipStatus(rec.get(relcol));
                sex = getSex(rec.get(sexcol));
                ageRangeStr = rec.get(agecol);

                int personCount = Integer.parseInt(rec.get(nofagentscol));
                IndRecord indRec = new IndRecord(relStatus, sex, getAgeRange(ageRangeStr), personCount, sa);
                indRecList.add(indRec);
            }
        }catch (NoSuchFileException nsfe){
            Log.warn("No data file: "+indFileInfo.toString());
        }
        return indData;
    }

    static Map<String, Map<String, Integer>> readSA1HouseholdDistribution(Path csvFile,
                                                                          int numberOfPersonsCol,
                                                                          int familyHhTypeCol)
            throws IOException {

        Map<String, Map<String, Integer>> householdTypesBySA1 = new LinkedHashMap<>();
        try (CSVParser csvParser = new CSVParser(new InputStreamReader(new GZIPInputStream(new BufferedInputStream
                                                                                                   (Files.newInputStream(
                                                                                                           csvFile)))),
                                                 CSVFormat.EXCEL.withSkipHeaderRecord(false))) {
            int row = -1;
            CSVRecord headerRow = null;
            for (CSVRecord csvRecord : csvParser) {
                row++;
                if (row == 0) {
                    headerRow = csvRecord;
                } else {
                    int numberOfPersons = parseIntNofPersons(csvRecord.get(numberOfPersonsCol));
                    int familyCount = getFamilyCountInHousehold(csvRecord.get(familyHhTypeCol));
                    FamilyType primaryFamily = getPrimaryFamilyType(csvRecord.get(familyHhTypeCol));

                    String key = numberOfPersons + ":" + familyCount + ":" + primaryFamily;

                    Map<String, Integer> sa1Map = new LinkedHashMap<>();
                    for (int column = familyHhTypeCol + 1; column < csvRecord.size(); column++) {
                        sa1Map.put(headerRow.get(column), Integer.parseInt(csvRecord.get(column)));
                    }
                    householdTypesBySA1.put(key, sa1Map);
                }

            }
        }

        return householdTypesBySA1;
    }

    static Map<Integer, Double> readAgeDistribution(Map<String, String> params) throws IOException {
        Path csvFile = Paths.get(params.get("FileName"));
        CSVParser csvParser = new CSVParser(Files.newBufferedReader(csvFile),
                                            CSVFormat.EXCEL.withSkipHeaderRecord(false));
        int row = -1;
        int dataRow = Integer.parseInt(params.get("DataStartRow"));
        int percentageCol = Integer.parseInt(params.get("PercentageColumn"));
        int ageColumn = Integer.parseInt(params.get("AgeColumn"));

        Map<Integer, Double> ageDist = new LinkedHashMap<>();
        for (CSVRecord csvRecord : csvParser) {
            row++;
            if (row >= dataRow) {
                if ((csvRecord.size() - 1) < ageColumn || csvRecord.get(ageColumn) == null || csvRecord.get(ageColumn)
                                                                                                       .equals("")) {
                    break; // We have reached end
                }
                String age = csvRecord.get(ageColumn).split(" ")[0];
                ageDist.put(Integer.parseInt(age), Double.parseDouble(csvRecord.get(percentageCol)));

            }

        }
        csvParser.close();
        return ageDist;
    }

    static List<List<String>> readTenlldDistribution(Path csvFile) throws IOException {
        CSVReader csvReader = new CSVReader(CSVFormat.EXCEL.withSkipHeaderRecord(false));
        csvReader.setStripChars(new String[]{"{", "}", "B"});
        List<List<String>> data = csvReader.readCsvRows(Files.newBufferedReader(csvFile));
        return data;

    }

    private static Sex getSex(String sexDescription) {
        switch (sexDescription) {
            case "Male":
                return Sex.Male;
            case "Female":
                return Sex.Female;
            default:
                throw new Error("Unrecognised sex");
        }
    }

    private static RelationshipStatus getRelationshipStatus(String relationshipType) {
        RelationshipStatus relStatus;
        switch (relationshipType) {
            case "Relatives":
                relStatus = RelationshipStatus.RELATIVE;
                break;
            case "Married":
                relStatus = RelationshipStatus.MARRIED;
                break;
            case "Lone parent":
                relStatus = RelationshipStatus.LONE_PARENT;
                break;
            case "U15Child":
                relStatus = RelationshipStatus.U15_CHILD;
                break;
            case "Student":
                relStatus = RelationshipStatus.STUDENT;
                break;
            case "O15Child":
                relStatus = RelationshipStatus.O15_CHILD;
                break;
            case "GroupHhold":
                relStatus = RelationshipStatus.GROUP_HOUSEHOLD;
                break;
            case "Lone person":
                relStatus = RelationshipStatus.LONE_PERSON;
                break;
            default:
                throw new Error("Unrecognised Person type");
        }
        return relStatus;
    }

    private static int parseIntNofPersons(String nofPersons) throws IOException {
        int nop = 0;
        switch (nofPersons) {
            case "One person":
                nop = 1;
                break;
            case "Two persons":
                nop = 2;
                break;
            case "Three persons":
                nop = 3;
                break;
            case "Four persons":
                nop = 4;
                break;
            case "Five persons":
                nop = 5;
                break;
            case "Six persons":
                nop = 6;
                break;
            case "Seven persons":
                nop = 7;
                break;
            case "Eight or more persons":
                nop = 8;
                break;
            default:
                throw new IOException("Unrecognised Number of persons in dwelling (NPRD) value: " + nofPersons);
        }
        return nop;
    }

    private static AgeRange getAgeRange(String ageRangeStr) {
        AgeRange age = null;
        switch (ageRangeStr) {
            case "0-14 years":
                age = AgeRange.A0_14;
                break;
            case "15-24 years":
                age = AgeRange.A15_24;
                break;
            case "25-39 years":
                age = AgeRange.A25_39;
                break;
            case "40-54 years":
                age = AgeRange.A40_54;
                break;
            case "55-69 years":
                age = AgeRange.A55_69;
                break;
            case "70-84 years":
                age = AgeRange.A70_84;
                break;
            case "85-99 years":
                age = AgeRange.A85_99;
                break;
            case "100 years and over":
                age = AgeRange.A100_110;
                break;
            default:
                throw new Error("Unrecognised age range: " + ageRangeStr);
        }
        return age;
    }

    private static FamilyType getPrimaryFamilyType(String familyDescription) {
        for (FamilyType familyType : EnumSet.allOf(FamilyType.class)) {
            if (familyDescription.contains(familyType.description())) {
                return familyType;
            }
        }
        throw new Error("Primary family type could not be recognised");
    }

    private static int getFamilyCountInHousehold(String familyDescription) {
        if (familyDescription.contains("One family")) {
            return 1;
        } else if (familyDescription.contains("Two family")) {
            return 2;
        } else if (familyDescription.contains("Three or more family")) {
            return 3;
        } else if (familyDescription.contains("Group household") | familyDescription.contains("Lone person")) {
            return 1;
        }

        throw new Error("Number of families could not be recognised");
    }

    static List<HhRecord> getHhRecordsByPrimaryFamilyType(List<HhRecord> hhRecs,
                                                          FamilyHouseholdType... familyHhTypes) {

        return hhRecs.stream()
                     .filter(r -> Arrays.asList(familyHhTypes).contains(r.FAMILY_HOUSEHOLD_TYPE))
                     .collect(Collectors.toList());
    }

    static List<IndRecord> getAgentsRecordsByRelationshipStatus(List<IndRecord> indRecs,
                                                                RelationshipStatus... relStates) {
        return indRecs.stream()
                      .filter(r -> Arrays.asList(relStates).contains(r.RELATIONSHIP_STATUS))
                      .collect(Collectors.toList());
    }

}

