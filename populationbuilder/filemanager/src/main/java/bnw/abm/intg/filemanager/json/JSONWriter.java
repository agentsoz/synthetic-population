package bnw.abm.intg.filemanager.json;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.GZIPOutputStream;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONWriter {

    /**
     * Writes the arrList to a file as a json formatted byte array
     * 
     * @param jsonObj
     *            Json object to write
     * @param filePath
     *            full file path to the output json file
     * @throws JsonMappingException
     *             arrList cannot be converted to json
     * @throws JsonGenerationException
     *             arrList cannot be converted to json
     * @throws IOException
     *             arrList cannot be converted to json or I/O exception when writing the output file
     */
    public static void writeListToJsonFile(Object jsonObj, Path filePath) throws JsonGenerationException, JsonMappingException, IOException {

        final OutputStream out = new ByteArrayOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, jsonObj);

        final byte[] data = ((ByteArrayOutputStream) out).toByteArray();

        Files.write(filePath, data);

    }

    /**
     * Writes the JSON file as compressed GZip file
     * 
     * @param jsonObj
     *            Json object to write
     * @param filePath
     *            full file path to the output json file
     * @throws JsonMappingException
     *             arrList cannot be converted to json
     * @throws JsonGenerationException
     *             arrList cannot be converted to json
     * @throws IOException
     *             arrList cannot be converted to json or I/O exception when writing the output file
     */
    public static void writeToJsonGzFile(Object jsonObj, Path filePath) throws JsonGenerationException, JsonMappingException, IOException {

        GZIPOutputStream gzipOS = new GZIPOutputStream(Files.newOutputStream(filePath));
        WritableByteChannel out = Channels.newChannel(gzipOS);

        ObjectMapper mapper = new ObjectMapper();
        byte[] jsonOut = mapper.writeValueAsBytes(jsonObj);
        out.write(ByteBuffer.wrap(jsonOut));
        out.close();

    }
}
