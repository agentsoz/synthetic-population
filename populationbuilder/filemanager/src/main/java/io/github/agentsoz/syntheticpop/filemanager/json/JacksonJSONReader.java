package io.github.agentsoz.syntheticpop.filemanager.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class JacksonJSONReader implements JSONReadable {
    private static void closeSafely(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                // Ignore
            }
        }
    }

    @Override
    public Map<String, ArrayList<Map>> readJSON(Path jsonFile)
            throws JsonParseException, JsonMappingException, IOException {
        Reader fileReader = Files.newBufferedReader(jsonFile);
        ObjectMapper mapper = new ObjectMapper();

        @SuppressWarnings("unchecked")
        Map<String, ArrayList<Map>> addressData = mapper.readValue(fileReader, Map.class);

        return addressData;
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

}
