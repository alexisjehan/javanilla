/*
MIT License

Copyright (c) 2018 Alexis Jehan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.github.alexisjehan.javanilla.lang.array;

import java.util.Arrays;
import java.util.List;

/**
 * <p>An utility class that provides {@code long array} tools.</p>
 * @since 1.0
 */
public final class LongArrays {

	/**
	 * <p>An empty {@code long array}.</p>
	 * @since 1.0
	 */
	public static final long[] EMPTY = new long[0];

	/**
	 * <p>Constructor not available.</p>
	 * @since 1.0
	 */
	private LongArrays() {
		// Not available
	}

	/**
	 * <p>Wrap a {@code long array} replacing {@code null} by an empty {@code long array}.</p>
	 * @param array a {@code long array} or {@code null}
	 * @return a non-{@code null} {@code long array}
	 * @since 1.0
	 */
	public static long[] nullToEmpty(final long[] array) {
		return null != array ? array : EMPTY;
	}

	/**
	 * <p>Wrap a {@code long array} replacing an empty one by {@code null}.</p>
	 * @param array a {@code long array} or {@code null}
	 * @return a non-empty {@code long array} or {@code null}
	 * @since 1.0
	 */
	public static long[] emptyToNull(final long[] array) {
		return null != array && 0 != array.length ? array : null;
	}

	/**
	 * <p>Get the first index of the {@code long} value in the given {@code long array}.</p>
	 * @param array the {@code long array} to look into
	 * @param value the {@code long} value to search
	 * @return the first index of the {@code long} value if found, {@code -1} otherwise
	 * @throws NullPointerException if the {@code long array} is {@code null}
	 * @since 1.0
	 */
	public static int indexOf(final long[] array, final long value) {
		return indexOf(array, value, 0);
	}

	/**
	 * <p>Get the first index of the {@code long} value in the given {@code long array} starting from the provided
	 * index.</p>
	 * @param array the {@code long array} to look into
	 * @param value the {@code long} value to search
	 * @param fromIndex the index to start from
	 * @return the first index of the {@code long} value if found, {@code -1} otherwise
	 * @throws NullPointerException if the {@code long array} is {@code null}
	 * @throws IndexOutOfBoundsException if the index to start from is not valid
	 * @since 1.0
	 */
	public static int indexOf(final long[] array, final long value, final int fromIndex) {
		if (null == array) {
			throw new NullPointerException("Invalid array (not null expected)");
		}
		if (0 > fromIndex || array.length <= fromIndex) {
			throw new IndexOutOfBoundsException("Invalid from index: " + fromIndex + " (between 0 and " + (array.length - 1) + " expected)");
		}
		for (var i = fromIndex; i < array.length; ++i) {
			if (value == array[i]) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * <p>Get the last index of the {@code long} value in the given {@code long array}.</p>
	 * @param array the {@code long array} to look into
	 * @param value the {@code long} value to search
	 * @return the last index of the {@code long} value if found, {@code -1} otherwise
	 * @throws NullPointerException if the {@code long array} is {@code null}
	 * @since 1.0
	 */
	public static int lastIndexOf(final long[] array, final long value) {
		return lastIndexOf(array, value, 0);
	}

	/**
	 * <p>Get the last index of the {@code long} value in the given {@code long array} starting from the provided
	 * index.</p>
	 * @param array the {@code long array} to look into
	 * @param value the {@code long} value to search
	 * @param fromIndex the index to start from
	 * @return the last index of the {@code long} value if found, {@code -1} otherwise
	 * @throws NullPointerException if the {@code long array} is {@code null}
	 * @throws IndexOutOfBoundsException if the index to start from is not valid
	 * @since 1.0
	 */
	public static int lastIndexOf(final long[] array, final long value, final int fromIndex) {
		if (null == array) {
			throw new NullPointerException("Invalid array (not null expected)");
		}
		if (0 > fromIndex || array.length <= fromIndex) {
			throw new IndexOutOfBoundsException("Invalid from index: " + fromIndex + " (between 0 and " + (array.length - 1) + " expected)");
		}
		for (var i = array.length - 1; i > fromIndex; --i) {
			if (value == array[i]) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * <p>Tell if the {@code long array} contains the given {@code long} value.</p>
	 * @param array the {@code long array} to look into
	 * @param value the {@code long} value to search
	 * @return {@code true} if the given {@code long} value is contained by the {@code long array}
	 * @throws NullPointerException if the {@code long array} is {@code null}
	 * @since 1.0
	 */
	public static boolean contains(final long[] array, final long value) {
		if (null == array) {
			throw new NullPointerException("Invalid array (not null expected)");
		}
		if (0 == array.length) {
			return false;
		}
		for (final var element : array) {
			if (value == element) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <p>Tell if the {@code long array} contains only the given {@code long} value.</p>
	 * @param array the {@code long array} to look into
	 * @param value the {@code long} value to search
	 * @return {@code true} if the given {@code long} value is the only value contained by the {@code long array}
	 * @throws NullPointerException if the {@code long array} is {@code null}
	 * @since 1.0
	 */
	public static boolean containsOnly(final long[] array, final long value) {
		if (null == array) {
			throw new NullPointerException("Invalid array (not null expected)");
		}
		if (0 == array.length) {
			return false;
		}
		for (final var element : array) {
			if (value != element) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>Tell if the {@code long array} contains any of given {@code long} values.</p>
	 * @param array the {@code long array} to look into
	 * @param values {@code long} values to search
	 * @return {@code true} if any of given {@code long} values is contained by the {@code long array}
	 * @throws NullPointerException if the {@code long array} or {@code long} values are {@code null}
	 * @since 1.0
	 */
	public static boolean containsAny(final long[] array, final long... values) {
		if (null == array) {
			throw new NullPointerException("Invalid array (not null expected)");
		}
		if (null == values) {
			throw new NullPointerException("Invalid values (not null expected)");
		}
		if (0 == array.length || 0 == values.length) {
			return false;
		}
		for (final var value : values) {
			for (final var element : array) {
				if (value == element) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * <p>Tell if the {@code long array} contains all of given {@code long} values.</p>
	 * @param array the {@code long array} to look into
	 * @param values {@code long} values to search
	 * @return {@code true} if all of given {@code long} values are contained by the {@code long array}
	 * @throws NullPointerException if the {@code long array} or {@code long} values are {@code null}
	 * @since 1.0
	 */
	public static boolean containsAll(final long[] array, final long... values) {
		if (null == array) {
			throw new NullPointerException("Invalid array (not null expected)");
		}
		if (null == values) {
			throw new NullPointerException("Invalid values (not null expected)");
		}
		if (0 == array.length || 0 == values.length) {
			return false;
		}
		for (final var value : values) {
			var found = false;
			for (final var element : array) {
				if (value == element) {
					found = true;
					break;
				}
			}
			if (!found) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>Concatenate multiple {@code long array}s.</p>
	 * @param arrays {@code long array}s to concatenate
	 * @return the concatenated {@code long array}
	 * @throws NullPointerException if the array or any of the {@code long array}s is {@code null}
	 * @since 1.0
	 */
	public static long[] concat(final long[]... arrays) {
		if (null == arrays) {
			throw new NullPointerException("Invalid array (not null expected)");
		}
		return concat(Arrays.asList(arrays));
	}

	/**
	 * <p>Concatenate a list of {@code long array}s.</p>
	 * @param arrays {@code long array}s to concatenate
	 * @return the concatenated {@code long array}
	 * @throws NullPointerException if the {@code long array} list or any of the {@code long array}s is
	 * {@code null}
	 * @since 1.0
	 */
	public static long[] concat(final List<long[]> arrays) {
		if (null == arrays) {
			throw new NullPointerException("Invalid array (not null expected)");
		}
		for (final var array : arrays) {
			if (null == array) {
				throw new NullPointerException("Invalid array (not null expected)");
			}
		}
		if (arrays.isEmpty()) {
			return EMPTY;
		}
		if (1 == arrays.size()) {
			return arrays.get(0);
		}
		final var result = new long[arrays.stream().mapToInt(array -> array.length).sum()];
		var offset = 0;
		for (final var array : arrays) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}

	/**
	 * <p>Join multiple {@code long array}s using a {@code long array} separator.</p>
	 * @param separator the {@code long array} sequence to add between each joined {@code long array}
	 * @param arrays {@code long array}s to join
	 * @return the joined {@code long array}
	 * @throws NullPointerException if the separator, the array or any of the {@code long array}s is {@code null}
	 * @since 1.0
	 */
	public static long[] join(final long[] separator, final long[]... arrays) {
		if (null == arrays) {
			throw new NullPointerException("Invalid array (not null expected)");
		}
		return join(separator, Arrays.asList(arrays));
	}

	/**
	 * <p>Join a list of {@code long array}s using a {@code long array} separator.</p>
	 * @param separator the {@code long array} sequence to add between each joined {@code long array}
	 * @param arrays {@code long array}s to join
	 * @return the joined {@code long array}
	 * @throws NullPointerException if the separator, the {@code long array} list or any of the {@code long array}s is
	 * {@code null}
	 * @since 1.0
	 */
	public static long[] join(final long[] separator, final List<long[]> arrays) {
		if (null == separator) {
			throw new NullPointerException("Invalid separator (not null expected)");
		}
		if (null == arrays) {
			throw new NullPointerException("Invalid array (not null expected)");
		}
		for (final var array : arrays) {
			if (null == array) {
				throw new NullPointerException("Invalid array (not null expected)");
			}
		}
		if (0 == separator.length) {
			return concat(arrays);
		}
		if (arrays.isEmpty()) {
			return EMPTY;
		}
		if (1 == arrays.size()) {
			return arrays.get(0);
		}
		final var result = new long[arrays.stream().mapToInt(array -> array.length).sum() + (arrays.size() - 1) * separator.length];
		final var iterator = arrays.iterator();
		var array = iterator.next();
		System.arraycopy(array, 0, result, 0, array.length);
		var offset = array.length;
		while (iterator.hasNext()) {
			System.arraycopy(separator, 0, result, offset, separator.length);
			offset += separator.length;
			array = iterator.next();
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}

	/**
	 * <p>Create a {@code long array} using given {@code long} values.</p>
	 * @param values {@code long} values
	 * @return the created {@code long array}
	 * @throws NullPointerException if {@code long} values are {@code null}
	 * @since 1.0
	 */
	public static long[] of(final long... values) {
		if (null == values) {
			throw new NullPointerException("Invalid values (not null expected)");
		}
		if (0 == values.length) {
			return EMPTY;
		}
		return values;
	}
}