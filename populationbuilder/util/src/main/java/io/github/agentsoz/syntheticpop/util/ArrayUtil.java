package io.github.agentsoz.syntheticpop.util;

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

/**
 * Custom utility functions for java arrays
 * 
 * @author Bhagya N. Wickramasinghe
 *
 */
public class ArrayUtil {

	private ArrayUtil() {
	}

	/**
	 * Gets the first element, same as array[0]
	 * 
	 * @param array
	 *            any array
	 * @return first array element
	 */
	public static <T> T getFirst(T[] array) {
		return array != null && (array.length != 0) ? array[0] : null;
	}

	/**
	 * Gets the last array element, same as array[array.length-1]. Just shorter when having to reference multiple functions
	 * 
	 * @param array
	 *            any array
	 * @return the last array element
	 */
	public static <T> T getLast(T[] array) {
		return array != null && (array.length != 0) ? array[array.length - 1] : null;
	}
}
