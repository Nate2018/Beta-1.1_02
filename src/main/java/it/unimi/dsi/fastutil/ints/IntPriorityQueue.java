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
 * Copyright (C) 2003-2013 Paolo Boldi and Sebastiano Vigna 
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

import java.util.NoSuchElementException;
import it.unimi.dsi.fastutil.PriorityQueue;

/**
 * A type-specific {@link PriorityQueue}; provides some additional methods that
 * use polymorphism to avoid (un)boxing.
 *
 * <P>
 * Additionally, this interface strengthens {@link #comparator()}.
 */
public interface IntPriorityQueue extends PriorityQueue<Integer> {
	/**
	 * Enqueues a new element.
	 *
	 * @param x the element to enqueue.
	 */
	void enqueue(int x);

	/**
	 * Dequeues the {@linkplain #first() first} element from the queue.
	 *
	 * @return the dequeued element.
	 * @throws NoSuchElementException if the queue is empty.
	 */
	int dequeueInt();

	/**
	 * Returns the first element of the queue.
	 *
	 * @return the first element.
	 * @throws NoSuchElementException if the queue is empty.
	 */
	int firstInt();

	/**
	 * Returns the last element of the queue, that is, the element the would be
	 * dequeued last (optional operation).
	 *
	 * @return the last element.
	 * @throws NoSuchElementException if the queue is empty.
	 */
	int lastInt();

	/**
	 * Returns the comparator associated with this sorted set, or null if it uses
	 * its elements' natural ordering.
	 *
	 * <P>
	 * Note that this specification strengthens the one given in
	 * {@link PriorityQueue#comparator()}.
	 *
	 * @see PriorityQueue#comparator()
	 */
	IntComparator comparator();
}