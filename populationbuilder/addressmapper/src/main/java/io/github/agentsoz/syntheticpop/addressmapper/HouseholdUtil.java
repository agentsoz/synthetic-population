package io.github.agentsoz.syntheticpop.addressmapper;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.agentsoz.syntheticpop.addressmapper.models.Address;
import io.github.agentsoz.syntheticpop.filemanager.csv.CSVReader;
import io.github.agentsoz.syntheticpop.filemanager.json.JacksonJSONReader;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wniroshan 30 Apr 2018
 */
public class HouseholdUtil {

    private static Map<String, List<Address>> loadAddresses(Path addressJson) throws IOException {

        TypeReference<HashMap<String, List<Address>>> typeRef = new TypeReference<HashMap<String, List<Address>>>() {
        };
        return new JacksonJSONReader().readJSONGz(addressJson, typeRef);
    }


    private static Map<String, List<Address>> groupBySACode(List<Address> addresses, String saType) {
        Pattern saPattern = Util.getSACodePattern(saType);
        Map<String, List<Address>> addressesBySA = new HashMap<>();

        for (Address a : addresses) {
            Matcher m = saPattern.matcher(a.getProperties().getSA1_MAIN16());
            if (m.matches()) {
                String saCode = m.group(1);
                addressesBySA.computeIfAbsent(saCode, v -> new ArrayList<>()).add(a);
            }

        }
        return addressesBySA;
    }

    static Map<String, List<Address>> getAddressesBySA(Path addressJson, String saLevel) throws IOException {

        List<Address> allAddresses = loadAddresses(addressJson).get("features");
        Map<String, List<Address>> addressesBySA = groupBySACode(allAddresses, saLevel);
        return addressesBySA;
    }



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
        List<LinkedHashMap<String,Object>> hhs = csvReader.readCsvGroupByRow(hhFileReader, 0);

        for(LinkedHashMap<String, Object> h : hhs){
            String sa1MainCode = h.get("SA2_MAINCODE") + ((String)h.get("SA1_7DIGCODE")).substring(5);
            hhBySA.computeIfAbsent(sa1MainCode, v -> new ArrayList<>()).add(h);
        }
        return hhBySA;
    }
}

