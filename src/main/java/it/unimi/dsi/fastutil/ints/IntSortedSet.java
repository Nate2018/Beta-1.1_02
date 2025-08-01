/* Generic definitions */

/* Assertions (useful to generate conditional code) */
/* Current type and class (and size, if applicable) */
/* Value methods */
/* Interfaces (keys) */
/* Interfaces (values) */
/* Abstract implementations (keys) */
/* Abstract implementations (values) */
/* Static containers (keys) */
/* Static containers (values) */
/* Implementations */
/* Synchronized wrappers */
/* Unmodifiable wrappers */
/* Other wrappers */
/* Methods (keys) */
/* Methods (values) */
/* Methods (keys/values) */
/* Methods that have special names depending on keys (but the special names depend on values) */
/* Equality */
/* Object/Reference-only definitions (keys) */
/* Primitive-type-only definitions (keys) */
/* Object/Reference-only definitions (values) */
/*		 
 * Copyright (C) 2002-2013 Sebastiano Vigna 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package it.unimi.dsi.fastutil.ints;

import java.util.SortedSet;
import java.util.Collection;

/**
 * A type-specific {@link SortedSet}; provides some additional methods that use
 * polymorphism to avoid (un)boxing.
 *
 * <P>
 * Additionally, this interface strengthens {@link #iterator()},
 * {@link #comparator()} (for primitive types),
 * {@link SortedSet#subSet(Object,Object)}, {@link SortedSet#headSet(Object)}
 * and {@link SortedSet#tailSet(Object)}.
 *
 * @see SortedSet
 */
public interface IntSortedSet extends IntSet, SortedSet<Integer> {
	/**
	 * Returns a type-specific {@link it.unimi.dsi.fastutil.BidirectionalIterator}
	 * on the elements in this set, starting from a given element of the domain
	 * (optional operation).
	 *
	 * <P>
	 * This method returns a type-specific bidirectional iterator with given
	 * starting point. The starting point is any element comparable to the elements
	 * of this set (even if it does not actually belong to the set). The next
	 * element of the returned iterator is the least element of the set that is
	 * greater than the starting point (if there are no elements greater than the
	 * starting point, {@link it.unimi.dsi.fastutil.BidirectionalIterator#hasNext()
	 * hasNext()} will return <code>false</code>). The previous element of the
	 * returned iterator is the greatest element of the set that is smaller than or
	 * equal to the starting point (if there are no elements smaller than or equal
	 * to the starting point,
	 * {@link it.unimi.dsi.fastutil.BidirectionalIterator#hasPrevious()
	 * hasPrevious()} will return <code>false</code>).
	 * 
	 * <P>
	 * Note that passing the last element of the set as starting point and calling
	 * {@link it.unimi.dsi.fastutil.BidirectionalIterator#previous() previous()} you
	 * can traverse the entire set in reverse order.
	 *
	 * @param fromElement an element to start from.
	 * @return a bidirectional iterator on the element in this set, starting at the
	 *         given element.
	 * @throws UnsupportedOperationException if this set does not support iterators
	 *                                       with a starting point.
	 */
	IntBidirectionalIterator iterator(int fromElement);

	/**
	 * Returns a type-specific {@link it.unimi.dsi.fastutil.BidirectionalIterator}
	 * iterator on the collection.
	 *
	 * <P>
	 * The iterator returned by the {@link #iterator()} method and by this method
	 * are identical; however, using this method you can save a type casting.
	 *
	 * Note that this specification strengthens the one given in the corresponding
	 * type-specific {@link Collection}.
	 *
	 * @deprecated As of <code>fastutil</code> 5, replaced by {@link #iterator()}.
	 */
	@Deprecated
	IntBidirectionalIterator intIterator();

	/**
	 * Returns a type-specific {@link it.unimi.dsi.fastutil.BidirectionalIterator}
	 * on the elements in this set.
	 *
	 * <P>
	 * This method returns a parameterised bidirectional iterator. The iterator can
	 * be moreover safely cast to a type-specific iterator.
	 *
	 * Note that this specification strengthens the one given in the corresponding
	 * type-specific {@link Collection}.
	 *
	 * @return a bidirectional iterator on the element in this set.
	 */
	IntBidirectionalIterator iterator();

	/**
	 * Returns a view of the portion of this sorted set whose elements range from
	 * <code>fromElement</code>, inclusive, to <code>toElement</code>, exclusive.
	 *
	 * <P>
	 * Note that this specification strengthens the one given in
	 * {@link SortedSet#subSet(Object,Object)}.
	 *
	 * @see SortedSet#subSet(Object,Object)
	 */
	IntSortedSet subSet(Integer fromElement, Integer toElement);

	/**
	 * Returns a view of the portion of this sorted set whose elements are strictly
	 * less than <code>toElement</code>.
	 *
	 * <P>
	 * Note that this specification strengthens the one given in
	 * {@link SortedSet#headSet(Object)}.
	 *
	 * @see SortedSet#headSet(Object)
	 */
	IntSortedSet headSet(Integer toElement);

	/**
	 * Returns a view of the portion of this sorted set whose elements are greater
	 * than or equal to <code>fromElement</code>.
	 *
	 * <P>
	 * Note that this specification strengthens the one given in
	 * {@link SortedSet#tailSet(Object)}.
	 *
	 * @see SortedSet#tailSet(Object)
	 */
	IntSortedSet tailSet(Integer fromElement);

	/**
	 * Returns the comparator associated with this sorted set, or null if it uses
	 * its elements' natural ordering.
	 *
	 * <P>
	 * Note that this specification strengthens the one given in
	 * {@link SortedSet#comparator()}.
	 *
	 * @see SortedSet#comparator()
	 */
	IntComparator comparator();

	/**
	 * @see SortedSet#subSet(Object,Object)
	 */
	IntSortedSet subSet(int fromElement, int toElement);

	/**
	 * @see SortedSet#headSet(Object)
	 */
	IntSortedSet headSet(int toElement);

	/**
	 * @see SortedSet#tailSet(Object)
	 */
	IntSortedSet tailSet(int fromElement);

	/**
	 * @see SortedSet#first()
	 */
	int firstInt();

	/**
	 * @see SortedSet#last()
	 */
	int lastInt();
}