package io.github.agentsoz.syntheticpop.util;

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