package bnw.abm.intg.filemanager.csv;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.google.common.collect.Table;

/**
 * @author Bhagya N. Wickramasinghe
 *
 */
public class CSVWriter {

	/**
	 * @param fileWriter
	 *            Writer object
	 * @param data
	 *            list of String list to write
	 * @throws IOException
	 *             When failing to write the file
	 */
	public void writeAsCsv(Writer fileWriter, List<List<String>> data) throws IOException {
		CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
		List<String> header = data.get(0);
		csvPrinter.printRecord(header);
		csvPrinter.printRecords(data.subList(1, data.size()));
		csvPrinter.flush();
		csvPrinter.close();
	}

	public void writeLinkedMapAsCsv(Writer fileWriter, List<LinkedHashMap<String, Object>> arrayList)
			throws IOException {
		CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
		Set<String> header = arrayList.get(0).keySet();
		List<Object> records = new ArrayList<>(arrayList.size());
		for (LinkedHashMap<String, Object> map : arrayList) {
			records.add(map.values());
		}
		csvPrinter.printRecord(header);
		csvPrinter.printRecords(records);
		csvPrinter.flush();
		csvPrinter.close();

	}

	public static CSVPrinter getCSVPrinter(Writer fileWriter) throws IOException {
		return new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
	}

	/**
	 * Writes the table to the specified writer. This calls toString() method of
	 * every key and value in the table
	 * 
	 * @param fileWriter
	 *            Writer instance where the table to be written
	 * @param table
	 *            Guava table instance
	 * @throws IOException
	 *             thrown if writing to the Writer failed
	 */
	public void writeAsCsv(Writer fileWriter, Table<?, ?, ?> table) throws IOException {
		CSVPrinter p = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
		List<String> headings = new ArrayList<>(
				table.columnKeySet().stream().map(k -> String.valueOf(k)).collect(Collectors.toList()));
		headings.add(0, "");

		List<List<String>> out = table.rowMap().entrySet().stream().map(entry -> {
			List<String> a = new ArrayList<>(Arrays.asList(String.valueOf(entry.getKey())));
			a.addAll(entry.getValue().values().stream().map(v -> String.valueOf(v)).collect(Collectors.toList()));
			return a;
		}).collect(Collectors.toList());
		List<List<String>> allEntries = new ArrayList<>();
		allEntries.add(headings);
		allEntries.addAll(out);

		writeAsCsv(fileWriter, allEntries);

	}

	public void maptoCsv(BufferedWriter fileWriter, Map map) throws IOException {
		CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
		ArrayList<ArrayList<String>> allRecords = new ArrayList<>();

		for (Object en1 : map.entrySet()) {
			String k1;
			if (((Entry) en1).getKey() instanceof Number) {
				k1 = String.valueOf(((Entry) en1).getKey());
			} else {
				k1 = String.valueOf(((Entry) en1).getKey());

			}

			Map m1 = (Map) ((Entry) en1).getValue();
			for (Object en2 : m1.entrySet()) {
				String k2;
				if (((Entry) en2).getKey() instanceof Number) {
					k2 = String.valueOf(((Entry) en2).getKey());
				} else {
					k2 = String.valueOf(((Entry) en2).getKey());
				}
				Map m2 = (Map) ((Entry) en2).getValue();
				for (Object en3 : m2.entrySet()) {
					String k3 = (String) ((Entry) en3).getKey();
					String v3;
					if (((Entry) en3).getValue() instanceof Number) {
						v3 = String.valueOf(((Entry) en3).getValue());
					} else {
						v3 = String.valueOf(((Entry) en3).getKey());
					}
					ArrayList<String> record = new ArrayList<>();
					record.add(k1);
					record.add(k2);
					record.add(k3);
					record.add(v3);
					allRecords.add(record);

				}

			}

		}
		csvPrinter.printRecords(allRecords);
		csvPrinter.flush();
		csvPrinter.close();
	}
}
