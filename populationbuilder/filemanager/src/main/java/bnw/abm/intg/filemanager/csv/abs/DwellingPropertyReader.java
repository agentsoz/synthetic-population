package bnw.abm.intg.filemanager.csv.abs;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import bnw.abm.intg.filemanager.BNWFiles;
import bnw.abm.intg.filemanager.csv.abs.models.DwellingType;
import bnw.abm.intg.filemanager.zip.Zip;

/**
 * @author Bhagya N. Wickramasinghe
 *
 */
public class DwellingPropertyReader {
	private static final int SA1_CODE_COL = 1, FIRST_DATA_ROW = 5, LAST_DATA_ROW = 588484, VALUE_COL = 6;
	private static final int BEDD_COL = 2, STRD_COL = 3, TENLLD_COL = 4, NPRD_COL = 5;

	public static Map<String, List<DwellingType>> read(Path filePath) throws IOException {
		Map<String, List<DwellingType>> dwellingProperties = new HashMap<>();
		String bedd = "", strd = "", tenlld = "", nprd = "", sa1code = "";
		Reader extractedCsvReader = Zip.read(filePath, BNWFiles.getFileName(filePath) + ".csv");

		CSVParser parser = new CSVParser(extractedCsvReader, CSVFormat.DEFAULT.withSkipHeaderRecord());
		int row = 0;
		for (CSVRecord record : parser) {
			row++;
			if (row < FIRST_DATA_ROW | row > LAST_DATA_ROW) {
				continue;
			}

			if (!record.get(SA1_CODE_COL).equals("")) {
				sa1code = record.get(SA1_CODE_COL);
			}
			if (!record.get(BEDD_COL).equals("")) {
				bedd = record.get(BEDD_COL).split("\\{")[0].trim();
			}
			if (!record.get(STRD_COL).equals("")) {
				strd = record.get(STRD_COL).split("\\{")[0].trim();
			}
			if (!record.get(TENLLD_COL).equals("")) {
				tenlld = record.get(TENLLD_COL).split("\\{")[0].trim();
			}
			if (!record.get(NPRD_COL).equals("")) {
				nprd = record.get(NPRD_COL).split("\\{")[0].trim();
			}

			DwellingType property = new DwellingType(bedd + ":" + strd + ":" + tenlld + ":" + nprd,
					record.get(VALUE_COL));

			if (dwellingProperties.containsKey(sa1code)) {
				dwellingProperties.get(sa1code).add(property);
			} else {
				List<DwellingType> propertiesList = new ArrayList<>();
				propertiesList.add(property);
				dwellingProperties.put(sa1code, propertiesList);
			}

		}
		parser.close();
		return dwellingProperties;
	}
}