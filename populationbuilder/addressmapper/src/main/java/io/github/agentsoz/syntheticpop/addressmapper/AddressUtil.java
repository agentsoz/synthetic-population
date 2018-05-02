package io.github.agentsoz.syntheticpop.addressmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.agentsoz.syntheticpop.addressmapper.models.Address;
import io.github.agentsoz.syntheticpop.filemanager.json.JSONWriter;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.geojson.feature.FeatureJSON;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Bhagya N. Wickramasinghe
 */
public class AddressUtil {

    /**
     * Converts address SimpleFeature to a Address POJO
     *
     * @param address SimpleFeature to be converted
     * @return Address POJO after converting
     * @throws IOException When converting SimpleFeature to a POJO
     */
    private static Address map2POJO(SimpleFeature address) throws IOException {

        FeatureJSON fJson = new FeatureJSON();
        StringWriter writer = new StringWriter();
        fJson.writeFeature(address, writer);

        String json = writer.toString();
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(json, Address.class);
    }

    static List<Address> map2POJO(SimpleFeatureCollection addresses) throws IOException {
        List<Address> pojoAddresses = new ArrayList<>(addresses.size());
        SimpleFeatureIterator addressItr = addresses.features();
        while (addressItr.hasNext()) {
            pojoAddresses.add(map2POJO(addressItr.next()));
        }
        return pojoAddresses;
    }

    /**
     * Saves addresses to a Json file
     *
     * @param buildingsBySA1 Map of all building json objects by SA1
     * @param crs            Coordinate reference system of the geo feature points
     * @param outputFile     The output Json file
     * @throws IOException When converting to JSON and saving the file
     */
    static void saveAsJSONFile(List<Address> buildingsBySA1, CoordinateReferenceSystem crs, Path outputFile) throws IOException {

        Map<String, Object> jsonStructure = new HashMap<>();
        jsonStructure.put("features", buildingsBySA1);
        jsonStructure.put("wkt", crs.toWKT());
        JSONWriter.writeToJsonGzFile(jsonStructure, outputFile);

    }

    static List<String> getLGAsWithMatchingSACode(Map<String, Set<String>> lgasOfSA1Map, String saGranularityLevel) {
        Set<String> lgaList = new HashSet<>();

        Pattern p = Util.getSACodePattern(saGranularityLevel);
        for (String sa1Code : lgasOfSA1Map.keySet()) {
            Matcher m = p.matcher(sa1Code);
            if (m.matches()) {
                if (m.group(1) != null) {
                    lgaList.addAll(lgasOfSA1Map.get(sa1Code));
                }
            }
        }
        return new ArrayList<>(lgaList);
    }

}

