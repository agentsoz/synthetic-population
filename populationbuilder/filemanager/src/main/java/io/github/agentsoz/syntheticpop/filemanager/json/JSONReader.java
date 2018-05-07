package io.github.agentsoz.syntheticpop.filemanager.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class JSONReader {

    public Map<String, ArrayList<Map>> readJSON(Path jsonFile) throws IOException {
        Reader fileReader = Files.newBufferedReader(jsonFile);
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(fileReader, Map.class);
    }

    public Map<String, ArrayList<Map>> readJSONGz(Path jsonGzFile) throws IOException {

        Map<String, ArrayList<Map>> jsonObject = null;
        try (GZIPInputStream gzipIs = new GZIPInputStream(new BufferedInputStream(Files.newInputStream(jsonGzFile)))) {
            ObjectMapper mapper = new ObjectMapper();
            jsonObject = mapper.readValue(gzipIs, Map.class);
        }

        return jsonObject;
    }

    public <E> E readJSONGz(Path jsonGzFile, TypeReference<E> typeReference) throws IOException {

        E jsonObject = null;
        try (GZIPInputStream gzipIs = new GZIPInputStream(new BufferedInputStream(Files.newInputStream(jsonGzFile)))) {
            ObjectMapper mapper = new ObjectMapper();
            jsonObject = mapper.readValue(gzipIs, typeReference);
        }

        return jsonObject;
    }

    /**
     * This method parse JSON String by using Jackson Streaming API example. Returns the value json object of the jsonObjectName
     *
     * @param inputStream    InputStream for the json file
     * @param jsonObjectName The parent token of the json object
     * @param typeReference  TypeReference object for the json object
     * @param <E>            Type of the json object
     * @return Json object
     * @throws IOException If json object reading fails
     */
    public <E> E readLargeJSON(InputStream inputStream, String jsonObjectName, TypeReference<E> typeReference) throws IOException {

        JsonFactory jsonfactory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper();
        JsonParser parser = jsonfactory.createParser(inputStream);
        E values = null;

        parser.nextToken(); // JsonToken.START_OBJECT;
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            if (parser.getCurrentName().equals(jsonObjectName)) {
                parser.nextToken();
                values = mapper.readValue(parser, typeReference);
                break;
            } else {
                parser.skipChildren();
            }
        }
        return values;
    }

}
