/**
 *
 */
package io.github.agentsoz.syntheticpop.filemanager.zip;

import io.github.agentsoz.syntheticpop.filemanager.FileUtils;
import io.github.agentsoz.syntheticpop.filemanager.Find;
import io.github.agentsoz.syntheticpop.util.LambdaCheckedException;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


/**
 * @author Bhagya N. Wickramasinghe
 */
public class Zip {

    /**
     * Returns a java.io.Reader for the specifed file in the zipFile
     *
     * @param zipFile   The path to the zipFile
     * @param fileInZip The file inside the zip file
     * @return A reader instance for the file in the zip
     * @throws IOException If a Reader instance cannot be instantiated for the specified file
     */
    public static Reader read(Path zipFile, String fileInZip) throws IOException {
        FileSystem fs = FileSystems.newFileSystem(zipFile, null);
        return Files.newBufferedReader(fs.getPath(fileInZip));
    }

    /**
     * /** Creates a zip file with the specified files
     *
     * @param zipFile        The output file
     * @param componentFiles The files to add to the zip file
     * @param deleteOriginal Set true to delete the original files
     * @throws IOException        When reading and writing files
     * @throws URISyntaxException If the URI of the zipFile cannot be obtained
     */
    public static void archiveFiles(Path zipFile,
                                    List<Path> componentFiles,
                                    boolean deleteOriginal) throws IOException, URISyntaxException {
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

        if (deleteOriginal) {
            FileUtils.delete(componentFiles);
        }

    }

    /**
     * Creates a zip file with the specified directories and files in them
     *
     * @param zipFile        The output file
     * @param directories    The directories to add to the zip file
     * @throws IOException When reading and writing files
     */
    public static void archiveDirectories(Path zipFile, List<Path> directories) throws IOException {
        if (Files.exists(zipFile)) {
            Files.delete(zipFile);
        }
        Files.createFile(zipFile);

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile))) {
            for (Path sourcePath : directories) {
                Files.walk(sourcePath)
                     .filter(path -> !Files.isDirectory(path))
                     .forEach(LambdaCheckedException.handlingConsumerWrapper(path -> {
                         ZipEntry zipEntry = new ZipEntry(sourcePath.getParent().relativize(path).toString());
                         zipOutputStream.putNextEntry(zipEntry);
                         zipOutputStream.write(Files.readAllBytes(path));
                         zipOutputStream.closeEntry();
                     }, IOException.class));
            }
        }
    }

    /**
     * Finds a file with the specified name pattern in a zip file
     *
     * @param zipFile The zip file to search in
     * @param pattern The pattern in the file inside the zip file
     * @return List of located files
     * @throws IOException When reading the zip file
     */
    public static List<Path> findFiles(Path zipFile, String pattern) throws IOException {

        Path currDir = Paths.get("").toAbsolutePath();
        zipFile = currDir.resolve(zipFile);

        HashMap<Path, FileSystem> fileSystems = new HashMap<>();
        FileSystem fs = fileSystems.get(zipFile);
        if (fs == null) {
            // throw new FileNotFoundException(jarFile.toString());
            Map<String, String> env = new HashMap<>();
            env.put("create", "false");
            fs = FileSystems.newFileSystem(URI.create("jar:file:" + zipFile), env);
            fileSystems.put(zipFile, fs);
        }

        Find finder = new Find(pattern, fs);

        for (Path root : fs.getRootDirectories()) {
            Files.walkFileTree(root, finder);
        }

        ArrayList<Path> files = finder.getFilePaths();
        fs.close();
        return files;
    }

    public static Path findFile(Path zipPath, String fileName) throws IOException {

        ZipFile zipFile = new ZipFile(zipPath.toFile());
        // get an enumeration of the ZIP file entries
        Enumeration<? extends ZipEntry> e = zipFile.entries();


        while (e.hasMoreElements()) {
            ZipEntry entry = e.nextElement();

            // get the name of the entry
            String entryName = entry.getName();

            if (entryName.contains(fileName)) {
                return Paths.get(entryName);
            }

        }
        return null;
    }
}