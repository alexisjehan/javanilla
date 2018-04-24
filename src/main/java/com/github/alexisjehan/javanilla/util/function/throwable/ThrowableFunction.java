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
package com.github.alexisjehan.javanilla.util.function.throwable;

import com.github.alexisjehan.javanilla.lang.Throwables;

import java.util.function.Function;

/**
 * <p>Interface for a {@link Function} that may throw a {@link Throwable}.</p>
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 * @param <X> the type of the {@code Throwable}
 * @since 1.0
 */
@FunctionalInterface
public interface ThrowableFunction<T, R, X extends Throwable> {

	/**
	 * <p>Applies this function to the given argument.</p>
	 * @param t the function argument
	 * @return the function result
	 * @throws X may throw a {@code Throwable}
	 * @since 1.0
	 */
	R apply(final T t) throws X;

	/**
	 * <p>Returns a composed {@code ThrowableFunction} that first applies the before function to its input, and then
	 * applies this function to the result.</p>
	 * @param before the {@code ThrowableFunction} to apply before this function is applied
	 * @param <V> the type of input to the before function, and to the composed function
	 * @return a composed {@code ThrowableFunction} that first applies the before function and then applies this
	 * function
	 * @throws NullPointerException if the before {@code ThrowableFunction} is {@code null}
	 * @since 1.0
	 */
	default <V> ThrowableFunction<V, R, X> compose(final ThrowableFunction<? super V, ? extends T, ? extends X> before) {
		if (null == before) {
			throw new NullPointerException("Invalid before (not null expected)");
		}
		return v -> apply(before.apply(v));
	}

	/**
	 * <p>Returns a composed {@code ThrowableFunction} that first applies this function to its input, and then applies
	 * the after function to the result.</p>
	 * @param after the {@code ThrowableFunction} to apply after this function is applied
	 * @param <V> the type of output of the after function, and of the composed function
	 * @return a composed {@code ThrowableFunction} that first applies this function and then applies the after function
	 * @throws NullPointerException if the after {@code ThrowableFunction} is {@code null}
	 * @since 1.0
	 */
	default <V> ThrowableFunction<T, V, X> andThen(final ThrowableFunction<? super R, ? extends V, ? extends X> after) {
		if (null == after) {
			throw new NullPointerException("Invalid after (not null expected)");
		}
		return t -> after.apply(apply(t));
	}

	/**
	 * <p>Converts the given {@code ThrowableFunction} to a {@code Function} that may throw an unchecked
	 * {@code Throwable}.</p>
	 * @param throwableFunction the given {@code ThrowableFunction}
	 * @param <T> the type of the input to the function
	 * @param <R> the type of the result of the function
	 * @param <X> the type of the {@code Throwable}
	 * @return the converted {@code Function}
	 * @throws NullPointerException if the {@code ThrowableFunction} is {@code null}
	 * @since 1.0
	 */
	static <T, R, X extends Throwable> Function<T, R> unchecked(final ThrowableFunction<? super T, ? extends R, ? extends X> throwableFunction) {
		if (null == throwableFunction) {
			throw new NullPointerException("Invalid throwable function (not null expected)");
		}
		return t -> {
			try {
				return throwableFunction.apply(t);
			} catch (final Throwable x) {
				throw Throwables.unchecked(x);
			}
		};
	}

	/**
	 * <p>Create a {@code ThrowableFunction} that throws nothing from the given {@code Function}.</p>
	 * @param function the given {@code Function}
	 * @param <T> the type of the input to the function
	 * @param <R> the type of the result of the function
	 * @param <X> the type of the {@code Throwable}
	 * @return the created {@code ThrowableFunction}
	 * @throws NullPointerException if the {@code Function} is {@code null}
	 * @since 1.0
	 */
	static <T, R, X extends Throwable> ThrowableFunction<T, R, X> of(final Function<? super T, ? extends R> function) {
		if (null == function) {
			throw new NullPointerException("Invalid function (not null expected)");
		}
		return function::apply;
	}
}