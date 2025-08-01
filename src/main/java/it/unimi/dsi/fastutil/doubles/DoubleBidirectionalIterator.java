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
package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.BidirectionalIterator;
import it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;

/**
 * A type-specific bidirectional iterator; provides an additional method to
 * avoid (un)boxing, and the possibility to skip elements backwards.
 *
 * @see BidirectionalIterator
 */
public interface DoubleBidirectionalIterator extends DoubleIterator, ObjectBidirectionalIterator<Double> {
	/**
	 * Returns the previous element as a primitive type.
	 *
	 * @return the previous element in the iteration.
	 * @see java.util.ListIterator#previous()
	 */
	double previousDouble();

	/**
	 * Moves back for the given number of elements.
	 *
	 * <P>
	 * The effect of this call is exactly the same as that of calling
	 * {@link #previous()} for <code>n</code> times (possibly stopping if
	 * {@link #hasPrevious()} becomes false).
	 *
	 * @param n the number of elements to skip back.
	 * @return the number of elements actually skipped.
	 * @see java.util.Iterator#next()
	 */
	int back(int n);
}