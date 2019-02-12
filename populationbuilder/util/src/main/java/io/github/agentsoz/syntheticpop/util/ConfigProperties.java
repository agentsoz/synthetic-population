package io.github.agentsoz.syntheticpop.util;

/*-
 * #%L
 * Synthetic Population Construction for Australia
 * %%
 * Copyright (C) 2016 - 2019 by its authors. See AUTHORS file.
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Bhagya N. Wickramasinghe
 */
public class ConfigProperties extends Properties {
    /**
     *
     */
    private static final long serialVersionUID = -5371179228765375673L;

    public ConfigProperties(String propertyFile) throws IOException {
        this.loadPropertyFile(propertyFile);
    }

    /**
     * Resolves environment variables that are in a file path
     *
     * @param filePath File path String
     * @return returns filePath after replacing environment variable with correct file path
     */
    private static String resolveEnvVar(String filePath) {
        int dollar = filePath.indexOf('$');
        if (dollar != -1) {
            String envVar = filePath.substring(dollar, filePath.indexOf(File.separatorChar));
            String replacement = System.getenv(envVar.substring(1));
            if (replacement == null) {
                Log.errorAndExit(envVar.substring(1) + "  environment variable not found", GlobalConstants.ExitCode.ENVVAR);
            } else {
                filePath = filePath.replace(envVar, replacement);
            }

        }
        return filePath;
    }

    private void loadPropertyFile(String propertyFile) throws IOException {

        try (InputStream inputStream = Files.newInputStream(Paths.get(propertyFile))) {
            this.load(inputStream);
        }
    }

    /**
     * Reads property file entry as a HashMap. Each Map Entry must be separated by a comma and Key Value pair must be separated by a colon.
     * e.g. President=Name:James,Age:16.
     *
     * @param propertyName Name of the property
     * @return A HashMap of key value pairs
     */
    public Map<String, String> readKeyValuePairs(String propertyName) {
        String[] entries = readCommaSepProperties(propertyName);
        if (entries != null) {
            return splitByColon(entries);
        } else {
            return null;
        }
    }

    /**
     * Reads a property entry's values separated by commas as an array e.g. Names=Bhagya,Sewwandi
     *
     * @param propertyName name of the property
     * @return the property values
     */
    public String[] readCommaSepProperties(String propertyName) {
        String entry = this.getProperty(propertyName);
        entry = resolveEnvVar(entry);
        if (entry != null) {
            return entry.trim().split(",");
        } else {
            return null;
        }
    }

    private Map<String, String> splitByColon(String[] entries) {

        Map<String, String> kvEntries = new LinkedHashMap<>();
        for (String entry : entries) {
            String[] arr = entry.split(":");
            if (arr.length == 1 || arr[1].trim().equals("")) {
                kvEntries.put(arr[0], null);
            } else {
                kvEntries.put(arr[0], arr[1]);
            }
        }
        return kvEntries;
    }

    public Path readFileOrDirectoryPath(String propertyName) {
        String entry = this.getProperty(propertyName);
        entry = resolveEnvVar(entry);
        return Paths.get(entry);
    }

    /**
     * Reads SA names list from user input
     *
     * @param propertyName       SA list property
     * @param inputDirectory The input data directory
     * @return List of SA2s
     * @throws IOException File reading
     */
    public List<String> getSAList(String propertyName,Path inputDirectory) throws IOException {
        List<String> saList = null;
        String saParam = this.getProperty(propertyName);
        if (saParam.equals("*")) {
            saList = Files.list(inputDirectory)
                           .map(Path::getFileName)
                           .map(Path::toString)
                           .collect(Collectors.toList());
        } else if (saParam.endsWith(".txt") || Files.exists(Paths.get(saParam))) {
            saList = Files.readAllLines(Paths.get(saParam));
        } else {
            saList = Arrays.asList(saParam.split(","));
        }
        return saList;
    }

}
