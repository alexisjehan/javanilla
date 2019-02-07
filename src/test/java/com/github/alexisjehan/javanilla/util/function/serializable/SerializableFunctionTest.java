/*
 * MIT License
 *
 * Copyright (c) 2018-2019 Alexis Jehan
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
package com.github.alexisjehan.javanilla.util.function.serializable;

import com.github.alexisjehan.javanilla.io.Serializables;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

/**
 * <p>{@link SerializableFunction} unit tests.</p>
 */
final class SerializableFunctionTest {

	@Test
	void testApply() {
		final var serializableFunction = (SerializableFunction<Integer, Integer>) t -> t + 1;
		assertThat(serializableFunction.apply(1)).isEqualTo(2);
		assertThat(serializableFunction.apply(3)).isEqualTo(4);
	}

	@Test
	void testCompose() {
		final var serializableFunction1 = (SerializableFunction<Integer, Integer>) t -> t + 1;
		final var serializableFunction2 = (SerializableFunction<Integer, Integer>) t -> -t;
		assertThat(serializableFunction1.compose(serializableFunction2).apply(1)).isEqualTo(0);
		assertThat(serializableFunction1.compose(serializableFunction2).apply(3)).isEqualTo(-2);
		assertThat(serializableFunction2.compose(serializableFunction1).apply(1)).isEqualTo(-2);
		assertThat(serializableFunction2.compose(serializableFunction1).apply(3)).isEqualTo(-4);
	}

	@Test
	void testComposeInvalid() {
		final var serializableFunction = (SerializableFunction<Integer, Integer>) t -> t + 1;
		assertThatNullPointerException().isThrownBy(() -> serializableFunction.compose(null));
	}

	@Test
	void testAndThen() {
		final var serializableFunction1 = (SerializableFunction<Integer, Integer>) t -> t + 1;
		final var serializableFunction2 = (SerializableFunction<Integer, Integer>) t -> -t;
		assertThat(serializableFunction1.andThen(serializableFunction2).apply(1)).isEqualTo(-2);
		assertThat(serializableFunction1.andThen(serializableFunction2).apply(3)).isEqualTo(-4);
		assertThat(serializableFunction2.andThen(serializableFunction1).apply(1)).isEqualTo(0);
		assertThat(serializableFunction2.andThen(serializableFunction1).apply(3)).isEqualTo(-2);
	}

	@Test
	void testAndThenInvalid() {
		final var serializableFunction = (SerializableFunction<Integer, Integer>) t -> t + 1;
		assertThatNullPointerException().isThrownBy(() -> serializableFunction.andThen(null));
	}

	@Test
	void testIdentity() {
		final var serializableFunction = SerializableFunction.identity();
		assertThat(serializableFunction.apply(1)).isEqualTo(1);
		assertThat(serializableFunction.apply(3)).isEqualTo(3);
	}

	@Test
	void testOf() {
		final var serializableFunction = SerializableFunction.of((Function<Integer, Integer>) t -> t + 1);
		assertThat(serializableFunction.apply(1)).isEqualTo(2);
		assertThat(serializableFunction.apply(3)).isEqualTo(4);
	}

	@Test
	void testOfInvalid() {
		assertThatNullPointerException().isThrownBy(() -> SerializableFunction.of(null));
	}

	@Test
	void testSerializable() {
		final var serializableFunction = Serializables.<SerializableFunction<Integer, Integer>>deserialize(
				Serializables.serialize(
						(SerializableFunction<Integer, Integer>) t -> t + 1
				)
		);
		assertThat(serializableFunction.apply(1)).isEqualTo(2);
		assertThat(serializableFunction.apply(3)).isEqualTo(4);
	}
}