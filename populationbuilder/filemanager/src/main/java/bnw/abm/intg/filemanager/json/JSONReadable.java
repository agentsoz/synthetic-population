package bnw.abm.intg.filemanager.json;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;

public interface JSONReadable {
	public Map readJSON(Path jsonFile) throws IOException;

	public Map<String, ArrayList<Map>> readJSONGz(Path jsonGzFile) throws IOException;
}
