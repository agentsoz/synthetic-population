package bnw.abm.intg.filemanager.json;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonJSONReader implements JSONReadable {
	@Override
	public Map<String, ArrayList<Map>> readJSON(Path jsonFile)
			throws JsonParseException, JsonMappingException, IOException {
		Reader fileReader = Files.newBufferedReader(jsonFile);
		ObjectMapper mapper = new ObjectMapper();

		@SuppressWarnings("unchecked")
		Map<String, ArrayList<Map>> addressData = mapper.readValue(fileReader, Map.class);

		return addressData;
	}

	public Map<String, ArrayList<Map>> readJSONGz(Path jsonGzFile)
			throws JsonParseException, JsonMappingException, IOException {
		InputStream fileIs = null;
		BufferedInputStream bufferedIs = null;
		GZIPInputStream gzipIs = null;
		try {
			fileIs = Files.newInputStream(jsonGzFile);
			// Even though GZIPInputStream has a buffer it reads individual
			// bytes
			// when processing the header, better add a buffer in-between
			bufferedIs = new BufferedInputStream(fileIs);
			gzipIs = new GZIPInputStream(bufferedIs);
		} catch (IOException e) {
			closeSafely(gzipIs);
			closeSafely(bufferedIs);
			closeSafely(fileIs);
			throw new UncheckedIOException(e);
		}
		ObjectMapper mapper = new ObjectMapper();

		@SuppressWarnings("unchecked")
		Map<String, ArrayList<Map>> addressData = mapper.readValue(gzipIs, Map.class);

		return addressData;
	}

	private static void closeSafely(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				// Ignore
			}
		}
	}

}
