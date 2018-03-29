package bnw.abm.intg.util;

import bnw.abm.intg.util.GlobalConstants.ExitCode;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author Bhagya N. Wickramasinghe
 */
public class BNWProperties extends Properties {
    /**
     *
     */
    private static final long serialVersionUID = -5371179228765375673L;

    public BNWProperties(String propertyFile) throws IOException {
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
                Log.errorAndExit(envVar.substring(1) + "  environment variable not found", ExitCode.ENVVAR);
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
    public HashMap<String, String> readKeyValuePairs(String propertyName) {
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

    private HashMap<String, String> splitByColon(String[] entries) {

        HashMap<String, String> kvEntries = new HashMap<>();
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

}
