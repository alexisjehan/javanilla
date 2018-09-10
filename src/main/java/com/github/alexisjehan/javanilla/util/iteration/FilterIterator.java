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
package com.github.alexisjehan.javanilla.util.iteration;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>An abstract {@link Iterator} filter to create decorators from the current position.</p>
 * @since 1.2.0
 */
public abstract class FilterIterator<E> implements Iterator<E> {

	/**
	 * <p>Delegated {@code Iterator}.</p>
	 * @since 1.2.0
	 */
	protected final Iterator<? extends E> iterator;

	/**
	 * <p>Constructor with an {@code Iterator} to decorate.</p>
	 * @param iterator the {@code Iterator} to decorate
	 * @throws NullPointerException if the {@code Iterator} is {@code null}
	 * @since 1.2.0
	 */
	protected FilterIterator(final Iterator<? extends E> iterator) {
		if (null == iterator) {
			throw new NullPointerException("Invalid Iterator (not null expected)");
		}
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public E next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		return iterator.next();
	}

	@Override
	public void remove() {
		iterator.remove();
	}
}