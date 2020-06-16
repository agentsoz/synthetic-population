package io.github.agentsoz.syntheticpop.addressmapper;

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

import io.github.agentsoz.syntheticpop.filemanager.csv.CSVReader;
import io.github.agentsoz.syntheticpop.filemanager.csv.CSVWriter;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.GZIPOutputStream;

/**
 * @author wniroshan 30 Apr 2018
 */
public class HouseholdUtil {

    /**
     * Reads the Households from the specified reader and group them by the SA1 codes
     *
     * @param hhFileReader The reader for households csv
     * @return The households grouped by the Statistical area codes of the specified saLevel
     * @throws IOException When reading the csv file
     */
    static Map<String, List<LinkedHashMap<String, Object>>> readHouseholds(Reader hhFileReader) throws IOException {

        Map<String, List<LinkedHashMap<String, Object>>> hhBySA = new HashMap<>();

        CSVReader csvReader = new CSVReader();
        List<LinkedHashMap<String, Object>> hhs = csvReader.readCsvGroupByRow(hhFileReader, 0);

        for (LinkedHashMap<String, Object> h : hhs) {
            String sa1MainCode = getSA1MainCode(h);
            hhBySA.computeIfAbsent(sa1MainCode, v -> new ArrayList<>()).add(h);
        }
        return hhBySA;
    }

    static void saveUpdatedHouseholds(Map<String, List<LinkedHashMap<String, Object>>> hhsBySA1, Path hhFile) throws IOException {
        CSVWriter csvWriter = new CSVWriter();
        List<LinkedHashMap<String, Object>> households = hhsBySA1.values()
                                                                 .stream()
                                                                 .flatMap(List::stream)
                                                                 .collect(Collectors.toList());
        try (OutputStreamWriter csvBW = new OutputStreamWriter(new GZIPOutputStream(new BufferedOutputStream(Files.newOutputStream
                (hhFile))))) {
            csvWriter.writeLinkedMapAsCsv(csvBW, households);
        }
    }

    static String getSA1MainCode(LinkedHashMap<String, Object> h) throws IllegalArgumentException  {
        String sa1Code = (String)h.get("SA1_7DIGCODE");
        if(sa1Code.isEmpty()){
            throw new  IllegalArgumentException("SA1_7DIGCODE is not set in households file");
        }else {
            return h.get("SA2_MAINCODE") + ((String) sa1Code).substring(5);
        }
    }
}

