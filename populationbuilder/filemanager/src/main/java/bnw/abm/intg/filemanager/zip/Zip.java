/**
 * 
 */
package bnw.abm.intg.filemanager.zip;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import bnw.abm.intg.filemanager.Find;
import bnw.abm.intg.util.BNWLogger;

/**
 * @author Bhagya N. Wickramasinghe
 *
 */
public class Zip {
	private static final Logger LOGGER = BNWLogger.getLogger();

	public static Reader read(Path zipFile, String fileInZip) throws IOException {
		FileSystem fs = FileSystems.newFileSystem(zipFile, null);
		return Files.newBufferedReader(fs.getPath(fileInZip));
	}

	public static void create(Path zipFile, List<Path> componentFiles) throws IOException, URISyntaxException {
		Map<String, String> env = new HashMap<>();
		env.put("create", "true");
		// locate file system by using the syntax
		// defined in java.net.JarURLConnection
		URI uri = new URI("jar:" + zipFile.toUri());

		try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
			for (Path externalFilePath : componentFiles) {
				Path filePathInZip = zipfs.getPath(externalFilePath.getFileName().toString());
				Files.copy(externalFilePath, filePathInZip, StandardCopyOption.REPLACE_EXISTING);
			}
		}
	}

	public static List<Path> findFiles(Path jarFile, String pattern) {
		HashMap<Path, FileSystem> fileSystems = new HashMap<Path, FileSystem>();
		FileSystem fs = fileSystems.get(jarFile);

		try {
			if (fs == null) {
				Map<String, String> env = new HashMap<>();
				env.put("create", "false");
				fs = FileSystems.newFileSystem(URI.create("jar:file:" + jarFile), env);
				fileSystems.put(jarFile, fs);
			}
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, ex.toString(), ex);
		}

		Find finder = new Find(pattern, fs);
		try {
			for (Path root : fs.getRootDirectories()) {
				Files.walkFileTree(root, finder);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<Path> files = finder.getFilePaths();
		return files;
	}
}