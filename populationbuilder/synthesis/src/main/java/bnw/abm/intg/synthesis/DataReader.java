/**
 *
 */
package bnw.abm.intg.synthesis;

import bnw.abm.intg.filemanager.csv.CSVReader;
import bnw.abm.intg.synthesis.models.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Bhagya N. Wickramasinghe
 */
public class DataReader {

    static Map<String, List<HhRecord>> readHouseholdRecords(Path hhfileinfo) throws IOException {
        int titleRow = 0;

        CSVParser parser = new CSVParser(Files.newBufferedReader(hhfileinfo),
                                         CSVFormat.EXCEL.withSkipHeaderRecord(false));
        int r = -1;
        String sa, hhSize, familyDesc, hhcount, currentSA = null;
        int saCol = 1, hhSizeCol = 2, familyDescriptionCol = 3, hhCountCol = 4;
        List<HhRecord> hhreclist = null;
        Map<String, List<HhRecord>> hhdata = new LinkedHashMap<>();
        for (CSVRecord rec : parser) {
            r++;
            if (r <= titleRow) {
                continue;
            }

            sa = rec.get(saCol);
            if (!sa.equals(currentSA)) {
                currentSA = sa;
                hhreclist = new ArrayList<>();
                hhdata.put(sa, hhreclist);
            }

            hhSize = rec.get(hhSizeCol);
            familyDesc = rec.get(familyDescriptionCol);
            hhcount = rec.get(hhCountCol);
            int nop = parseIntNofPersons(hhSize);
            int nofFamilies = getFamilyCountInHousehold(familyDesc);
            FamilyType primaryFamilyType = getPrimaryFamilyType(familyDesc);
            int nofHhs = Integer.parseInt(hhcount);
            HhRecord hhr = new HhRecord(nop, nofFamilies, primaryFamilyType, nofHhs);
            hhreclist.add(hhr);
        }
        parser.close();

        return hhdata;
    }

    static Map<String, List<IndRecord>> readPersonRecords(Path indFileInfo) throws IOException {
        int titleRow = 0;
        CSVParser parser = new CSVParser(Files.newBufferedReader(indFileInfo),
                                         CSVFormat.EXCEL.withSkipHeaderRecord(false));
        int r = -1;
        String sa, ageRangeStr, currentSA = null;
        RelationshipStatus relStatus;
        Sex sex;
        int sacol = 1, relcol = 2, sexcol = 3, agecol = 4, nofagentscol = 5;
        List<IndRecord> indreclist = new ArrayList<>();
        Map<String, List<IndRecord>> inddata = new LinkedHashMap<>();
        for (CSVRecord rec : parser) {
            r++;
            if (r <= titleRow) {
                continue;
            }
            sa = rec.get(sacol);
            if (!sa.equals(currentSA)) {
                currentSA = sa;
                indreclist = new ArrayList<>();
                inddata.put(sa, indreclist);
            }

            relStatus = getRelationshipStatus(rec.get(relcol));
            sex = getSex(rec.get(sexcol));
            ageRangeStr = rec.get(agecol);

            int personcount = Integer.parseInt(rec.get(nofagentscol));
            IndRecord indr = new IndRecord(relStatus, sex, getAgeRange(ageRangeStr), personcount);
            indreclist.add(indr);
        }
        parser.close();
        return inddata;
    }

    static Map<String, Map<String, Integer>> readSA1HouseholdDistribution(Path csvFile,
                                                                          int sa1Row,
                                                                          int numberOfPersonsCol,
                                                                          int familyHhTypeCol)
            throws IOException {
        CSVParser csvParser = new CSVParser(Files.newBufferedReader(csvFile),
                                            CSVFormat.EXCEL.withSkipHeaderRecord(false));
        int row = -1;

        Map<String, Map<String, Integer>> householdTypesBySA1 = new LinkedHashMap<>();
        List<String> sa1list = new ArrayList<>();

        for (CSVRecord csvRecord : csvParser) {
            row++;
            if (row == sa1Row) {
                for (int column = 0; column < csvRecord.size(); column++) {
                    sa1list.add(csvRecord.get(column));
                }
            } else if (row > sa1Row) {
                int numberOfPersons = parseIntNofPersons(csvRecord.get(numberOfPersonsCol));
                int familyCount = getFamilyCountInHousehold(csvRecord.get(familyHhTypeCol));
                FamilyType primaryFamily = getPrimaryFamilyType(csvRecord.get(familyHhTypeCol));

                String key = numberOfPersons + ":" + familyCount + ":" + primaryFamily;

                Map<String, Integer> sa1Map = new LinkedHashMap<>();
                for (int column = 0; column < csvRecord.size(); column++) {
                    if (column > familyHhTypeCol) {
                        sa1Map.put(sa1list.get(column), Integer.parseInt(csvRecord.get(column)));
                    }
                }
                householdTypesBySA1.put(key, sa1Map);
            }

        }
        csvParser.close();

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

    static List<HhRecord> getHhsByFamilyType(List<HhRecord> hhrecs, FamilyHouseholdType... familyTypes) {
        List<HhRecord> shortlist = new ArrayList<>();
        for (int j = 0; j < familyTypes.length; j++) {
            for (int i = 0; i < hhrecs.size(); i++) {
                if (hhrecs.get(i).getFamilyCountPerHousehold() == familyTypes[j].getFamilyCount() &&
                        hhrecs.get(i).getPrimaryFamilyType() == familyTypes[j].getFamilyType()) {
                    shortlist.add(hhrecs.get(i));
                }
            }
        }
        return shortlist;
    }

    static List<IndRecord> getAgentsByRelType(List<IndRecord> indrecs, RelationshipStatus... relStates) {
        List<IndRecord> shortlist = new ArrayList<>();
        for (int j = 0; j < relStates.length; j++) {
            for (int i = 0; i < indrecs.size(); i++) {
                if (indrecs.get(i).relationshipStatus == relStates[j]) {
                    shortlist.add(indrecs.get(i));
                }
            }
        }
        return shortlist;

    }

}

class HhRecord {
    public final int NUM_OF_PERSONS_PER_HH;
    public final int hhCount;
    public final FamilyHouseholdType FAMILY_HOUSEHOLD_TYPE;

    public HhRecord(int nofPersons, int familyCountPerHh, FamilyType familyType, int hhcount) {
        this.NUM_OF_PERSONS_PER_HH = nofPersons;
        this.hhCount = hhcount;

        FamilyHouseholdType tempFamilyHhtype = null;
        for (FamilyHouseholdType familyHouseholdType : FamilyHouseholdType.values()) {
            if (familyHouseholdType.getFamilyCount() == familyCountPerHh && familyHouseholdType.getFamilyType() == familyType) {
                tempFamilyHhtype = familyHouseholdType;
                break;
            }
        }
        FAMILY_HOUSEHOLD_TYPE = tempFamilyHhtype;

    }

    public FamilyType getPrimaryFamilyType() {

        return this.FAMILY_HOUSEHOLD_TYPE.getFamilyType();
    }

    public int getFamilyCountPerHousehold() {
        return this.FAMILY_HOUSEHOLD_TYPE.getFamilyCount();
    }
}

class IndRecord {
    final public RelationshipStatus relationshipStatus;
    final public Sex sex;
    final public AgeRange ageRange;
    final public int indCount;

    public IndRecord(RelationshipStatus relStatus, Sex sex, AgeRange ageRange, int individualsCount) {
        this.ageRange = ageRange;
        this.sex = sex;
        this.relationshipStatus = relStatus;
        this.indCount = individualsCount;
        if (individualsCount > 0) {
            this.ageRange.markNotEmpty(true);
        }
    }
}