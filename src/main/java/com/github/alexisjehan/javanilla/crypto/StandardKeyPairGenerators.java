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
package com.github.alexisjehan.javanilla.crypto;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * <p>A {@link KeyPairGenerator} factory to get standard instances without throwing checked exceptions.</p>
 * @since 1.3.1
 */
public final class StandardKeyPairGenerators {

	/**
	 * <p>Constructor not available.</p>
	 * @since 1.3.1
	 */
	private StandardKeyPairGenerators() {
		// Not available
	}

	/**
	 * <p>Get a new "DiffieHellman" {@code KeyPairGenerator} instance.</p>
	 * @return a "DiffieHellman" {@code KeyPairGenerator} instance
	 * @since 1.3.1
	 */
	public static KeyPairGenerator getDiffieHellmanInstance() {
		return getInstance("DiffieHellman");
	}

	/**
	 * <p>Get a new "DSA" {@code KeyPairGenerator} instance.</p>
	 * @return a "DSA" {@code KeyPairGenerator} instance
	 * @since 1.3.1
	 */
	public static KeyPairGenerator getDsaInstance() {
		return getInstance("DSA");
	}

	/**
	 * <p>Get a new "RSA" {@code KeyPairGenerator} instance.</p>
	 * @return a "RSA" {@code KeyPairGenerator} instance
	 * @since 1.3.1
	 */
	public static KeyPairGenerator getRsaInstance() {
		return getInstance("RSA");
	}

	/**
	 * <p>Get a new {@code KeyPairGenerator} instance without throwing {@code NoSuchAlgorithmException}.</p>
	 * @param algorithm the {@code KeyPairGenerator} algorithm
	 * @return a {@code KeyPairGenerator} instance of the provided algorithm
	 * @since 1.3.1
	 */
	private static KeyPairGenerator getInstance(final String algorithm) {
		try {
			return KeyPairGenerator.getInstance(algorithm);
		} catch (final NoSuchAlgorithmException e) {
			throw new AssertionError(e);
		}
	}
}