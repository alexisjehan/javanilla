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
package com.github.alexisjehan.javanilla.io;

import com.github.alexisjehan.javanilla.misc.tuples.SerializablePair;
import com.github.alexisjehan.javanilla.misc.tuples.Single;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

/**
 * <p>{@link Serializables} unit tests.</p>
 */
final class SerializablesTest {

	private static final SerializablePair<String, Integer> SERIALIZABLE = SerializablePair.of("foo", 1);

	@Test
	void testSerializeDeserialize() {
		assertThat(Serializables.<SerializablePair<String, Integer>>deserialize(Serializables.serialize(SERIALIZABLE))).isEqualTo(SERIALIZABLE);
		assertThat(Serializables.<SerializablePair<String, Integer>>deserialize(Serializables.serialize(null))).isNull();

		// Serializable containing a non-serializable attribute
		final var exceptionSerializable = new ArrayList<>(List.of(Single.of("bar")));
		assertThatExceptionOfType(SerializationException.class)
				.isThrownBy(() -> Serializables.serialize(exceptionSerializable))
				.withRootCauseExactlyInstanceOf(NotSerializableException.class);

		// Serialized corrupted
		final var exceptionSerialized = Serializables.serialize(SERIALIZABLE);
		exceptionSerialized[0] = (byte) 1;
		assertThatExceptionOfType(SerializationException.class)
				.isThrownBy(() -> Serializables.deserialize(exceptionSerialized))
				.withRootCauseExactlyInstanceOf(StreamCorruptedException.class);
	}

	@Test
	void testSerializeInvalid() {
		assertThatNullPointerException().isThrownBy(() -> Serializables.serialize(null, SERIALIZABLE));
	}

	@Test
	void testDeserializeInvalid() {
		assertThatNullPointerException().isThrownBy(() -> Serializables.deserialize((byte[]) null));
		assertThatNullPointerException().isThrownBy(() -> Serializables.deserialize((InputStream) null));
	}
}