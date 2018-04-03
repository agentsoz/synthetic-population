package io.github.agentsoz.syntheticpop.filemanager.csv.abs;

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

    public enum Title {
        SA1_MAINCODE_2011,
        SA1_7DIGITCODE_2011,
        SA2_MAINCODE_2011,
        SA2_5DIGITCODE_2011,
        SA2_NAME_2011,
        SA3_CODE_2011,
        SA3_NAME_2011,
        SA4_CODE_2011,
        SA4_NAME_2011,
        GCCSA_CODE_2011,
        GCCSA_NAME_2011,
        STATE_CODE_2011,
        STATE_NAME_2011,
        AREA_ALBERS_SQM;

    }
}