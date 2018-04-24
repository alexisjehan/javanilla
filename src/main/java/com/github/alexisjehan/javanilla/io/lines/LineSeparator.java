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
package com.github.alexisjehan.javanilla.io.lines;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.IntStream;

/**
 * <p>Enumeration of line separator types used by {@link LineReader} and {@link LineWriter}.</p>
 * @since 1.0
 */
public enum LineSeparator {

	/**
	 * <p>"Line Feed" char line separator type, used mainly on Linux and MacOS operating systems.</p>
	 * @since 1.0
	 */
	LF("\n") {
		@Override
		int read(final Reader reader, final StringBuilder builder) throws IOException {
			int c;
			while (-1 != (c = reader.read())) {
				if ('\n' == c) {
					break;
				}
				builder.append((char) c);
			}
			return c;
		}
	},

	/**
	 * <p>"Carriage Return" followed by "Line Feed" chars line separator type, used mainly on the Windows operating
	 * system.</p>
	 * @since 1.0
	 */
	CR_LF("\r\n") {
		@Override
		int read(final Reader reader, final StringBuilder builder) throws IOException {
			int c1;
			int c2;
			while (-1 != (c1 = reader.read())) {
				if ('\r' == c1) {
					if ('\n' == (c2 = reader.read())) {
						return c2;
					}
					builder.append((char) c1);
					if (-1 == c2) {
						return c2;
					}
					builder.append((char) c2);
				} else {
					builder.append((char) c1);
				}
			}
			return c1;
		}
	},

	/**
	 * <p>"Carriage Return" char line separator type, used on old MacOS operating systems.</p>
	 * @since 1.0
	 */
	CR("\r") {
		@Override
		int read(final Reader reader, final StringBuilder builder) throws IOException {
			int c;
			while (-1 != (c = reader.read())) {
				if ('\r' == c) {
					break;
				}
				builder.append((char) c);
			}
			return c;
		}
	},

	/**
	 * <p>"Line Feed" or "Carriage Return" char line separator type on read, system's default line separator
	 * representation on write.</p>
	 * @since 1.0
	 */
	DEFAULT(System.lineSeparator()) {
		@Override
		int read(final Reader reader, final StringBuilder builder) throws IOException {
			int c;
			while (-1 != (c = reader.read())) {
				if ('\n' == c || '\r' == c) {
					break;
				}
				builder.append((char) c);
			}
			return c;
		}
	};

	/**
	 * <p>Limit on the characters sample to read while detecting the {@code LineSeparator} type over a
	 * {@code Reader}.</p>
	 * @since 1.0
	 */
	private static final int DETECT_LIMIT = 8000;

	/**
	 * <p>{@code String} representation of the current {@code LineSeparator} type.</p>
	 * @since 1.0
	 */
	private final String string;

	/**
	 * <p>Enumeration constructor.</p>
	 * @param string the {@code String} representation
	 * @since 1.0
	 */
	LineSeparator(final String string) {
		this.string = string;
	}

	/**
	 * <p>Read the next line from the given {@code Reader} using the current {@code LineSeparator} strategy.</p>
	 * @param reader the {@code Reader} to read from
	 * @param builder the {@code StringBuilder} to wrote the line to
	 * @return the {@code int} value of the last read {@code char}
	 * @throws IOException might occurs with I/O operations
	 * @since 1.0
	 */
	abstract int read(final Reader reader, final StringBuilder builder) throws IOException;

	/**
	 * <p>Return the {@code String} representation.</p>
	 * @return the {@code String} representation
	 * @since 1.0
	 */
	@Override
	public String toString() {
		return string;
	}

	/**
	 * <p>Attempt to detect the {@code LineSeparator} type of the given file {@code Path} using a default
	 * {@code Charset} reading a sample.</p>
	 * @param file the file {@code Path} to analyze
	 * @return the detected {@code LineSeparator} if one has been found, {@code DEFAULT} otherwise
	 * @throws IOException might occurs with I/O operations
	 * @throws NullPointerException if the {@code Path} is {@code null}
	 * @since 1.0
	 */
	public static LineSeparator detect(final Path file) throws IOException {
		return detect(file, Charset.defaultCharset());
	}

	/**
	 * <p>Attempt to detect the {@code LineSeparator} type of the given file {@code Path} using the given
	 * {@code Charset} reading a sample.</p>
	 * @param file the file {@code Path} to analyze
	 * @param charset the {@code Charset} of the file
	 * @return the detected {@code LineSeparator} if one has been found, {@code DEFAULT} otherwise
	 * @throws IOException might occurs with I/O operations
	 * @throws NullPointerException whether the {@code Path} or {@code Charset} is {@code null}
	 * @since 1.0
	 */
	public static LineSeparator detect(final Path file, final Charset charset) throws IOException {
		if (null == file) {
			throw new NullPointerException("Invalid file (not null expected)");
		}
		if (null == charset) {
			throw new NullPointerException("Invalid charset (not null expected)");
		}
		try (final var reader = Files.newBufferedReader(file, charset)) {
			return detect(reader);
		}
	}

	/**
	 * <p>Attempt to detect the {@code LineSeparator} type of the given {@code Reader} using a sample.</p>
	 * <p><b>Note</b>: The {@code Reader} need to support {@link Reader#mark(int)}.</p>
	 * @param reader the {@code Reader} to analyze
	 * @return the detected {@code LineSeparator} if one has been found, {@code DEFAULT} otherwise
	 * @throws IOException might occurs with I/O operations
	 * @throws NullPointerException if the {@code Reader} is {@code null}
	 * @throws IllegalArgumentException if the {@code Reader} does not support {@link Reader#mark(int)}
	 * @since 1.0
	 */
	public static LineSeparator detect(final Reader reader) throws IOException {
		if (null == reader) {
			throw new NullPointerException("Invalid reader (not null expected)");
		}
		if (!reader.markSupported()) {
			throw new IllegalArgumentException("Invalid reader (mark is not supported)");
		}
		reader.mark(DETECT_LIMIT);
		final var counts = new int[3];
		int c1;
		int c2;
		var i = 0;
		while (-1 != (c1 = reader.read())) {
			if ('\n' == c1) {
				++counts[LF.ordinal()];
			} else if ('\r' == c1) {
				if ('\n' == (c2 = reader.read())) {
					++counts[CR_LF.ordinal()];
				} else {
					++counts[CR.ordinal()];
					if ('\r' == c2) {
						++counts[CR.ordinal()];
					}
				}
			}
			if (DETECT_LIMIT <= ++i) {
				break;
			}
		}
		reader.reset();
		final var max = IntStream.of(counts).max().getAsInt();
		final var min = IntStream.of(counts).min().getAsInt();
		if (max != min) {
			if (max == counts[LF.ordinal()]) {
				return LF;
			} else if (max == counts[CR_LF.ordinal()]) {
				return CR_LF;
			} else {
				return CR;
			}
		}
		return DEFAULT;
	}
}