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
package com.github.alexisjehan.javanilla.misc;

import com.github.alexisjehan.javanilla.lang.Strings;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * <p>An immutable formatter to pretty display values of several types as {@code String}s.</p>
 * <p><b>Note</b>: This class is serializable.</p>
 * <p><b>Note</b>: This class implements its own {@link #equals(Object)} and {@link #hashCode()} methods.</p>
 * @since 1.0
 */
public final class StringFormatter implements Serializable {

	/**
	 * <p>Serial version unique ID.</p>
	 * @since 1.0
	 */
	private static final long serialVersionUID = 4546811002575964622L;

	/**
	 * <p>Byte representation prefixes.</p>
	 * @since 1.0
	 */
	public enum BytePrefix {
		/**
		 * <p>SI prefix with a base of {@code 1000}.</p>
		 * @see <a href="https://en.wikipedia.org/wiki/SI_prefix">https://en.wikipedia.org/wiki/SI_prefix</a>
		 * @since 1.0
		 */
		SI(1000),

		/**
		 * <p>Binary prefix with a base of {@code 1024}.</p>
		 * @see <a href="https://en.wikipedia.org/wiki/Binary_prefix">https://en.wikipedia.org/wiki/Binary_prefix</a>
		 * @since 1.0
		 */
		BINARY(1024);

		/**
		 * <p>Base value.</p>
		 * @since 1.0
		 */
		final int base;

		/**
		 * <p>Enumeration constructor.</p>
		 * @param base the base value
		 * @since 1.0
		 */
		BytePrefix(final int base) {
			this.base = base;
		}
	}

	/**
	 * <p>Units suffixes.</p>
	 * @since 1.0
	 */
	private static final String[] UNITS = {"k", "M", "G", "T", "P", "E", "Z", "Y"};

	/**
	 * <p>Default {@code BytePrefix}.</p>
	 * @since 1.0
	 */
	private static final BytePrefix DEFAULT_BYTE_PREFIX = BytePrefix.BINARY;

	/**
	 * <p>Default float precision.</p>
	 * @since 1.0
	 */
	static final int DEFAULT_FLOAT_PRECISION = 2;

	/**
	 * <p>Default value to enable strict precision or not.</p>
	 * @since 1.0
	 */
	static final boolean DEFAULT_STRICT_PRECISION = false;

	/**
	 * <p>{@code StringFormatter}'s {@code Locale}.</p>
	 * @since 1.0
	 */
	private final Locale locale;

	/**
	 * <p>Float precision.</p>
	 * @since 1.0
	 */
	private final int floatPrecision;

	/**
	 * <p>Whether to enable strict precision or not.</p>
	 * @since 1.0
	 */
	private final boolean strictPrecision;

	/**
	 * <p>Formatter for {@code long} values.</p>
	 * @since 1.0
	 */
	private final NumberFormat longFormatter;

	/**
	 * <p>Formatter for {@code double} values.</p>
	 * @since 1.0
	 */
	private final DecimalFormat doubleFormatter;

	/**
	 * <p>Formatter for percent values.</p>
	 * @since 1.0
	 */
	private final NumberFormat percentFormatter;

	/**
	 * <p>Formatter for currency values.</p>
	 * @since 1.0
	 */
	private final NumberFormat currencyFormatter;

	/**
	 * <p>{@code Locale}'s delimiter.</p>
	 * @since 1.0
	 */
	private final String localeDelimiter;

	/**
	 * <p>Default constructor using default parameters.</p>
	 * @since 1.0
	 */
	public StringFormatter() {
		this(Locale.getDefault());
	}

	/**
	 * <p>Constructor with a custom {@code Locale} and the default float precision.</p>
	 * @param locale a custom {@code Locale}
	 * @throws NullPointerException if the {@code Locale} is {@code null}
	 * @since 1.0
	 */
	public StringFormatter(final Locale locale) {
		this(locale, DEFAULT_FLOAT_PRECISION);
	}

	/**
	 * <p>Constructor with customs {@code Locale} and float precision, and the default strict precision parameter.</p>
	 * @param locale a custom {@code Locale}
	 * @param floatPrecision a custom float precision
	 * @throws NullPointerException if the {@code Locale} is {@code null}
	 * @throws IllegalArgumentException if the float precision is negative
	 * @since 1.0
	 */
	public StringFormatter(final Locale locale, final int floatPrecision) {
		this(locale, floatPrecision, DEFAULT_STRICT_PRECISION);
	}

	/**
	 * <p>Complete constructor with customs {@code Locale}, float precision and strict precision parameter.</p>
	 * @param locale a custom {@code Locale}
	 * @param floatPrecision a custom float precision
	 * @param strictPrecision a custom strict precision parameter
	 * @throws NullPointerException if the {@code Locale} is {@code null}
	 * @throws IllegalArgumentException if the float precision is negative
	 * @since 1.0
	 */
	public StringFormatter(final Locale locale, final int floatPrecision, final boolean strictPrecision) {
		if (null == locale) {
			throw new NullPointerException("Invalid locale (not null expected)");
		}
		if (0 > floatPrecision) {
			throw new IllegalArgumentException("Invalid float precision: " + floatPrecision + " (greater than or equal to 0 expected)");
		}
		this.locale = locale;
		this.floatPrecision = floatPrecision;
		this.strictPrecision = strictPrecision;
		longFormatter = NumberFormat.getIntegerInstance(locale);
		doubleFormatter = new DecimalFormat("###,##0." + Strings.repeat(strictPrecision ? '0' : '#', floatPrecision), new DecimalFormatSymbols(locale));
		percentFormatter = NumberFormat.getPercentInstance(locale);
		percentFormatter.setMaximumFractionDigits(floatPrecision);
		if (strictPrecision) {
			percentFormatter.setMinimumFractionDigits(floatPrecision);
		} else {
			percentFormatter.setMinimumFractionDigits(0);
		}
		currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		currencyFormatter.setMaximumFractionDigits(floatPrecision);
		if (strictPrecision) {
			currencyFormatter.setMinimumFractionDigits(floatPrecision);
		} else {
			currencyFormatter.setMinimumFractionDigits(0);
		}
		final var s = NumberFormat.getPercentInstance(locale).format(0L);
		localeDelimiter = s.substring(s.indexOf('0') + 1, s.indexOf('%'));
	}

	/**
	 * <p>Format a {@code int}/{@code long} value as a pretty {@code String}.</p>
	 * <p><b>Note</b>: The {@code Locale} attribute is used.</p>
	 * @param value the {@code long} value
	 * @return the {@code long} {@code String} representation
	 * @since 1.0
	 */
	public String format(final long value) {
		return longFormatter.format(value);
	}

	/**
	 * <p>Format a {@code float}/{@code double} value as a pretty {@code String}.</p>
	 * <p><b>Note</b>: {@code Locale}, float precision and strict precision parameter attributes are used.</p>
	 * @param value the {@code double} value
	 * @return the {@code double} {@code String} representation
	 * @since 1.0
	 */
	public String format(final double value) {
		return doubleFormatter.format(value);
	}

	/**
	 * <p>Format a currency value as a pretty {@code String}, the currency type is the one from the {@code Locale}.</p>
	 * <p><b>Note</b>: {@code Locale}, float precision and strict precision parameter attributes are used.</p>
	 * @param value the currency value
	 * @return the currency {@code String} representation
	 * @since 1.0
	 */
	public String formatCurrency(final double value) {
		return currencyFormatter.format(value);
	}

	/**
	 * <p>Format a percent value as a pretty {@code String}.</p>
	 * <p><b>Note</b>: {@code Locale}, float precision and strict precision parameter attributes are used.</p>
	 * @param progression the progression value
	 * @param total the total value
	 * @return the percent {@code String} representation
	 * @throws IllegalArgumentException if the progression is lower than 0 or greater than the total
	 * @since 1.0
	 */
	public String formatPercent(final double progression, final double total) {
		if (0 > progression || total < progression) {
			throw new IllegalArgumentException("Invalid progression: " + progression + " (between 0 and " + total + " expected)");
		}
		return percentFormatter.format(progression / total);
	}

	/**
	 * <p>Format a bytes value such as a file size as a pretty {@code String} using the default {@code BytePrefix}.</p>
	 * @param value the bytes value
	 * @return the bytes {@code String} representation
	 * @since 1.0
	 */
	public String formatBytes(final long value) {
		return formatBytes(value, DEFAULT_BYTE_PREFIX);
	}

	/**
	 * <p>Format a bytes value such as a file size as a pretty {@code String} using a custom {@code BytePrefix}.</p>
	 * <p><b>Note</b>: {@code Locale}, float precision and strict precision parameter attributes are used.</p>
	 * @param value the bytes value
	 * @param bytePrefix the {@code BytePrefix} to use
	 * @return the bytes {@code String} representation
	 * @throws NullPointerException if the {@code BytePrefix} is {@code null}
	 * @since 1.0
	 */
	public String formatBytes(final long value, final BytePrefix bytePrefix) {
		if (null == bytePrefix) {
			throw new NullPointerException("Invalid byte prefix (not null expected)");
		}
		if (bytePrefix.base > Math.abs(value)) {
			return format((double) value) + localeDelimiter + "B";
		}
		final var exponent = (int) (Math.log(value) / Math.log(bytePrefix.base));
		return format(value / Math.pow(bytePrefix.base, exponent))
				+ localeDelimiter
				+ UNITS[exponent - 1]
				+ (BytePrefix.BINARY == bytePrefix ? "iB" : "B");
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof StringFormatter)) {
			return false;
		}
		final var other = (StringFormatter) object;
		return Objects.equals(locale, other.locale)
				&& Objects.equals(floatPrecision, other.floatPrecision)
				&& Objects.equals(strictPrecision, other.strictPrecision);
	}

	@Override
	public int hashCode() {
		return Objects.hash(locale, floatPrecision, strictPrecision);
	}

	/**
	 * <p>Get the {@code Locale}.</p>
	 * @return the {@code Locale}
	 * @since 1.0
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * <p>Get the float precision.</p>
	 * @return the float precision
	 * @since 1.0
	 */
	public int getFloatPrecision() {
		return floatPrecision;
	}

	/**
	 * <p>Whether strict precision is enabled or not.</p>
	 * @return {@code true} if the strict precision is enabled
	 * @since 1.0
	 */
	public boolean hasStrictPrecision() {
		return strictPrecision;
	}
}