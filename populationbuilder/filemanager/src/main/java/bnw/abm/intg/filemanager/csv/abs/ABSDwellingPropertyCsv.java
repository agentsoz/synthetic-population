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
public class ABSDwellingPropertyCsv {
	private static int sa1codeCol = 1, firstDataRow = 5, lastDataRow = 588484, valueCol = 6;
	private static int beddCol = 2, strdCol = 3, tenlldCol = 4, nprdCol = 5;

	public static Map<String, List<DwellingType>> read(Path filePath) throws IOException {
		Map<String, List<DwellingType>> dwellingProperties = new HashMap<>();
		String bedd = "", strd = "", tenlld = "", nprd = "", sa1code = "";
		Reader extractedCsvReader = Zip.read(filePath, BNWFiles.getFileName(filePath) + ".csv");

		CSVParser parser = new CSVParser(extractedCsvReader, CSVFormat.DEFAULT.withSkipHeaderRecord());
		int row = 0;
		for (CSVRecord record : parser) {
			row++;
			if (row < firstDataRow | row > lastDataRow) {
				continue;
			}

			if (!record.get(sa1codeCol).equals("")) {
				sa1code = record.get(sa1codeCol);
			}
			if (!record.get(beddCol).equals("")) {
				bedd = record.get(beddCol).split("\\{")[0].trim();
			}
			if (!record.get(strdCol).equals("")) {
				strd = record.get(strdCol).split("\\{")[0].trim();
			}
			if (!record.get(tenlldCol).equals("")) {
				tenlld = record.get(tenlldCol).split("\\{")[0].trim();
			}
			if (!record.get(nprdCol).equals("")) {
				nprd = record.get(nprdCol).split("\\{")[0].trim();
			}

			DwellingType property = new DwellingType(bedd + ":" + strd + ":" + tenlld + ":" + nprd,
					record.get(valueCol));

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