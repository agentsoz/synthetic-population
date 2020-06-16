package io.github.agentsoz.syntheticpop.util;

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

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * LinkedHashSet that orders elements according to the encounters. Traditional
 * LinkedHashSet does not add an element if it is already in the set. This one
 * modifies LinkedHashSet to remove the existing element instance and add new
 * instance of the same element making it appear at the top of the list.
 * 
 * @author Bhagya N. Wickramasinghe
 *
 * @param <E>
 */
public class LIFOLinkedHashSet<E> extends LinkedHashSet<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int MAX_SIZE = 10;

	public LIFOLinkedHashSet(int setMaxSize) {
		this.MAX_SIZE = setMaxSize;
	}

	@Override
	public boolean add(E e) {

		if (contains(e)) {
			remove(e);
		} else if (size() > this.MAX_SIZE) {
			remove(this.iterator().next());
		}

		return super.add(e);

	}

}
