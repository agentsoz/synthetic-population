package io.github.agentsoz.syntheticpop.filemanager.csv.abs;

/*-
 * #%L
 * Synthetic Population Construction for Australia
 * %%
 * Copyright (C) 2016 - 2020 by its authors. See AUTHORS file.
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

import io.github.agentsoz.syntheticpop.filemanager.FileUtils;
import io.github.agentsoz.syntheticpop.filemanager.csv.abs.models.DwellingType;
import io.github.agentsoz.syntheticpop.filemanager.zip.Zip;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Reader extractedCsvReader = Zip.read(filePath, FileUtils.getFileName(filePath) + ".csv");

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
