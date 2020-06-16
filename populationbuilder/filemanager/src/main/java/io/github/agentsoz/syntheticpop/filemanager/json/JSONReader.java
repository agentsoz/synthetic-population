package io.github.agentsoz.syntheticpop.filemanager.json;

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
