package io.github.agentsoz.syntheticpop.addressmapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.agentsoz.syntheticpop.addressmapper.models.Address;
import io.github.agentsoz.syntheticpop.filemanager.json.JSONReader;
import io.github.agentsoz.syntheticpop.filemanager.json.JSONWriter;
import org.geotools.geojson.feature.FeatureJSON;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

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
    static Address map2POJO(SimpleFeature address) throws IOException {

        FeatureJSON fJson = new FeatureJSON();
        StringWriter writer = new StringWriter();
        fJson.writeFeature(address, writer);

        String json = writer.toString();
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(json, Address.class);
    }

    /**
     * Saves addresses to a Json file
     *
     * @param addressesBySA1 Map of all building json objects by SA1
     * @param crs            Coordinate reference system of the geo feature points
     * @param outputFile     The output Json file
     * @throws IOException When converting to JSON and saving the file
     */
    static void saveAsJSONFile(Map<String, Set<Address>> addressesBySA1,
                               CoordinateReferenceSystem crs,
                               Path outputFile) throws IOException {

        Map<String, Object> jsonStructure = new HashMap<>();
        jsonStructure.put("features", addressesBySA1);
        jsonStructure.put("wkt", crs.toWKT());
        JSONWriter.writeToJsonGzFile(jsonStructure, outputFile);

    }

    static Map<String, List<Address>> loadAddresses(Path addressJson) throws IOException {

        Map<String, List<Address>> addressesBySA1 = null;
        TypeReference<Map<String, List<Address>>> typeRef = new TypeReference<Map<String, List<Address>>>() {};
        try (GZIPInputStream gzipIs = new GZIPInputStream(new BufferedInputStream(Files.newInputStream(addressJson)))) {
            addressesBySA1 = new JSONReader().readLargeJSON(gzipIs, "features", typeRef);
        }
        return addressesBySA1;
    }
}

