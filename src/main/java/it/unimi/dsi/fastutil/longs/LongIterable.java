/*
	* Copyright (C) 2002-2017 Sebastiano Vigna
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
package it.unimi.dsi.fastutil.longs;

import java.lang.Iterable;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * A type-specific {@link Iterable} that strengthens that specification of
 * {@link #iterator()} and {@link #forEach(Consumer)}.
 *
 * <p>
 * Note that whenever there exist a primitive consumer in
 * {@link java.util.function} (e.g., {@link java.util.function.IntConsumer}),
 * trying to access any version of {@link #forEach(Consumer)} using a lambda
 * expression with untyped arguments will generate an ambiguous method error.
 * This can be easily solved by specifying the type of the argument, as in
 * 
 * <pre>
*    intIterable.forEach((int x) -&gt; { // Do something with x });
 * </pre>
 * <p>
 * The same problem plagues, for example,
 * {@link java.util.PrimitiveIterator.OfInt#forEachRemaining(java.util.function.IntConsumer)}.
 *
 * <p>
 * <strong>Warning</strong>: Java will let you write &ldquo;colon&rdquo;
 * {@code for} statements with primitive-type loop variables; however, what is
 * (unfortunately) really happening is that at each iteration an unboxing (and,
 * in the case of {@code fastutil} type-specific data structures, a boxing) will
 * be performed. Watch out.
 *
 * @see Iterable
 */
public interface LongIterable extends Iterable<Long> {
	/**
	 * Returns a type-specific iterator.
	 *
	 * <p>
	 * Note that this specification strengthens the one given in
	 * {@link Iterable#iterator()}.
	 *
	 * @return a type-specific iterator.
	 * @see Iterable#iterator()
	 */
	@Override
	LongIterator iterator();

	/**
	 * Performs the given action for each element of this type-specific
	 * {@link java.lang.Iterable} until all elements have been processed or the
	 * action throws an exception.
	 * 
	 * @param action the action to be performed for each element.
	 * @see java.lang.Iterable#forEach(java.util.function.Consumer)
	 * @since 8.0.0
	 */
	@SuppressWarnings("overloads")
	default void forEach(final java.util.function.LongConsumer action) {
		Objects.requireNonNull(action);
		for (final LongIterator iterator = iterator(); iterator.hasNext();)
			action.accept(iterator.nextLong());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @deprecated Please use the corresponding type-specific method instead.
	 */
	@Deprecated
	@Override
	default void forEach(final Consumer<? super Long> action) {
		forEach((java.util.function.LongConsumer) action::accept);
	}
}