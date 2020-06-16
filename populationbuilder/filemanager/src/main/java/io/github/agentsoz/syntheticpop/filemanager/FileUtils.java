/**
 *
 */
package io.github.agentsoz.syntheticpop.filemanager;

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

import io.github.agentsoz.syntheticpop.util.LambdaCheckedException;
import io.github.agentsoz.syntheticpop.util.Log;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
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

    public static String getFileExtension(Path filename) {
        return filename.getFileName().toString().split("\\.(?=[^\\.]+$)")[1];
    }

    public static void delete(List<Path> filesToDelete) throws IOException {
        for (Path dir : filesToDelete) {
            Files.walk(dir, FileVisitOption.FOLLOW_LINKS)
                 .sorted(Comparator.reverseOrder())
                 .forEach(LambdaCheckedException.handlingConsumerWrapper(Files::deleteIfExists, IOException.class));

        }
    }

}
