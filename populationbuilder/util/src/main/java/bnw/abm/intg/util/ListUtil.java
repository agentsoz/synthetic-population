package bnw.abm.intg.util;

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