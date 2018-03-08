/**
 *
 */
package bnw.abm.intg.filemanager;

import bnw.abm.intg.util.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Bhagya N. Wickramasinghe
 */
public class FileUtils {

    public static List<Path> find(Path startDir, String pattern) {
        Find finder = new Find(pattern);
        try {
            Files.walkFileTree(startDir, finder);
        } catch (IOException ex) {
            Log.error("When trying to walk file tree", ex);
        }
        List<Path> files = finder.getFilePaths();
        return files;
    }

    public static String getFileName(Path filename) {
        return filename.getFileName().toString().split("\\.(?=[^\\.]+$)")[0];
    }

    public static String getFileExtention(Path filename) {
        return filename.getFileName().toString().split("\\.(?=[^\\.]+$)")[1];
    }

}
