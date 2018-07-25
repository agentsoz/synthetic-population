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

import java.util.List;

/**
 * Custom utility functions for java lists
 * 
 * @author Bhagya N. Wickramasinghe
 *
 */
public final class ListUtil {

	private ListUtil() {
	}

	/**
	 * Gets the fist element from a list, same as list.get(0). But this is shorter when having to use a list just returned from a method
	 * 
	 * @param list
	 *            any list
	 * @return the first element in list
	 */
	public static <T> T getFirst(List<T> list) {
		return list != null && !list.isEmpty() ? list.get(0) : null;
	}

	/**
	 * Gets the last element from a list, same as list.get(list.size() - 1). But this is shorter when having to use a list just returned from a
	 * method
	 * 
	 * @param list
	 *            any list
	 * @return the last element in list
	 */
	public static <T> T getLast(List<T> list) {
		return list != null && !list.isEmpty() ? list.get(list.size() - 1) : null;
	}
}
