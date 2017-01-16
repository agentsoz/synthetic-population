/**
 * 
 */
package bnw.abm.intg.latchpop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import bnw.abm.intg.filemanager.csv.CSVReader;

/**
 * @author Bhagya N. Wickramasinghe
 *
 */
public class DataReader {

	static Map<String, List<HhRecord>> readHouseholdRecords(Path hhfileinfo) throws IOException {
		int titleRow = 0;

		CSVParser parser = new CSVParser(Files.newBufferedReader(hhfileinfo), CSVFormat.EXCEL.withSkipHeaderRecord(false));
		int r = -1;
		String sa, hhSize, familyDesc, hhcount, currentSA = null;
		int sacol = 1, hhSizecol = 2, familyDescriptioncol = 3, hhcountcol = 4;
		List<HhRecord> hhreclist = null;
		Map<String, List<HhRecord>> hhdata = new LinkedHashMap<>();
		for (CSVRecord rec : parser) {
			r++;
			if (r <= titleRow) {
				continue;
			}

			sa = rec.get(sacol);
			if (!sa.equals(currentSA)) {
				currentSA = sa;
				hhreclist = new ArrayList<>();
				hhdata.put(sa, hhreclist);
			}

			hhSize = rec.get(hhSizecol);
			familyDesc = rec.get(familyDescriptioncol);
			hhcount = rec.get(hhcountcol);
			int nop = nofPersons(hhSize);
			int nofFamilies = getFamilyCountInHousehold(familyDesc);
			FamilyType primaryFamilyType = getPrimaryFamilyType(familyDesc);
			int nofHhs = Integer.parseInt(hhcount);
			HhRecord hhr = new HhRecord(nop, nofFamilies, primaryFamilyType, nofHhs);
			hhreclist.add(hhr);
		}
		parser.close();

		return hhdata;
	}

	static Map<String, List<IndRecord>> readPersonRecords(Path indfileinfo) throws IOException {
		int titleRow = 0;
		CSVParser parser = new CSVParser(Files.newBufferedReader(indfileinfo), CSVFormat.EXCEL.withSkipHeaderRecord(false));
		int r = -1;
		String sa, ageRangeStr, currentSA = null;
		PersonType personType;
		ChildType childType;
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

			personType = getPersonType(rec.get(relcol));
			childType = getChildType(rec.get(relcol));
			sex = getSex(rec.get(sexcol));
			ageRangeStr = rec.get(agecol);

			int personcount = Integer.parseInt(rec.get(nofagentscol));
			IndRecord indr = new IndRecord(personType, childType, sex, getAgeRange(ageRangeStr), personcount);
			indreclist.add(indr);
		}
		parser.close();
		return inddata;
	}

	static Map<String, Map<String, Integer>> readSA1HouseholdDistribution(Path csvFile, int sa1Row, int numberOfPersonsCol, int familyHhTypeCol)
			throws IOException {
		CSVParser csvParser = new CSVParser(Files.newBufferedReader(csvFile), CSVFormat.EXCEL.withSkipHeaderRecord(false));
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
				int numberOfPersons = nofPersons(csvRecord.get(numberOfPersonsCol));
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
		CSVParser csvParser = new CSVParser(Files.newBufferedReader(csvFile), CSVFormat.EXCEL.withSkipHeaderRecord(false));
		int row = -1;
		int dataRow = Integer.parseInt(params.get("DataStartRow"));
		int percentageCol = Integer.parseInt(params.get("PercentageColumn"));
		int ageColumn = Integer.parseInt(params.get("AgeColumn"));

		Map<Integer, Double> ageDist = new LinkedHashMap<>();
		for (CSVRecord csvRecord : csvParser) {
			row++;
			if (row >= dataRow) {
				if ((csvRecord.size()-1) < ageColumn || csvRecord.get(ageColumn) == null || csvRecord.get(ageColumn).equals("")) {
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
		csvReader.setStripChars(new String[] { "{", "}", "B" });
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

	private static PersonType getPersonType(String relationshipType) {
		PersonType ptype;
		switch (relationshipType) {
		case "Relative {B}":
			ptype = PersonType.Relative;
			break;
		case "Married {B}":
			ptype = PersonType.Married;
			break;
		case "Lone parent {B}":
			ptype = PersonType.LoneParent;
			break;
		case "U15Child {B}":
			ptype = PersonType.Child;
			break;
		case "Student {B}":
			ptype = PersonType.Child;
			break;
		case "O15Child {B}":
			ptype = PersonType.Child;
			break;
		case "GroupHhold {B}":
			ptype = PersonType.GroupHousehold;
			break;
		case "LonePerson {B}":
			ptype = PersonType.LonePerson;
			break;
		default:
			throw new Error("Unrecognised Person type");
		}
		return ptype;
	}

	private static ChildType getChildType(String relationshipType) {
		ChildType ctype = null;
		switch (relationshipType) {
		case "U15Child {B}":
			ctype = ChildType.U15Child;
			break;
		case "Student {B}":
			ctype = ChildType.Student;
			break;
		case "O15Child {B}":
			ctype = ChildType.O15Child;
			break;
		default:
			ctype = null;
			break;
		}
		return ctype;
	}

	private static int nofPersons(String nofPersons) throws IOException {
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

}

class HhRecord {
	final public int numOfPersonsPerHh;
	final public int hhCount;
	final public FamilyType primaryFamilyType;
	final public int familyCountPerHousehold;

	public HhRecord(int nofPersons, int familyCountPerHh, FamilyType familyType, int hhcount) {
		this.numOfPersonsPerHh = nofPersons;
		this.familyCountPerHousehold = familyCountPerHh;
		this.hhCount = hhcount;
		this.primaryFamilyType = familyType;
	}
}

class IndRecord {
	final public PersonType personType;
	final public ChildType childType;
	final public Sex sex;
	final public AgeRange ageRange;
	final public int indCount;

	public IndRecord(PersonType type, ChildType childType, Sex sex, AgeRange agerange, int individualscount) {
		this.ageRange = agerange;
		this.sex = sex;
		this.personType = type;
		this.childType = childType;
		this.indCount = individualscount;
		if (individualscount > 0) {
			this.ageRange.markNotEmpty(true);
		}
	}
}