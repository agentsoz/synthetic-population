package bnw.abm.intg.util;

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