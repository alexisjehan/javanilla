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
package examples;

import com.github.alexisjehan.javanilla.lang.array.DoubleArrays;
import com.github.alexisjehan.javanilla.lang.array.ObjectArrays;
import com.github.alexisjehan.javanilla.util.Comparators;

public final class Example08 {

	public static void main(final String... args) {
		System.out.println("foo10".compareTo("foo2")); // Prints -1
		System.out.println(Comparators.NUMBER_AWARE.compare("foo10", "foo2")); // Prints 1
		System.out.println(Comparators.DOUBLE_ARRAYS.compare(
				DoubleArrays.of(0.0d, 1.0d, 2.0d),
				DoubleArrays.of(0.0d, 1.0d)
		)); // Prints 1
		System.out.println(Comparators.<String>array().compare(
				ObjectArrays.of("foo", "bar2"),
				ObjectArrays.of("foo", "bar1")
		)); // Prints 1
	}
}