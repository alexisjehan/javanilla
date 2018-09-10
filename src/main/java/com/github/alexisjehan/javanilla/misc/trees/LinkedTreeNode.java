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
package com.github.alexisjehan.javanilla.misc.trees;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>A {@link TreeNode} implementation which uses a {@link LinkedList} to store its children.</p>
 * <p><b>Note</b>: This class implements its own {@link #equals(Object)}, {@link #hashCode()} and {@link #toString()}
 * methods.</p>
 * @param <V> the value type
 * @since 1.2.0
 */
public final class LinkedTreeNode<V> implements TreeNode<V> {

	/**
	 * <p>Parent {@code TreeNode} or {@code null}.</p>
	 * @since 1.2.0
	 */
	private final TreeNode<V> parent;

	/**
	 * <p>{@code List} of children {@code TreeNode}s.</p>
	 * @since 1.2.0
	 */
	private final List<TreeNode<V>> children = new LinkedList<>();

	/**
	 * <p>Mutable value.</p>
	 * @since 1.2.0
	 */
	private V value;

	/**
	 * <p>Constructor of a new tree with his root {@code TreeNode} having the given value.</p>
	 * @param value the value of the root {@code TreeNode}
	 * @since 1.2.0
	 */
	public LinkedTreeNode(final V value) {
		this(null, value);
	}

	/**
	 * <p>Private constructor of a {@code TreeNode}.</p>
	 * @param parent the parent of the {@code TreeNode} or {@code null}
	 * @param value the value of the {@code TreeNode}
	 * @since 1.2.0
	 */
	private LinkedTreeNode(final LinkedTreeNode<V> parent, final V value) {
		this.parent = parent;
		this.value = value;
	}

	@Override
	public TreeNode<V> extend(final V value) {
		final var child = new LinkedTreeNode<>(this, value);
		children.add(child);
		return child;
	}

	@Override
	public boolean remove(final TreeNode<V> descendant) {
		if (null == descendant) {
			throw new NullPointerException("Invalid descendant (not null expected)");
		}
		if (children.remove(descendant)) {
			return true;
		}
		for (final var child : children) {
			if (child.remove(descendant)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void clear() {
		children.clear();
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public void setValue(final V value) {
		this.value = value;
	}

	@Override
	public Optional<TreeNode<V>> parent() {
		return Optional.ofNullable(parent);
	}

	@Override
	public List<TreeNode<V>> children() {
		return Collections.unmodifiableList(children);
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof TreeNode)) {
			return false;
		}
		final var other = (TreeNode) object;
		return Objects.equals(getValue(), other.getValue())
				&& Objects.equals(children(), other.children());
	}

	@Override
	public int hashCode() {
		var h = Objects.hashCode(value);
		for (final var child : children) {
			h += child.hashCode();
		}
		return h;
	}

	@Override
	public String toString() {
		return value + (!children.isEmpty() ? "{" + children.stream().map(TreeNode::toString).collect(Collectors.joining(", ")) + "}" : "");
	}
}