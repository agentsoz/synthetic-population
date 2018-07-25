package io.github.agentsoz.syntheticpop.filemanager;

/*-
 * #%L
 * Synthetic Population Construction for Australia
 * %%
 * Copyright (C) 2016 - 2018 by its authors. See AUTHORS file.
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

import static java.nio.file.FileVisitResult.CONTINUE;

/**
 * Sample code that finds files that match the specified glob pattern.
 * For more information on what constitutes a glob pattern, see
 * https://docs.oracle.com/javase/tutorial/essential/io/fileOps.html#glob
 *
 * The file or directories that match the pattern are printed to
 * standard out.  The number of matches is also printed.
 *
 * When executing this application, you must put the glob pattern
 * in quotes, so the shell will not expand any wild cards:
 *              java Find . -name "*.java"
 */
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;


/**
 * @author Bhagya N. Wickramasinghe
 *
 */
public class Find extends SimpleFileVisitor<Path> {
	private final PathMatcher matcher;
	private int numMatches = 0;
	private ArrayList<Path> filePaths = new ArrayList<>();

	public Find(String pattern) {
		matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
	}

	public Find(String pattern, FileSystem fs) {
		matcher = fs.getPathMatcher("glob:" + pattern);

	}

	// Compares the glob pattern against
	// the file or directory name.
	public void find(Path file) {
		Path name = file.getFileName();
		if (name != null && matcher.matches(name)) {
			filePaths.add(file);
			numMatches++;
		}
	}

	// Prints the total number of
	// matches to standard out.
	public void done() {
		System.out.println("Matched: " + numMatches);
	}

	// Invoke the pattern matching
	// method on each file.
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
		find(file);
		return CONTINUE;
	}

	// Invoke the pattern matching
	// method on each directory.
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
		find(dir);
		return CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) {
		System.err.println(exc);
		return CONTINUE;
	}

	public ArrayList<Path> getFilePaths() {
		return this.filePaths;
	}

}
