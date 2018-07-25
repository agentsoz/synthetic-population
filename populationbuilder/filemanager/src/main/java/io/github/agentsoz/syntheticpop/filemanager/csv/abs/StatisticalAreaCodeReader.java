package io.github.agentsoz.syntheticpop.filemanager.csv.abs;

/*-
 * #%L
 * Synthetic Population Construction for Australia
 * %%
 * Copyright (C) 2016 - 2018 by its authors. See AUTHORS file.
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

import io.github.agentsoz.syntheticpop.filemanager.zip.Zip;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Bhagya N. Wickramasinghe
 */
public class StatisticalAreaCodeReader {

    /**
     * This function loads All Australia Statistical Codes csv and creates a map
     * of values under reference column (key) and target column (value)
     *
     * @param zipWithCsv            The zip file downloaded from ABS
     * @param referenceColumnHeader Column in csv to be used as key set
     * @param targetColumnHeader    Column in csv to be used as value set
     * @return A map of key value pairs representing reference column value and
     * target column value pairs
     * @throws IOException
     */
    public static Map<String, String> loadCsvAndCreateMapWithAreaCode(Path zipWithCsv,
                                                                      String referenceColumnHeader,
                                                                      String targetColumnHeader) throws IOException {
        Map<String, String> csvRecordsMap = new LinkedHashMap<>();
        Path csvFileName = Zip.findFiles(zipWithCsv, "*.csv").get(0);
        Reader extractedCsvReader = Zip.read(zipWithCsv, csvFileName.toString());

        try (CSVParser parser = new CSVParser(extractedCsvReader, CSVFormat.EXCEL.withHeader())) {
            List<CSVRecord> csvRecords = parser.getRecords();
            for (CSVRecord rec : csvRecords) {
                if (csvRecordsMap.containsKey(rec.get(referenceColumnHeader))
                        && !csvRecordsMap.get(rec.get(referenceColumnHeader)).equals(rec.get(targetColumnHeader))) {
                    throw new Error("Inconsistent mapping: Key: " + rec.get(referenceColumnHeader) + " Current: "
                                            + csvRecordsMap.get(referenceColumnHeader) + " New: " + rec.get(
                            targetColumnHeader));
                }
                csvRecordsMap.put(rec.get(referenceColumnHeader), rec.get(targetColumnHeader));
            }
        }
        return csvRecordsMap;
    }
}
