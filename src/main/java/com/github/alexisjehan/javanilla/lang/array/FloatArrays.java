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

import com.github.alexisjehan.javanilla.util.iteration.Iterables;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>An utility class that provides {@code float} array tools.</p>
 * @since 1.0.0
 */
public final class FloatArrays {

	/**
	 * <p>An empty {@code float} array.</p>
	 * @since 1.0.0
	 */
	public static final float[] EMPTY = {};

	/**
	 * <p>Constructor not available.</p>
	 * @since 1.0.0
	 */
	private FloatArrays() {
		// Not available
	}

	/**
	 * <p>Wrap a {@code float} array replacing {@code null} by an empty one.</p>
	 * @param array the {@code float} array or {@code null}
	 * @return a non-{@code null} {@code float} array
	 * @since 1.0.0
	 */
	public static float[] nullToEmpty(final float[] array) {
		return nullToDefault(array, EMPTY);
	}

	/**
	 * <p>Wrap a {@code float} array replacing {@code null} by a default one.</p>
	 * @param array the {@code float} array or {@code null}
	 * @param defaultArray the default {@code float} array
	 * @return a non-{@code null} {@code float} array
	 * @throws NullPointerException if the default {@code float} array is {@code null}
	 * @since 1.1.0
	 */
	public static float[] nullToDefault(final float[] array, final float[] defaultArray) {
		if (null == defaultArray) {
			throw new NullPointerException("Invalid default array (not null expected)");
		}
		return null != array ? array : defaultArray;
	}

	/**
	 * <p>Wrap a {@code float} array replacing an empty one by {@code null}.</p>
	 * @param array the {@code float} array or {@code null}
	 * @return a non-empty {@code float} array or {@code null}
	 * @since 1.0.0
	 */
	public static float[] emptyToNull(final float[] array) {
		return emptyToDefault(array, null);
	}

	/**
	 * <p>Wrap a {@code float} array replacing an empty one by a default {@code float} array.</p>
	 * @param array the {@code float} array or {@code null}
	 * @param defaultArray the default {@code float} array or {@code null}
	 * @return a non-empty {@code float} array or {@code null}
	 * @throws IllegalArgumentException if the default {@code float} array is empty
	 * @since 1.1.0
	 */
	public static float[] emptyToDefault(final float[] array, final float[] defaultArray) {
		if (null != defaultArray && 0 == defaultArray.length) {
			throw new IllegalArgumentException("Invalid default array (not empty expected)");
		}
		return null == array || 0 != array.length ? array : defaultArray;
	}

	/**
	 * <p>Tell if a {@code float} array is empty.</p>
	 * @param array the {@code float} array to test
	 * @return {@code true} if the {@code float} array is empty
	 * @throws NullPointerException if the {@code float} array is {@code null}
	 * @since 1.2.0
	 */
	public static boolean isEmpty(final float[] array) {
		if (null == array) {
			throw new NullPointerException("Invalid array (not null expected)");
		}
		return 0 == array.length;
	}

	/**
	 * <p>Get the first index of the {@code float} value in the {@code float} array.</p>
	 * @param array the {@code float} array to lookup
	 * @param value the {@code float} value to search
	 * @return the first index of the {@code float} value if found, {@code -1} otherwise
	 * @throws NullPointerException if the {@code float} array is {@code null}
	 * @since 1.0.0
	 */
	public static int indexOf(final float[] array, final float value) {
		return indexOf(array, value, 0);
	}

	/**
	 * <p>Get the first index of the {@code float} value in the {@code float} array starting from the given index.</p>
	 * @param array the {@code float} array to lookup
	 * @param value the {@code float} value to search
	 * @param fromIndex the starting index
	 * @return the first index of the {@code float} value if found, {@code -1} otherwise
	 * @throws NullPointerException if the {@code float} array is {@code null}
	 * @throws IndexOutOfBoundsException if the starting index is not valid
	 * @since 1.0.0
	 */
	public static int indexOf(final float[] array, final float value, final int fromIndex) {
		if (null == array) {
			throw new NullPointerException("Invalid array (not null expected)");
		}
		if (0 < array.length) {
			if (0 > fromIndex || array.length - 1 < fromIndex) {
				throw new IndexOutOfBoundsException("Invalid from index: " + fromIndex + " (between 0 and " + (array.length - 1) + " expected)");
			}
			for (var i = fromIndex; i < array.length; ++i) {
				if (value == array[i]) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * <p>Get the last index of the {@code float} value in the {@code float} array.</p>
	 * @param array the {@code float} array to lookup
	 * @param value the {@code float} value to search
	 * @return the last index of the {@code float} value if found, {@code -1} otherwise
	 * @throws NullPointerException if the {@code float} array is {@code null}
	 * @since 1.0.0
	 */
	public static int lastIndexOf(final float[] array, final float value) {
		return lastIndexOf(array, value, 0);
	}

	/**
	 * <p>Get the last index of the {@code float} value in the {@code float} array starting from the given index.</p>
	 * @param array the {@code float} array to lookup
	 * @param value the {@code float} value to search
	 * @param fromIndex the starting index
	 * @return the last index of the {@code float} value if found, {@code -1} otherwise
	 * @throws NullPointerException if the {@code float} array is {@code null}
	 * @throws IndexOutOfBoundsException if the starting index is not valid
	 * @since 1.0.0
	 */
	public static int lastIndexOf(final float[] array, final float value, final int fromIndex) {
		if (null == array) {
			throw new NullPointerException("Invalid array (not null expected)");
		}
		if (0 < array.length) {
			if (0 > fromIndex || array.length - 1 < fromIndex) {
				throw new IndexOutOfBoundsException("Invalid from index: " + fromIndex + " (between 0 and " + (array.length - 1) + " expected)");
			}
			for (var i = array.length - 1; i > fromIndex; --i) {
				if (value == array[i]) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * <p>Tell if the {@code float} array contains any of given {@code float} values at least one.</p>
	 * @param array the {@code float} array to test
	 * @param values {@code float} values to test
	 * @return {@code true} if any of given {@code float} values is contained at least once by the {@code float} array
	 * @throws NullPointerException if the {@code float} array or the {@code float} values array is {@code null}
	 * @throws IllegalArgumentException if the {@code float} values array is empty
	 * @since 1.0.0
	 */
	public static boolean containsAny(final float[] array, final float... values) {
		if (null == array) {
			throw new NullPointerException("Invalid array (not null expected)");
		}
		if (null == values) {
			throw new NullPointerException("Invalid values (not null expected)");
		}
		if (0 == values.length) {
			throw new IllegalArgumentException("Invalid values (not empty expected)");
		}
		if (0 == array.length) {
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
	 * <p>Tell if the {@code float} array contains all of given {@code float} values at least one.</p>
	 * @param array the {@code float} array to test
	 * @param values {@code float} values to test
	 * @return {@code true} if all of given {@code float} values are contained at least once by the {@code float} array
	 * @throws NullPointerException if the {@code float} array or the {@code float} values array is {@code null}
	 * @throws IllegalArgumentException if the {@code float} values array is empty
	 * @since 1.0.0
	 */
	public static boolean containsAll(final float[] array, final float... values) {
		if (null == array) {
			throw new NullPointerException("Invalid array (not null expected)");
		}
		if (null == values) {
			throw new NullPointerException("Invalid values (not null expected)");
		}
		if (0 == values.length) {
			throw new IllegalArgumentException("Invalid values (not empty expected)");
		}
		if (0 == array.length) {
			return false;
		}
		for (final var value : values) {
			var contained = false;
			for (final var element : array) {
				if (value == element) {
					contained = true;
					break;
				}
			}
			if (!contained) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>Tell if the {@code float} array contains each given {@code float} value only once.</p>
	 * @param array the {@code float} array to test
	 * @param values {@code float} values to test
	 * @return {@code true} if each of given {@code float} values are contained only once by the {@code float} array
	 * @throws NullPointerException if the {@code float} array or the {@code float} values array is {@code null}
	 * @throws IllegalArgumentException if the {@code float} values array is empty
	 * @since 1.1.0
	 */
	public static boolean containsOnce(final float[] array, final float... values) {
		if (null == array) {
			throw new NullPointerException("Invalid array (not null expected)");
		}
		if (null == values) {
			throw new NullPointerException("Invalid values (not null expected)");
		}
		if (0 == values.length) {
			throw new IllegalArgumentException("Invalid values (not empty expected)");
		}
		if (0 == array.length) {
			return false;
		}
		for (final var value : values) {
			var contained = false;
			for (final var element : array) {
				if (value == element) {
					if (contained) {
						return false;
					}
					contained = true;
				}
			}
			if (!contained) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>Tell if the {@code float} array contains only given {@code float} values at least one.</p>
	 * @param array the {@code float} array to test
	 * @param values {@code float} values to test
	 * @return {@code true} if given {@code float} values are only values contained by the {@code float} array
	 * @throws NullPointerException if the {@code float} array or the {@code float} values array is {@code null}
	 * @throws IllegalArgumentException if the {@code float} values array is empty
	 * @since 1.0.0
	 */
	public static boolean containsOnly(final float[] array, final float... values) {
		if (null == array) {
			throw new NullPointerException("Invalid array (not null expected)");
		}
		if (null == values) {
			throw new NullPointerException("Invalid values (not null expected)");
		}
		if (0 == values.length) {
			throw new IllegalArgumentException("Invalid values (not empty expected)");
		}
		if (0 == array.length) {
			return false;
		}
		for (final var element : array) {
			var contained = false;
			for (final var value : values) {
				if (value == element) {
					contained = true;
					break;
				}
			}
			if (!contained) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>Shuffle values in the given {@code float} array using the Fisher-Yates algorithm.</p>
	 * @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle</a>
	 * @param array the {@code float} array to shuffle
	 * @throws NullPointerException if the {@code float} array is {@code null}
	 * @since 1.2.0
	 */
	public static void shuffle(final float[] array) {
		if (null == array) {
			throw new NullPointerException("Invalid array (not null expected)");
		}
		if (1 < array.length) {
			final var random = ThreadLocalRandom.current();
			for (var i = 0; i < array.length; ++i) {
				swap(array, i, random.nextInt(i + 1));
			}
		}
	}

	/**
	 * <p>Reverse values in the given {@code float} array.</p>
	 * @param array the {@code float} array to reverse
	 * @throws NullPointerException if the {@code float} array is {@code null}
	 * @since 1.2.0
	 */
	public static void reverse(final float[] array) {
		if (null == array) {
			throw new NullPointerException("Invalid array (not null expected)");
		}
		if (1 < array.length) {
			for (var i = 0; i < array.length / 2; ++i) {
				swap(array, i, array.length - i - 1);
			}
		}
	}

	/**
	 * <p>Reorder values in the given {@code float} array using provided indexes.</p>
	 * @param array the {@code float} array to reorder
	 * @param indexes indexes to use
	 * @throws NullPointerException if the {@code float} array or the indexes array is {@code null}
	 * @throws IllegalArgumentException if {@code float} array and indexes array lengths are not the same or if
	 * indexes are not distinct
	 * @throws IndexOutOfBoundsException if any index is not valid
	 * @since 1.2.0
	 */
	public static void reorder(final float[] array, final int... indexes) {
		if (null == array) {
			throw new NullPointerException("Invalid array (not null expected)");
		}
		if (null == indexes) {
			throw new NullPointerException("Invalid indexes (not null expected)");
		}
		if (array.length != indexes.length) {
			throw new IllegalArgumentException("Invalid array and indexes lengths: " + array.length + " and " + indexes.length + " (same expected)");
		}
		if (array.length != Arrays.stream(indexes).distinct().count()) {
			throw new IllegalArgumentException("Invalid indexes (distinct expected)");
		}
		if (1 < array.length) {
			for (var i = 0; i < array.length; ++i) {
				var j = indexes[i];
				if (0 > j || array.length - 1 < j) {
					throw new IndexOutOfBoundsException("Invalid index: " + j + " (between 0 and " + (array.length - 1) + " expected)");
				}
				while (j < i) {
					j = indexes[j];
				}
				swap(array, i, j);
			}
		}
	}

	/**
	 * <p>Swap two values in the given {@code float} array using their indexes.</p>
	 * @param array the {@code float} array to swap
	 * @param index1 the index of the first value
	 * @param index2 the index of the second value
	 * @throws NullPointerException if the {@code float} array is {@code null}
	 * @throws IndexOutOfBoundsException if any index is not valid
	 * @since 1.2.0
	 */
	public static void swap(final float[] array, final int index1, final int index2) {
		if (null == array) {
			throw new NullPointerException("Invalid array (not null expected)");
		}
		if (0 > index1 || array.length - 1 < index1) {
			throw new IndexOutOfBoundsException("Invalid first index: " + index1 + " (between 0 and " + (array.length - 1) + " expected)");
		}
		if (0 > index2 || array.length - 1 < index2) {
			throw new IndexOutOfBoundsException("Invalid second index: " + index2 + " (between 0 and " + (array.length - 1) + " expected)");
		}
		if (index1 != index2) {
			final var value = array[index1];
			array[index1] = array[index2];
			array[index2] = value;
		}
	}

	/**
	 * <p>Concatenate multiple {@code float} arrays.</p>
	 * @param arrays the {@code float} array array to concatenate
	 * @return the concatenated {@code float} array
	 * @throws NullPointerException if the {@code float} array array or any of them is {@code null}
	 * @since 1.0.0
	 */
	public static float[] concat(final float[]... arrays) {
		if (null == arrays) {
			throw new NullPointerException("Invalid arrays (not null expected)");
		}
		return concat(Arrays.asList(arrays));
	}

	/**
	 * <p>Concatenate multiple {@code float} arrays.</p>
	 * @param arrays the {@code float} array {@code List} to concatenate
	 * @return the concatenated {@code float} array
	 * @throws NullPointerException if the {@code float} array {@code List} or any of them is {@code null}
	 * @since 1.0.0
	 */
	public static float[] concat(final List<float[]> arrays) {
		if (null == arrays) {
			throw new NullPointerException("Invalid arrays (not null expected)");
		}
		for (final var indexedArray : Iterables.index(arrays)) {
			if (null == indexedArray.getElement()) {
				throw new NullPointerException("Invalid array at index " + indexedArray.getIndex() + " (not null expected)");
			}
		}
		final var size = arrays.size();
		if (0 == size) {
			return EMPTY;
		}
		if (1 == size) {
			return arrays.get(0);
		}
		final var result = new float[arrays.stream().mapToInt(array -> array.length).sum()];
		var offset = 0;
		for (final var array : arrays) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}

	/**
	 * <p>Join multiple {@code float} arrays using a {@code float} array separator.</p>
	 * @param separator the {@code float} array separator
	 * @param arrays the {@code float} array array to join
	 * @return the joined {@code float} array
	 * @throws NullPointerException if the {@code float} array separator, the {@code float} array array or any of them
	 * is {@code null}
	 * @since 1.0.0
	 */
	public static float[] join(final float[] separator, final float[]... arrays) {
		if (null == arrays) {
			throw new NullPointerException("Invalid arrays (not null expected)");
		}
		return join(separator, Arrays.asList(arrays));
	}

	/**
	 * <p>Join multiple {@code float} arrays using a {@code float} array separator.</p>
	 * @param separator the {@code float} array separator
	 * @param arrays the {@code float} array {@code List} to join
	 * @return the joined {@code float} array
	 * @throws NullPointerException if the {@code float} array separator, the {@code float} array {@code List} or any
	 * of them is {@code null}
	 * @since 1.0.0
	 */
	public static float[] join(final float[] separator, final List<float[]> arrays) {
		if (null == separator) {
			throw new NullPointerException("Invalid separator (not null expected)");
		}
		if (null == arrays) {
			throw new NullPointerException("Invalid arrays (not null expected)");
		}
		for (final var indexedArray : Iterables.index(arrays)) {
			if (null == indexedArray.getElement()) {
				throw new NullPointerException("Invalid array at index " + indexedArray.getIndex() + " (not null expected)");
			}
		}
		if (0 == separator.length) {
			return concat(arrays);
		}
		final var size = arrays.size();
		if (0 == size) {
			return EMPTY;
		}
		if (1 == size) {
			return arrays.get(0);
		}
		final var result = new float[arrays.stream().mapToInt(array -> array.length).sum() + (arrays.size() - 1) * separator.length];
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
	 * <p>Create a {@code float} array from a single {@code float}.</p>
	 * @param f the {@code float} to convert
	 * @return the created {@code float} array
	 * @since 1.1.0
	 */
	public static float[] singleton(final float f) {
		return of(f);
	}

	/**
	 * <p>Create a {@code float} array from multiple {@code float}s.</p>
	 * @param floats the {@code float} array to convert
	 * @return the created {@code float} array
	 * @throws NullPointerException if the {@code float} array is {@code null}
	 * @since 1.0.0
	 */
	public static float[] of(final float... floats) {
		if (null == floats) {
			throw new NullPointerException("Invalid floats (not null expected)");
		}
		if (0 == floats.length) {
			return EMPTY;
		}
		return floats;
	}

	/**
	 * <p>Create a {@code float} array from a boxed {@code Float} array.</p>
	 * @param boxedFloats the boxed {@code Float} array to convert
	 * @return the created {@code float} array
	 * @throws NullPointerException if the boxed {@code Float} array is {@code null}
	 * @since 1.2.0
	 */
	public static float[] of(final Float[] boxedFloats) {
		if (null == boxedFloats) {
			throw new NullPointerException("Invalid Floats (not null expected)");
		}
		if (0 == boxedFloats.length) {
			return EMPTY;
		}
		final var floats = new float[boxedFloats.length];
		for (var i = 0; i < floats.length; ++i) {
			floats[i] = boxedFloats[i];
		}
		return floats;
	}

	/**
	 * <p>Convert a {@code float} array to a boxed {@code Float} array.</p>
	 * @param floats the {@code float} array to convert
	 * @return the created boxed {@code Float} array
	 * @throws NullPointerException if the {@code float} array is {@code null}
	 * @since 1.2.0
	 */
	public static Float[] toBoxed(final float[] floats) {
		if (null == floats) {
			throw new NullPointerException("Invalid floats (not null expected)");
		}
		final var boxedFloats = new Float[floats.length];
		for (var i = 0; i < boxedFloats.length; ++i) {
			boxedFloats[i] = floats[i];
		}
		return boxedFloats;
	}
}