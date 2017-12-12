/**
 * 
 */
package bnw.abm.intg.filemanager.zip;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bnw.abm.intg.filemanager.Find;


/**
 * @author Bhagya N. Wickramasinghe
 *
 */
public class Zip {

	private static List<FileSystem> fileSystems = new ArrayList<>();

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

	public static List<Path> findFiles(Path jarFile, String pattern) throws IOException {
		
		Path currDir = Paths.get("").toAbsolutePath();
		jarFile = currDir.resolve(jarFile);
		
		HashMap<Path, FileSystem> fileSystems = new HashMap<Path, FileSystem>();
		FileSystem fs = fileSystems.get(jarFile);
		

		if (fs == null) {
			// throw new FileNotFoundException(jarFile.toString());
			Map<String, String> env = new HashMap<>();
			env.put("create", "false");
			try {
				fs = FileSystems.newFileSystem(URI.create("jar:file:" + jarFile), env);
			} catch (FileSystemNotFoundException iae) {
				throw new FileNotFoundException(jarFile.toString());
			}
			fileSystems.put(jarFile, fs);
		}

		Find finder = new Find(pattern, fs);

		for (Path root : fs.getRootDirectories()) {
			Files.walkFileTree(root, finder);
		}

		ArrayList<Path> files = finder.getFilePaths();
		Zip.fileSystems.add(fs);
		return files;
	}

	public static void closeOpenedFileSystems() throws IOException {
		for (FileSystem fs : fileSystems) {
			fs.close();
		}
	}
}