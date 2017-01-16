/**
 * 
 */
package bnw.abm.intg.filemanager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bnw.abm.intg.util.BNWLogger;

/**
 * @author Bhagya N. Wickramasinghe
 *
 */
public class BNWFiles {
	private static final Logger LOGGER = BNWLogger.getLogger("bnw.abm.intg.util");

	public static List<Path> find(Path startDir, String pattern) {
		Find finder = new Find(pattern);
		try {
			Files.walkFileTree(startDir, finder);
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, ex.toString(), ex);
		}
		List<Path> files = finder.getFilePaths();
		return files;
	}

	public static String getFileName(Path filename){
		return filename.getFileName().toString().split("\\.(?=[^\\.]+$)")[0];
	}

	public static String getFileExtention(Path filename){
		return filename.getFileName().toString().split("\\.(?=[^\\.]+$)")[1];
	}

}
