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

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.GZIPOutputStream;

public class JSONWriter {

    /**
     * Writes the arrList to a file as a json formatted byte array
     *
     * @param jsonObj  Json object to write
     * @param filePath full file path to the output json file
     * @throws JsonMappingException    arrList cannot be converted to json
     * @throws JsonGenerationException arrList cannot be converted to json
     * @throws IOException             arrList cannot be converted to json or I/O exception when writing the output file
     */
    public static void writeListToJsonFile(Object jsonObj, Path filePath) throws IOException {

        final OutputStream out = new ByteArrayOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, jsonObj);

        final byte[] data = ((ByteArrayOutputStream) out).toByteArray();

        Files.write(filePath, data);

    }

    /**
     * Writes the JSON file as compressed GZip file
     *
     * @param jsonObj  Json object to write
     * @param filePath full file path to the output json file
     * @throws JsonMappingException    arrList cannot be converted to json
     * @throws JsonGenerationException arrList cannot be converted to json
     * @throws IOException             arrList cannot be converted to json or I/O exception when writing the output file
     */
    public static void writeToJsonGzFile(Object jsonObj, Path filePath) throws IOException {

        GZIPOutputStream gzipOS = new GZIPOutputStream(Files.newOutputStream(filePath));
        WritableByteChannel out = Channels.newChannel(gzipOS);

        ObjectMapper mapper = new ObjectMapper();
        byte[] jsonOut = mapper.writeValueAsBytes(jsonObj);
        out.write(ByteBuffer.wrap(jsonOut));
        out.close();

    }
}
