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
package com.github.alexisjehan.javanilla.misc.quality;

import com.github.alexisjehan.javanilla.lang.Strings;
import com.github.alexisjehan.javanilla.lang.array.BooleanArrays;
import com.github.alexisjehan.javanilla.lang.array.ByteArrays;
import com.github.alexisjehan.javanilla.lang.array.CharArrays;
import com.github.alexisjehan.javanilla.lang.array.DoubleArrays;
import com.github.alexisjehan.javanilla.lang.array.FloatArrays;
import com.github.alexisjehan.javanilla.lang.array.IntArrays;
import com.github.alexisjehan.javanilla.lang.array.LongArrays;
import com.github.alexisjehan.javanilla.lang.array.ObjectArrays;
import com.github.alexisjehan.javanilla.lang.array.ShortArrays;
import com.github.alexisjehan.javanilla.util.collection.bags.Bag;
import com.github.alexisjehan.javanilla.util.iteration.Iterables;
import com.github.alexisjehan.javanilla.util.iteration.Iterators;

import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>An utility class that provides multiple functions to validate arguments, throwing {@link NullPointerException} or
 * {@link IllegalArgumentException} if they are not valid.</p>
 * @since 1.3.0
 */
public final class Ensure {

	/**
	 * <p>Constructor not available.</p>
	 * @since 1.3.0
	 */
	private Ensure() {
		// Not available
	}

	/**
	 * <p>Ensure the value is not {@code null}.</p>
	 * @param name the name of the value
	 * @param value the value to validate
	 * @param <V> the value type
	 * @return the validated value
	 * @throws NullPointerException if the name or the value is {@code null}
	 * @since 1.3.0
	 */
	public static <V> V notNull(final String name, final V value) {
		if (null == name) {
			throw new NullPointerException("Invalid name (not null expected)");
		}
		if (null == value) {
			throw new NullPointerException("Invalid " + name + " (not null expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the array and its elements are not {@code null}.</p>
	 * @param name the name of the array
	 * @param array the array to validate
	 * @param <E> the element type
	 * @return the validated array
	 * @throws NullPointerException if the name, the array or any of its elements is {@code null}
	 * @since 1.3.0
	 */
	public static <E> E[] notNullAndNotNullElements(final String name, final E[] array) {
		notNull(name, array);
		for (var i = 0; i < array.length; ++i) {
			notNull(name + " element at index " + i, array[i]);
		}
		return array;
	}

	/**
	 * <p>Ensure the {@code Iterable} and its elements are not {@code null}.</p>
	 * @param name the name of the {@code Iterable}
	 * @param iterable the {@code Iterable} to validate
	 * @param <I> the {@code Iterable} type
	 * @return the validated {@code Iterable}
	 * @throws NullPointerException if the name, the {@code Iterable} or any of its elements is {@code null}
	 * @since 1.3.0
	 */
	public static <I extends Iterable<?>> I notNullAndNotNullElements(final String name, final I iterable) {
		notNull(name, iterable);
		for (final var indexedElement : Iterables.index(iterable)) {
			notNull(name + " element at index " + indexedElement.getIndex(), indexedElement.getElement());
		}
		return iterable;
	}

	/**
	 * <p>Ensure the {@code CharSequence} is not {@code null} and not empty.</p>
	 * @param name the name of the {@code CharSequence}
	 * @param charSequence the {@code CharSequence} to validate
	 * @param <C> the {@code CharSequence} type
	 * @return the validated {@code CharSequence}
	 * @throws NullPointerException if the name or the {@code CharSequence} is {@code null}
	 * @throws IllegalArgumentException if the {@code CharSequence} is empty
	 * @since 1.3.0
	 */
	public static <C extends CharSequence> C notNullAndNotEmpty(final String name, final C charSequence) {
		notNull(name, charSequence);
		if (Strings.isEmpty(charSequence)) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(charSequence) + " (not empty expected)");
		}
		return charSequence;
	}

	/**
	 * <p>Ensure the {@code boolean} array is not {@code null} and not empty.</p>
	 * @param name the name of the {@code boolean} array
	 * @param array the {@code boolean} array to validate
	 * @return the validated {@code boolean} array
	 * @throws NullPointerException if the name or the {@code boolean} array is {@code null}
	 * @throws IllegalArgumentException if the {@code boolean} array is empty
	 * @since 1.3.0
	 */
	public static boolean[] notNullAndNotEmpty(final String name, final boolean[] array) {
		notNull(name, array);
		if (BooleanArrays.isEmpty(array)) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(array) + " (not empty expected)");
		}
		return array;
	}

	/**
	 * <p>Ensure the {@code byte} array is not {@code null} and not empty.</p>
	 * @param name the name of the {@code byte} array
	 * @param array the {@code byte} array to validate
	 * @return the validated {@code byte} array
	 * @throws NullPointerException if the name or the {@code byte} array is {@code null}
	 * @throws IllegalArgumentException if the {@code byte} array is empty
	 * @since 1.3.0
	 */
	public static byte[] notNullAndNotEmpty(final String name, final byte[] array) {
		notNull(name, array);
		if (ByteArrays.isEmpty(array)) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(array) + " (not empty expected)");
		}
		return array;
	}

	/**
	 * <p>Ensure the {@code short} array is not {@code null} and not empty.</p>
	 * @param name the name of the {@code short} array
	 * @param array the {@code short} array to validate
	 * @return the validated {@code short} array
	 * @throws NullPointerException if the name or the {@code short} array is {@code null}
	 * @throws IllegalArgumentException if the {@code short} array is empty
	 * @since 1.3.0
	 */
	public static short[] notNullAndNotEmpty(final String name, final short[] array) {
		notNull(name, array);
		if (ShortArrays.isEmpty(array)) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(array) + " (not empty expected)");
		}
		return array;
	}

	/**
	 * <p>Ensure the {@code char} array is not {@code null} and not empty.</p>
	 * @param name the name of the {@code char} array
	 * @param array the {@code char} array to validate
	 * @return the validated {@code char} array
	 * @throws NullPointerException if the name or the {@code char} array is {@code null}
	 * @throws IllegalArgumentException if the {@code char} array is empty
	 * @since 1.3.0
	 */
	public static char[] notNullAndNotEmpty(final String name, final char[] array) {
		notNull(name, array);
		if (CharArrays.isEmpty(array)) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(array) + " (not empty expected)");
		}
		return array;
	}

	/**
	 * <p>Ensure the {@code int} array is not {@code null} and not empty.</p>
	 * @param name the name of the {@code int} array
	 * @param array the {@code int} array to validate
	 * @return the validated {@code int} array
	 * @throws NullPointerException if the name or the {@code int} array is {@code null}
	 * @throws IllegalArgumentException if the {@code int} array is empty
	 * @since 1.3.0
	 */
	public static int[] notNullAndNotEmpty(final String name, final int[] array) {
		notNull(name, array);
		if (IntArrays.isEmpty(array)) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(array) + " (not empty expected)");
		}
		return array;
	}

	/**
	 * <p>Ensure the {@code long} array is not {@code null} and not empty.</p>
	 * @param name the name of the {@code long} array
	 * @param array the {@code long} array to validate
	 * @return the validated {@code long} array
	 * @throws NullPointerException if the name or the {@code long} array is {@code null}
	 * @throws IllegalArgumentException if the {@code long} array is empty
	 * @since 1.3.0
	 */
	public static long[] notNullAndNotEmpty(final String name, final long[] array) {
		notNull(name, array);
		if (LongArrays.isEmpty(array)) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(array) + " (not empty expected)");
		}
		return array;
	}

	/**
	 * <p>Ensure the {@code float} array is not {@code null} and not empty.</p>
	 * @param name the name of the {@code float} array
	 * @param array the {@code float} array to validate
	 * @return the validated {@code float} array
	 * @throws NullPointerException if the name or the {@code float} array is {@code null}
	 * @throws IllegalArgumentException if the {@code float} array is empty
	 * @since 1.3.0
	 */
	public static float[] notNullAndNotEmpty(final String name, final float[] array) {
		notNull(name, array);
		if (FloatArrays.isEmpty(array)) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(array) + " (not empty expected)");
		}
		return array;
	}

	/**
	 * <p>Ensure the {@code double} array is not {@code null} and not empty.</p>
	 * @param name the name of the {@code double} array
	 * @param array the {@code double} array to validate
	 * @return the validated {@code double} array
	 * @throws NullPointerException if the name or the {@code double} array is {@code null}
	 * @throws IllegalArgumentException if the {@code double} array is empty
	 * @since 1.3.0
	 */
	public static double[] notNullAndNotEmpty(final String name, final double[] array) {
		notNull(name, array);
		if (DoubleArrays.isEmpty(array)) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(array) + " (not empty expected)");
		}
		return array;
	}

	/**
	 * <p>Ensure the {@code Object} array is not {@code null} and not empty.</p>
	 * @param name the name of the {@code Object} array
	 * @param array the {@code Object} array to validate
	 * @param <E> the element type
	 * @return the validated array
	 * @throws NullPointerException if the name or the {@code Object} array is {@code null}
	 * @throws IllegalArgumentException if the {@code Object} array is empty
	 * @since 1.3.0
	 */
	public static <E> E[] notNullAndNotEmpty(final String name, final E[] array) {
		notNull(name, array);
		if (ObjectArrays.isEmpty(array)) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(array) + " (not empty expected)");
		}
		return array;
	}

	/**
	 * <p>Ensure the {@code Collection} is not {@code null} and not empty.</p>
	 * @param name the name of the {@code Collection}
	 * @param collection the {@code Collection} to validate
	 * @param <C> the {@code Collection} type
	 * @return the validated {@code Collection}
	 * @throws NullPointerException if the name or the {@code Collection} is {@code null}
	 * @throws IllegalArgumentException if the {@code Collection} is empty
	 * @since 1.3.0
	 */
	public static <C extends Collection> C notNullAndNotEmpty(final String name, final C collection) {
		notNull(name, collection);
		if (collection.isEmpty()) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(collection) + " (not empty expected)");
		}
		return collection;
	}

	/**
	 * <p>Ensure the {@code Map} is not {@code null} and not empty.</p>
	 * @param name the name of the {@code Map}
	 * @param map the {@code Map} to validate
	 * @param <M> the {@code Map} type
	 * @return the validated {@code Map}
	 * @throws NullPointerException if the name or the {@code Map} is {@code null}
	 * @throws IllegalArgumentException if the {@code Map} is empty
	 * @since 1.3.0
	 */
	public static <M extends Map> M notNullAndNotEmpty(final String name, final M map) {
		notNull(name, map);
		if (map.isEmpty()) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(map) + " (not empty expected)");
		}
		return map;
	}

	/**
	 * <p>Ensure the {@code Bag} is not {@code null} and not empty.</p>
	 * @param name the name of the {@code Bag}
	 * @param bag the {@code Bag} to validate
	 * @param <B> the {@code Bag} type
	 * @return the validated {@code Bag}
	 * @throws NullPointerException if the name or the {@code Bag} is {@code null}
	 * @throws IllegalArgumentException if the {@code Bag} is empty
	 * @since 1.3.0
	 */
	public static <B extends Bag> B notNullAndNotEmpty(final String name, final B bag) {
		notNull(name, bag);
		if (bag.isEmpty()) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(bag) + " (not empty expected)");
		}
		return bag;
	}

	/**
	 * <p>Ensure the {@code Iterator} is not {@code null} and not empty.</p>
	 * @param name the name of the {@code Iterator}
	 * @param iterator the {@code Iterator} to validate
	 * @param <I> the {@code Iterator} type
	 * @return the validated {@code Iterator}
	 * @throws NullPointerException if the name or the {@code Iterator} is {@code null}
	 * @throws IllegalArgumentException if the {@code Iterator} is empty
	 * @since 1.3.0
	 */
	public static <I extends Iterator> I notNullAndNotEmpty(final String name, final I iterator) {
		notNull(name, iterator);
		if (Iterators.isEmpty(iterator)) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(iterator) + " (not empty expected)");
		}
		return iterator;
	}

	/**
	 * <p>Ensure the {@code CharSequence} is not {@code null} and not blank.</p>
	 * @param name the name of the {@code CharSequence}
	 * @param charSequence the {@code CharSequence} to validate
	 * @param <C> the {@code CharSequence} type
	 * @return the validated {@code CharSequence}
	 * @throws NullPointerException if the name or the {@code CharSequence} is {@code null}
	 * @throws IllegalArgumentException if the {@code CharSequence} is blank
	 * @since 1.3.0
	 */
	public static <C extends CharSequence> C notNullAndNotBlank(final String name, final C charSequence) {
		notNull(name, charSequence);
		if (Strings.isBlank(charSequence)) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(charSequence) + " (not blank expected)");
		}
		return charSequence;
	}

	/**
	 * <p>Ensure the {@code InputStream} is not {@code null} and has mark supported.</p>
	 * @param name the name of the {@code InputStream}
	 * @param inputStream the {@code InputStream} to validate
	 * @param <I> the {@code InputStream} type
	 * @return the validated {@code InputStream}
	 * @throws NullPointerException if the name or the {@code InputStream} is {@code null}
	 * @throws IllegalArgumentException if the {@code InputStream} does not have mark supported
	 * @since 1.3.0
	 */
	public static <I extends InputStream> I notNullAndMarkSupported(final String name, final I inputStream) {
		notNull(name, inputStream);
		if (!inputStream.markSupported()) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(inputStream) + " (mark supported expected)");
		}
		return inputStream;
	}

	/**
	 * <p>Ensure the {@code Reader} is not {@code null} and has mark supported.</p>
	 * @param name the name of the {@code Reader}
	 * @param reader the {@code Reader} to validate
	 * @param <R> the {@code Reader} type
	 * @return the validated {@code Reader}
	 * @throws NullPointerException if the name or the {@code Reader} is {@code null}
	 * @throws IllegalArgumentException if the {@code Reader} does not have mark supported
	 * @since 1.3.0
	 */
	public static <R extends Reader> R notNullAndMarkSupported(final String name, final R reader) {
		notNull(name, reader);
		if (!reader.markSupported()) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(reader) + " (mark supported expected)");
		}
		return reader;
	}

	/**
	 * <p>Ensure the {@code Path} is not {@code null} and exists.</p>
	 * @param name the name of the {@code Path}
	 * @param path the {@code Path} to validate
	 * @param <P> the {@code Path} type
	 * @return the validated {@code Path}
	 * @throws NullPointerException if the name or the {@code Path} is {@code null}
	 * @throws IllegalArgumentException if the {@code Path} does not exist
	 * @since 1.3.0
	 */
	public static <P extends Path> P notNullAndExists(final String name, final P path) {
		notNull(name, path);
		if (!Files.exists(path)) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(path) + " (existing expected)");
		}
		return path;
	}

	/**
	 * <p>Ensure the {@code Path} is not {@code null}, exists and is a file.</p>
	 * @param name the name of the {@code Path}
	 * @param path the {@code Path} to validate
	 * @param <P> the {@code Path} type
	 * @return the validated {@code Path}
	 * @throws NullPointerException if the name or the {@code Path} is {@code null}
	 * @throws IllegalArgumentException if the {@code Path} does not exist or is not a file
	 * @since 1.3.0
	 */
	public static <P extends Path> P notNullAndFile(final String name, final P path) {
		notNull(name, path);
		if (!Files.isRegularFile(path)) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(path) + " (existing file expected)");
		}
		return path;
	}

	/**
	 * <p>Ensure the {@code Path} is not {@code null}, exists and is a directory.</p>
	 * @param name the name of the {@code Path}
	 * @param path the {@code Path} to validate
	 * @param <P> the {@code Path} type
	 * @return the validated {@code Path}
	 * @throws NullPointerException if the name or the {@code Path} is {@code null}
	 * @throws IllegalArgumentException if the {@code Path} does not exist or is not a directory
	 * @since 1.3.0
	 */
	public static <P extends Path> P notNullAndDirectory(final String name, final P path) {
		notNull(name, path);
		if (!Files.isDirectory(path)) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(path) + " (existing directory expected)");
		}
		return path;
	}

	/**
	 * <p>Ensure the {@code boolean} value is equal to the other one.</p>
	 * @param name the name of the {@code boolean} value
	 * @param value the {@code boolean} value to validate
	 * @param other the other {@code boolean} value
	 * @return the validated {@code boolean} value
	 * @throws IllegalArgumentException if the {@code boolean} value is not equal to the other one
	 * @since 1.3.0
	 */
	public static boolean equalTo(final String name, final boolean value, final boolean other) {
		notNull("name", name);
		if (other != value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (equal to " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code byte} value is equal to the other one.</p>
	 * @param name the name of the {@code byte} value
	 * @param value the {@code byte} value to validate
	 * @param other the other {@code byte} value
	 * @return the validated {@code byte} value
	 * @throws IllegalArgumentException if the {@code byte} value is not equal to the other one
	 * @since 1.3.0
	 */
	public static byte equalTo(final String name, final byte value, final byte other) {
		notNull("name", name);
		if (other != value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (equal to " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code short} value is equal to the other one.</p>
	 * @param name the name of the {@code short} value
	 * @param value the {@code short} value to validate
	 * @param other the other {@code short} value
	 * @return the validated {@code short} value
	 * @throws IllegalArgumentException if the {@code short} value is not equal to the other one
	 * @since 1.3.0
	 */
	public static short equalTo(final String name, final short value, final short other) {
		notNull("name", name);
		if (other != value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (equal to " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code char} value is equal to the other one.</p>
	 * @param name the name of the {@code char} value
	 * @param value the {@code char} value to validate
	 * @param other the other {@code char} value
	 * @return the validated {@code char} value
	 * @throws IllegalArgumentException if the {@code char} value is not equal to the other one
	 * @since 1.3.0
	 */
	public static char equalTo(final String name, final char value, final char other) {
		notNull("name", name);
		if (other != value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (equal to " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code int} value is equal to the other one.</p>
	 * @param name the name of the {@code int} value
	 * @param value the {@code int} value to validate
	 * @param other the other {@code int} value
	 * @return the validated {@code int} value
	 * @throws IllegalArgumentException if the {@code int} value is not equal to the other one
	 * @since 1.3.0
	 */
	public static int equalTo(final String name, final int value, final int other) {
		notNull("name", name);
		if (other != value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (equal to " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code long} value is equal to the other one.</p>
	 * @param name the name of the {@code long} value
	 * @param value the {@code long} value to validate
	 * @param other the other {@code long} value
	 * @return the validated {@code long} value
	 * @throws IllegalArgumentException if the {@code long} value is not equal to the other one
	 * @since 1.3.0
	 */
	public static long equalTo(final String name, final long value, final long other) {
		notNull("name", name);
		if (other != value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (equal to " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code float} value is equal to the other one.</p>
	 * @param name the name of the {@code float} value
	 * @param value the {@code float} value to validate
	 * @param other the other {@code float} value
	 * @return the validated {@code float} value
	 * @throws IllegalArgumentException if the {@code float} value is not equal to the other one
	 * @since 1.3.0
	 */
	public static float equalTo(final String name, final float value, final float other) {
		notNull("name", name);
		if (0 != Float.compare(other, value)) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (equal to " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code double} value is equal to the other one.</p>
	 * @param name the name of the {@code double} value
	 * @param value the {@code double} value to validate
	 * @param other the other {@code double} value
	 * @return the validated {@code double} value
	 * @throws IllegalArgumentException if the {@code double} value is not equal to the other one
	 * @since 1.3.0
	 */
	public static double equalTo(final String name, final double value, final double other) {
		notNull("name", name);
		if (0 != Double.compare(other, value)) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (equal to " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code byte} value is lower than the other one.</p>
	 * @param name the name of the {@code byte} value
	 * @param value the {@code byte} value to validate
	 * @param other the other {@code byte} value
	 * @return the validated {@code byte} value
	 * @throws IllegalArgumentException if the {@code byte} value is greater than or equal to the other one
	 * @since 1.3.0
	 */
	public static byte lowerThan(final String name, final byte value, final byte other) {
		notNull("name", name);
		if (other <= value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (lower than " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code short} value is lower than the other one.</p>
	 * @param name the name of the {@code short} value
	 * @param value the {@code short} value to validate
	 * @param other the other {@code short} value
	 * @return the validated {@code short} value
	 * @throws IllegalArgumentException if the {@code short} value is greater than or equal to the other one
	 * @since 1.3.0
	 */
	public static short lowerThan(final String name, final short value, final short other) {
		notNull("name", name);
		if (other <= value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (lower than " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code char} value is lower than the other one.</p>
	 * @param name the name of the {@code char} value
	 * @param value the {@code char} value to validate
	 * @param other the other {@code char} value
	 * @return the validated {@code char} value
	 * @throws IllegalArgumentException if the {@code char} value is greater than or equal to the other one
	 * @since 1.3.0
	 */
	public static char lowerThan(final String name, final char value, final char other) {
		notNull("name", name);
		if (other <= value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (lower than " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code int} value is lower than the other one.</p>
	 * @param name the name of the {@code int} value
	 * @param value the {@code int} value to validate
	 * @param other the other {@code int} value
	 * @return the validated {@code int} value
	 * @throws IllegalArgumentException if the {@code int} value is greater than or equal to the other one
	 * @since 1.3.0
	 */
	public static int lowerThan(final String name, final int value, final int other) {
		notNull("name", name);
		if (other <= value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (lower than " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code long} value is lower than the other one.</p>
	 * @param name the name of the {@code long} value
	 * @param value the {@code long} value to validate
	 * @param other the other {@code long} value
	 * @return the validated {@code long} value
	 * @throws IllegalArgumentException if the {@code long} value is greater than or equal to the other one
	 * @since 1.3.0
	 */
	public static long lowerThan(final String name, final long value, final long other) {
		notNull("name", name);
		if (other <= value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (lower than " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code float} value is lower than the other one.</p>
	 * @param name the name of the {@code float} value
	 * @param value the {@code float} value to validate
	 * @param other the other {@code float} value
	 * @return the validated {@code float} value
	 * @throws IllegalArgumentException if the {@code float} value is greater than or equal to the other one
	 * @since 1.3.0
	 */
	public static float lowerThan(final String name, final float value, final float other) {
		notNull("name", name);
		if (other <= value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (lower than " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code double} value is lower than the other one.</p>
	 * @param name the name of the {@code double} value
	 * @param value the {@code double} value to validate
	 * @param other the other {@code double} value
	 * @return the validated {@code double} value
	 * @throws IllegalArgumentException if the {@code double} value is greater than or equal to the other one
	 * @since 1.3.0
	 */
	public static double lowerThan(final String name, final double value, final double other) {
		notNull("name", name);
		if (other <= value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (lower than " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code byte} value is lower than or equal to the other one.</p>
	 * @param name the name of the {@code byte} value
	 * @param value the {@code byte} value to validate
	 * @param other the other {@code byte} value
	 * @return the validated {@code byte} value
	 * @throws IllegalArgumentException if the {@code byte} value is greater than the other one
	 * @since 1.3.0
	 */
	public static byte lowerThanOrEqualTo(final String name, final byte value, final byte other) {
		notNull("name", name);
		if (other < value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (lower than or equal to " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code short} value is lower than or equal to the other one.</p>
	 * @param name the name of the {@code short} value
	 * @param value the {@code short} value to validate
	 * @param other the other {@code short} value
	 * @return the validated {@code short} value
	 * @throws IllegalArgumentException if the {@code short} value is greater than the other one
	 * @since 1.3.0
	 */
	public static short lowerThanOrEqualTo(final String name, final short value, final short other) {
		notNull("name", name);
		if (other < value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (lower than or equal to " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code char} value is lower than or equal to the other one.</p>
	 * @param name the name of the {@code char} value
	 * @param value the {@code char} value to validate
	 * @param other the other {@code char} value
	 * @return the validated {@code char} value
	 * @throws IllegalArgumentException if the {@code char} value is greater than the other one
	 * @since 1.3.0
	 */
	public static char lowerThanOrEqualTo(final String name, final char value, final char other) {
		notNull("name", name);
		if (other < value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (lower than or equal to " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code int} value is lower than or equal to the other one.</p>
	 * @param name the name of the {@code int} value
	 * @param value the {@code int} value to validate
	 * @param other the other {@code int} value
	 * @return the validated {@code int} value
	 * @throws IllegalArgumentException if the {@code int} value is greater than the other one
	 * @since 1.3.0
	 */
	public static int lowerThanOrEqualTo(final String name, final int value, final int other) {
		notNull("name", name);
		if (other < value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (lower than or equal to " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code long} value is lower than or equal to the other one.</p>
	 * @param name the name of the {@code long} value
	 * @param value the {@code long} value to validate
	 * @param other the other {@code long} value
	 * @return the validated {@code long} value
	 * @throws IllegalArgumentException if the {@code long} value is greater than the other one
	 * @since 1.3.0
	 */
	public static long lowerThanOrEqualTo(final String name, final long value, final long other) {
		notNull("name", name);
		if (other < value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (lower than or equal to " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code float} value is lower than or equal to the other one.</p>
	 * @param name the name of the {@code float} value
	 * @param value the {@code float} value to validate
	 * @param other the other {@code float} value
	 * @return the validated {@code float} value
	 * @throws IllegalArgumentException if the {@code float} value is greater than the other one
	 * @since 1.3.0
	 */
	public static float lowerThanOrEqualTo(final String name, final float value, final float other) {
		notNull("name", name);
		if (other < value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (lower than or equal to " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code double} value is lower than or equal to the other one.</p>
	 * @param name the name of the {@code double} value
	 * @param value the {@code double} value to validate
	 * @param other the other {@code double} value
	 * @return the validated {@code double} value
	 * @throws IllegalArgumentException if the {@code double} value is greater than the other one
	 * @since 1.3.0
	 */
	public static double lowerThanOrEqualTo(final String name, final double value, final double other) {
		notNull("name", name);
		if (other < value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (lower than or equal to " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code byte} value is greater than the other one.</p>
	 * @param name the name of the {@code byte} value
	 * @param value the {@code byte} value to validate
	 * @param other the other {@code byte} value
	 * @return the validated {@code byte} value
	 * @throws IllegalArgumentException if the {@code byte} value is lower than or equal to the other one
	 * @since 1.3.0
	 */
	public static byte greaterThan(final String name, final byte value, final byte other) {
		notNull("name", name);
		if (other >= value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (greater than " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code short} value is greater than the other one.</p>
	 * @param name the name of the {@code short} value
	 * @param value the {@code short} value to validate
	 * @param other the other {@code short} value
	 * @return the validated {@code short} value
	 * @throws IllegalArgumentException if the {@code short} value is lower than or equal to the other one
	 * @since 1.3.0
	 */
	public static short greaterThan(final String name, final short value, final short other) {
		notNull("name", name);
		if (other >= value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (greater than " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code char} value is greater than the other one.</p>
	 * @param name the name of the {@code char} value
	 * @param value the {@code char} value to validate
	 * @param other the other {@code char} value
	 * @return the validated {@code char} value
	 * @throws IllegalArgumentException if the {@code char} value is lower than or equal to the other one
	 * @since 1.3.0
	 */
	public static char greaterThan(final String name, final char value, final char other) {
		notNull("name", name);
		if (other >= value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (greater than " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code int} value is greater than the other one.</p>
	 * @param name the name of the {@code int} value
	 * @param value the {@code int} value to validate
	 * @param other the other {@code int} value
	 * @return the validated {@code int} value
	 * @throws IllegalArgumentException if the {@code int} value is lower than or equal to the other one
	 * @since 1.3.0
	 */
	public static int greaterThan(final String name, final int value, final int other) {
		notNull("name", name);
		if (other >= value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (greater than " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code long} value is greater than the other one.</p>
	 * @param name the name of the {@code long} value
	 * @param value the {@code long} value to validate
	 * @param other the other {@code long} value
	 * @return the validated {@code long} value
	 * @throws IllegalArgumentException if the {@code long} value is lower than or equal to the other one
	 * @since 1.3.0
	 */
	public static long greaterThan(final String name, final long value, final long other) {
		notNull("name", name);
		if (other >= value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (greater than " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code float} value is greater than the other one.</p>
	 * @param name the name of the {@code float} value
	 * @param value the {@code float} value to validate
	 * @param other the other {@code float} value
	 * @return the validated {@code float} value
	 * @throws IllegalArgumentException if the {@code float} value is lower than or equal to the other one
	 * @since 1.3.0
	 */
	public static float greaterThan(final String name, final float value, final float other) {
		notNull("name", name);
		if (other >= value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (greater than " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code double} value is greater than the other one.</p>
	 * @param name the name of the {@code double} value
	 * @param value the {@code double} value to validate
	 * @param other the other {@code double} value
	 * @return the validated {@code double} value
	 * @throws IllegalArgumentException if the {@code double} value is lower than or equal to the other one
	 * @since 1.3.0
	 */
	public static double greaterThan(final String name, final double value, final double other) {
		notNull("name", name);
		if (other >= value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (greater than " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code byte} value is greater than or equal to the other one.</p>
	 * @param name the name of the {@code byte} value
	 * @param value the {@code byte} value to validate
	 * @param other the other {@code byte} value
	 * @return the validated {@code byte} value
	 * @throws IllegalArgumentException if the {@code byte} value is lower than the other one
	 * @since 1.3.0
	 */
	public static byte greaterThanOrEqualTo(final String name, final byte value, final byte other) {
		notNull("name", name);
		if (other > value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (greater than or equal to " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code short} value is greater than or equal to the other one.</p>
	 * @param name the name of the {@code short} value
	 * @param value the {@code short} value to validate
	 * @param other the other {@code short} value
	 * @return the validated {@code short} value
	 * @throws IllegalArgumentException if the {@code short} value is lower than the other one
	 * @since 1.3.0
	 */
	public static short greaterThanOrEqualTo(final String name, final short value, final short other) {
		notNull("name", name);
		if (other > value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (greater than or equal to " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code char} value is greater than or equal to the other one.</p>
	 * @param name the name of the {@code char} value
	 * @param value the {@code char} value to validate
	 * @param other the other {@code char} value
	 * @return the validated {@code char} value
	 * @throws IllegalArgumentException if the {@code char} value is lower than the other one
	 * @since 1.3.0
	 */
	public static char greaterThanOrEqualTo(final String name, final char value, final char other) {
		notNull("name", name);
		if (other > value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (greater than or equal to " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code int} value is greater than or equal to the other one.</p>
	 * @param name the name of the {@code int} value
	 * @param value the {@code int} value to validate
	 * @param other the other {@code int} value
	 * @return the validated {@code int} value
	 * @throws IllegalArgumentException if the {@code int} value is lower than the other one
	 * @since 1.3.0
	 */
	public static int greaterThanOrEqualTo(final String name, final int value, final int other) {
		notNull("name", name);
		if (other > value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (greater than or equal to " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code long} value is greater than or equal to the other one.</p>
	 * @param name the name of the {@code long} value
	 * @param value the {@code long} value to validate
	 * @param other the other {@code long} value
	 * @return the validated {@code long} value
	 * @throws IllegalArgumentException if the {@code long} value is lower than the other one
	 * @since 1.3.0
	 */
	public static long greaterThanOrEqualTo(final String name, final long value, final long other) {
		notNull("name", name);
		if (other > value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (greater than or equal to " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code float} value is greater than or equal to the other one.</p>
	 * @param name the name of the {@code float} value
	 * @param value the {@code float} value to validate
	 * @param other the other {@code float} value
	 * @return the validated {@code float} value
	 * @throws IllegalArgumentException if the {@code float} value is lower than the other one
	 * @since 1.3.0
	 */
	public static float greaterThanOrEqualTo(final String name, final float value, final float other) {
		notNull("name", name);
		if (other > value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (greater than or equal to " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code double} value is greater than or equal to the other one.</p>
	 * @param name the name of the {@code double} value
	 * @param value the {@code double} value to validate
	 * @param other the other {@code double} value
	 * @return the validated {@code double} value
	 * @throws IllegalArgumentException if the {@code double} value is lower than the other one
	 * @since 1.3.0
	 */
	public static double greaterThanOrEqualTo(final String name, final double value, final double other) {
		notNull("name", name);
		if (other > value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (greater than or equal to " + ToString.toString(other) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code byte} value is between both other ones.</p>
	 * @param name the name of the {@code byte} value
	 * @param value the {@code byte} value to validate
	 * @param from the from {@code byte} value
	 * @param to the to {@code byte} value
	 * @return the validated {@code byte} value
	 * @throws IllegalArgumentException if the {@code byte} value is not between both other ones
	 * @since 1.3.0
	 */
	public static byte between(final String name, final byte value, final byte from, final byte to) {
		notNull("name", name);
		if (from > value || to < value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (between " + ToString.toString(from) + " and " + ToString.toString(to) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code short} value is between both other ones.</p>
	 * @param name the name of the {@code short} value
	 * @param value the {@code short} value to validate
	 * @param from the from {@code short} value
	 * @param to the to {@code short} value
	 * @return the validated {@code short} value
	 * @throws IllegalArgumentException if the {@code short} value is not between both other ones
	 * @since 1.3.0
	 */
	public static short between(final String name, final short value, final short from, final short to) {
		notNull("name", name);
		if (from > value || to < value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (between " + ToString.toString(from) + " and " + ToString.toString(to) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code char} value is between both other ones.</p>
	 * @param name the name of the {@code char} value
	 * @param value the {@code char} value to validate
	 * @param from the from {@code char} value
	 * @param to the to {@code char} value
	 * @return the validated {@code char} value
	 * @throws IllegalArgumentException if the {@code char} value is not between both other ones
	 * @since 1.3.0
	 */
	public static char between(final String name, final char value, final char from, final char to) {
		notNull("name", name);
		if (from > value || to < value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (between " + ToString.toString(from) + " and " + ToString.toString(to) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code int} value is between both other ones.</p>
	 * @param name the name of the {@code int} value
	 * @param value the {@code int} value to validate
	 * @param from the from {@code int} value
	 * @param to the to {@code int} value
	 * @return the validated {@code int} value
	 * @throws IllegalArgumentException if the {@code int} value is not between both other ones
	 * @since 1.3.0
	 */
	public static int between(final String name, final int value, final int from, final int to) {
		notNull("name", name);
		if (from > value || to < value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (between " + ToString.toString(from) + " and " + ToString.toString(to) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code long} value is between both other ones.</p>
	 * @param name the name of the {@code long} value
	 * @param value the {@code long} value to validate
	 * @param from the from {@code long} value
	 * @param to the to {@code long} value
	 * @return the validated {@code long} value
	 * @throws IllegalArgumentException if the {@code long} value is not between both other ones
	 * @since 1.3.0
	 */
	public static long between(final String name, final long value, final long from, final long to) {
		notNull("name", name);
		if (from > value || to < value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (between " + ToString.toString(from) + " and " + ToString.toString(to) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code float} value is between both other ones.</p>
	 * @param name the name of the {@code float} value
	 * @param value the {@code float} value to validate
	 * @param from the from {@code float} value
	 * @param to the to {@code float} value
	 * @return the validated {@code float} value
	 * @throws IllegalArgumentException if the {@code float} value is not between both other ones
	 * @since 1.3.0
	 */
	public static float between(final String name, final float value, final float from, final float to) {
		notNull("name", name);
		if (from > value || to < value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (between " + ToString.toString(from) + " and " + ToString.toString(to) + " expected)");
		}
		return value;
	}

	/**
	 * <p>Ensure the {@code double} value is between both other ones.</p>
	 * @param name the name of the {@code double} value
	 * @param value the {@code double} value to validate
	 * @param from the from {@code double} value
	 * @param to the to {@code double} value
	 * @return the validated {@code double} value
	 * @throws IllegalArgumentException if the {@code double} value is not between both other ones
	 * @since 1.3.0
	 */
	public static double between(final String name, final double value, final double from, final double to) {
		notNull("name", name);
		if (from > value || to < value) {
			throw new IllegalArgumentException("Invalid " + name + ": " + ToString.toString(value) + " (between " + ToString.toString(from) + " and " + ToString.toString(to) + " expected)");
		}
		return value;
	}
}