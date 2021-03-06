/*
 * MIT License
 *
 * Copyright (c) 2018-2021 Alexis Jehan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.alexisjehan.javanilla.util.function.throwable;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UncheckedIOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIOException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

/**
 * <p>{@link ThrowableSupplier} unit tests.</p>
 */
final class ThrowableSupplierTest {

	@Test
	void testGet() throws IOException {
		final var throwableSupplier = (ThrowableSupplier<Integer, IOException>) () -> 1;
		final var exceptionThrowableSupplier = (ThrowableSupplier<Integer, IOException>) () -> {
			throw new IOException();
		};
		assertThat(throwableSupplier.get()).isEqualTo(1);
		assertThatIOException().isThrownBy(exceptionThrowableSupplier::get);
	}

	@Test
	void testUnchecked() {
		final var throwableSupplier = (ThrowableSupplier<Integer, IOException>) () -> 1;
		final var exceptionThrowableSupplier = (ThrowableSupplier<Integer, IOException>) () -> {
			throw new IOException();
		};
		assertThat(ThrowableSupplier.unchecked(throwableSupplier).get()).isEqualTo(1);
		assertThatExceptionOfType(UncheckedIOException.class).isThrownBy(() -> ThrowableSupplier.unchecked(exceptionThrowableSupplier).get());
	}

	@Test
	void testUncheckedInvalid() {
		assertThatNullPointerException().isThrownBy(() -> ThrowableSupplier.unchecked(null));
	}

	@Test
	void testOf() throws Throwable {
		final var throwableSupplier = ThrowableSupplier.of(() -> 1);
		assertThat(throwableSupplier.get()).isEqualTo(1);
	}

	@Test
	void testOfInvalid() {
		assertThatNullPointerException().isThrownBy(() -> ThrowableSupplier.of(null));
	}
}