package io.github.agentsoz.syntheticpop.filemanager.csv;

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

import io.github.agentsoz.syntheticpop.util.Log;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.util.*;


/**
 * @author Bhagya N. Wickramasinghe
 */
public class CSVReader {


    CSVFormat csvFileFormat;
    private String[] stripChars = null;

    public CSVReader() {
        csvFileFormat = CSVFormat.EXCEL.withHeader();
    }

    public CSVReader(CSVFormat csvFormat) {
        csvFileFormat = csvFormat;
    }

    /**
     * Reads csv file row by row and returns an ArrayList of LinkedHashMaps. LinkedHashMap entry contains String key-value pairs
     *
     * @param csvf   FileReader object of csv file
     * @param titles String array of known titles in csv file
     * @return ArrayList of csv rows
     * @throws IOException If there is a problem reading the csv file
     */
    public ArrayList<LinkedHashMap<String, Object>> readCsvGroupByRow(Reader csvf, String[] titles) throws IOException {
        CSVParser parser = new CSVParser(csvf, csvFileFormat);

        // Iterable<CSVParser> records =
        // CSVFormat.EXCEL.withHeader().parse(csvf);
        LinkedHashMap<String, Object> recordMap = null;
        ArrayList<LinkedHashMap<String, Object>> allRecords = new ArrayList<LinkedHashMap<String, Object>>();
        for (CSVRecord csvRecord : parser) {
            recordMap = new LinkedHashMap<String, Object>();
            for (int i = 0; i < titles.length; i++) {
                Object value = csvRecord.get(titles[i]);
                value = processValue(value, titles[i]);
                recordMap.put(titles[i], value);

            }
            allRecords.add(recordMap);
        }
        parser.close();
        return allRecords;
    }

    /**
     * Reads csv file row by row and returns an ArrayList of LinkedHashMaps. LinkedHashMap entry contains String key-value pairs
     *
     * @param csvf        FileReader object of csv file
     * @param titles      String array of known titles in csv file
     * @param uniqueTitle Index of the title to be used as key - must be unique otherwise records will be lost
     * @return Map of csv entries with uniqueTitle as key
     * @throws IOException If there is a problem reading the csv file
     */
    public HashMap<String, LinkedHashMap<String, Object>> readCsvGroupByRow(Reader csvf,
                                                                            String[] titles,
                                                                            int uniqueTitle) throws IOException {
        CSVParser parser = new CSVParser(csvf, csvFileFormat);

        // Iterable<CSVParser> records =
        // CSVFormat.EXCEL.withHeader().parse(csvf);
        LinkedHashMap<String, Object> recordMap = null;
        HashMap<String, LinkedHashMap<String, Object>> allRecords = new HashMap<String, LinkedHashMap<String, Object>>();
        for (CSVRecord csvRecord : parser) {
            recordMap = new LinkedHashMap<String, Object>();
            for (int i = 0; i < titles.length; i++) {
                Object value = csvRecord.get(titles[i]);
                value = processValue(value, titles[i]);
                recordMap.put(titles[i], value);

            }
            allRecords.put(titles[uniqueTitle], recordMap);
        }
        parser.close();
        return allRecords;
    }

    public LinkedHashMap<String, List<Object>> readABSCsvByColumn(Reader csvr, int titleRow, int lastRow, int firstCol, int maxCol)
            throws IOException {
        LinkedHashMap<String, List<Object>> allRecords = new LinkedHashMap<>();

        int i = 1;
        CSVParser parser = new CSVParser(csvr, csvFileFormat.withIgnoreEmptyLines(false));
        String[] titles = new String[maxCol - firstCol + 1];
        for (CSVRecord csvRecord : parser) {
            if (i < titleRow) {
                i++;
                continue;
            } else if (i > lastRow) {
                break;
            }
            for (int j = firstCol; j <= maxCol; j++) {
                if (i == titleRow) {
                    allRecords.put(csvRecord.get(j), new ArrayList<>());
                    titles[j - firstCol] = csvRecord.get(j);
                } else {
                    allRecords.get(titles[j - firstCol]).add(processValue(csvRecord.get(j)));
                }
            }
            i++;

        }
        parser.close();
        return allRecords;
    }

    /**
     * This is used to process csv files downloaded from ABS. The function expects last column to be the only column with values. Other
     * preceding columns are expected to have strings. These are treaded as row headers and used as keys of a series of Map objects. This
     * function ignores cv column titles.
     * <p>
     * |rowhead1|rowhead1.1|rowhead1.1.1|value|<br /> |........|..........|rowhead1.1.2|value|<br />
     * |........|rowhead1.2|rowhead1.2.1|value|<br /> |........|..........|rowhead1.2.2|value|<br />
     * |rowhead2|rowhead2.1|rowhead2.1.1|value|<br /> |........|..........|rowhead2.1.2|value|<br /> ...
     *
     * @param csvf     Reader object of csv file
     * @param titlerow 0 based index of title row
     * @param endrow   0 based index of the last row to read. -1 read till end
     * @param firstCol 0 based index of starting column
     * @param lastCol  0 based index of the last column to read
     * @return Series of nested HashMaps with data in csv file
     * @throws IOException
     */
    public LinkedHashMap<String, Object> readABSCsvAsMap(Reader csvf,
                                                         int titlerow,
                                                         int endrow,
                                                         int firstCol,
                                                         int lastCol) throws IOException {
        Log.info("Reading ABS csv file as a Map ");
        CSVParser parser = new CSVParser(csvf, csvFileFormat);

        LinkedHashMap<String, Object> recordMap = new LinkedHashMap<>();
        new LinkedHashMap<String, LinkedHashMap<String, Object>>();
        int i = 0;

        String[] keys = new String[lastCol];
        for (CSVRecord csvRecord : parser) {
            i++;
            if (i <= titlerow) {
                continue;
            }
            this.readKeyVal(csvRecord, recordMap, firstCol, keys);
            if (i == endrow) {
                break;
            }
        }
        parser.close();
        return recordMap;
    }

    private void readKeyVal(CSVRecord csvRec, LinkedHashMap<String, Object> map, int keyCol, String[] keys) throws IOException {

        if (map == null) {
            map = new LinkedHashMap<>();
        }
        LinkedHashMap<String, Object> procMap = map;

        for (int i = 0; i < csvRec.size(); i++) {
            String str = csvRec.get(i);

            if (i < keyCol) {
                continue;
            }

            if (i == keys.length - 1) { // 0 based indices. We use last 2 cols
                procMap.put(csvRec.get(i), csvRec.get(++i));
                break;
            }
            if (!(str.equals("") || str == null)) {
                LinkedHashMap<String, Object> subMap = new LinkedHashMap<>();
                if (procMap == null) {
                    System.out.println();
                }
                procMap.put(str, subMap);
                keys[i - keyCol] = str;
                procMap = subMap;
            } else {
                if (procMap == null) {
                    break;
                }
                if (procMap.get(keys[i - keyCol]) == null) {
                    Log.info("Something wrong with the file. " + keys[i - keyCol] + " has no value");
                    throw new IOException("Something wrong with the file. " + keys[i - keyCol] + " has no value");
                }
                procMap = (LinkedHashMap<String, Object>) procMap.get(keys[i - keyCol]);
            }

        }
    }

    public ArrayList<LinkedHashMap<String, Object>> readCsvGroupByRow(Reader csvf, final int titleRow) throws IOException {
        CSVParser parser = new CSVParser(csvf, CSVFormat.EXCEL.withSkipHeaderRecord(true));

        // Iterable<CSVParser> records =
        // CSVFormat.EXCEL.withHeader().parse(csvf);
        LinkedHashMap<String, Object> recordMap = null;
        ArrayList<LinkedHashMap<String, Object>> allRecords = new ArrayList<LinkedHashMap<String, Object>>();
        String[] titles = null;
        int row = -1;
        for (CSVRecord csvRecord : parser) {
            row++;
            if (titleRow == row) {
                titles = new String[csvRecord.size()];
                for (int i = 0; i < csvRecord.size(); i++) {
                    titles[i] = csvRecord.get(i);
                }
                continue;
            }

            if (titles != null) {
                recordMap = new LinkedHashMap<>();
                for (int i = 0; i < titles.length; i++) {
                    Object value = csvRecord.get(i);
                    value = processValue(value);
                    recordMap.put(titles[i], value);
                }
                allRecords.add(recordMap);
            }
        }
        parser.close();
        return allRecords;
    }

    public HashMap<String, LinkedHashMap<String, Object>> readCsvGroupByRow(Reader csvf, String uniqueColName) throws IOException {
        CSVParser parser = new CSVParser(csvf, csvFileFormat);

        HashMap<String, LinkedHashMap<String, Object>> allRecords = new HashMap<>();
        LinkedHashMap<String, Object> recordMap = null;
        for (CSVRecord csvRecord : parser) {
            recordMap = new LinkedHashMap<>();
            Map<String, String> record = csvRecord.toMap();
            for (String key : parser.getHeaderMap().keySet()) {
                Object val = record.get(key);
                val = processValue(val);
                recordMap.put(key, val);
            }
            allRecords.put(csvRecord.get(uniqueColName), recordMap);
        }
        parser.close();
        csvf.close();
        return allRecords;
    }

    public List<List<String>> readCsvRows(Reader csvf) throws IOException {
        CSVParser parser = new CSVParser(csvf, csvFileFormat);
        // Iterable<CSVParser> records =
        // CSVFormat.EXCEL.withHeader().parse(csvf);
        List<List<String>> records = new ArrayList<>();
        for (CSVRecord csvRecord : parser) {
            ArrayList<String> entry = new ArrayList<>();
            int len = csvRecord.size();

            for (int i = 0; i < len; i++) {
                Object value = csvRecord.get(i);
                value = processValue(value);
                entry.add((String) value);
            }
            records.add(entry);
        }
        parser.close();
        csvf.close();
        return records;
    }

    /**
     * Reads csv file column by column and returns a LinkedHashMap of column-headers(key) and column-values(values-ArrayList<String>)
     *
     * @param csvf   Reader object of csv file
     * @param titles String array of known titles in csv file
     * @return LinkedHashMap of column headers and values
     * @throws IOException If there is a problem reading csv file
     */
    public LinkedHashMap<String, ArrayList<String>> readCsvGroupByColumn(Reader csvf, String[] titles) throws IOException {
        LinkedHashMap<String, ArrayList<String>> allRecords = new LinkedHashMap<String, ArrayList<String>>();
        for (String header : titles) {
            allRecords.put(header, new ArrayList<String>());
        }

        CSVParser parser = new CSVParser(csvf, csvFileFormat);
        for (CSVRecord csvRecord : parser) {
            for (String header : titles) {
                allRecords.get(header).add(csvRecord.get(header));
            }
        }
        parser.close();
        csvf.close();
        return allRecords;
    }

    public String[] getStripChars() {
        return stripChars;
    }

    /**
     * Strings to be removed form a cell value
     *
     * @param stripChars array of strings to be removed when processing the csv
     */
    public void setStripChars(String[] stripChars) {
        this.stripChars = stripChars;
    }

    Object processValue(Object value, Object... args) {
        // Array of value can be placed inside square brackets [] as comma
        // separated elements
        if (stripChars == null) {
            return value;
        }
        if (value == null) {
            return null;
        }
        for (String str : stripChars) {// Remove the characters in stripchars
            // from value
            value = value.toString().replace(str, "");
        }
        String[] parts = value.toString().split(",");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        if (parts.length > 1) {
            return new ArrayList<>(Arrays.asList(parts));
        } else {
            return parts[0];
        }
    }
}
