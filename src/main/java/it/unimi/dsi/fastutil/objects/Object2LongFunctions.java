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
/* Object/Reference-only definitions (values) */
/* Primitive-type-only definitions (values) */
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
package it.unimi.dsi.fastutil.objects;

/**
 * A class providing static methods and objects that do useful things with
 * type-specific functions.
 *
 * @see it.unimi.dsi.fastutil.Function
 * @see java.util.Collections
 */
public class Object2LongFunctions {
	private Object2LongFunctions() {
	}

	/**
	 * An immutable class representing an empty type-specific function.
	 *
	 * <P>
	 * This class may be useful to implement your own in case you subclass a
	 * type-specific function.
	 */
	public static class EmptyFunction<K> extends AbstractObject2LongFunction<K>
			implements java.io.Serializable, Cloneable {
		private static final long serialVersionUID = -7046029254386353129L;

		protected EmptyFunction() {
		}

		public long getLong(final Object k) {
			return (0);
		}

		public boolean containsKey(final Object k) {
			return false;
		}

		public long defaultReturnValue() {
			return (0);
		}

		public void defaultReturnValue(final long defRetValue) {
			throw new UnsupportedOperationException();
		}

		public int size() {
			return 0;
		}

		public void clear() {
		}

		private Object readResolve() {
			return EMPTY_FUNCTION;
		}

		public Object clone() {
			return EMPTY_FUNCTION;
		}
	}

	/**
	 * An empty type-specific function (immutable). It is serializable and
	 * cloneable.
	 */
	@SuppressWarnings("rawtypes")
	public static final EmptyFunction EMPTY_FUNCTION = new EmptyFunction();

	/**
	 * An immutable class representing a type-specific singleton function.
	 *
	 * <P>
	 * This class may be useful to implement your own in case you subclass a
	 * type-specific function.
	 */

	public static class Singleton<K> extends AbstractObject2LongFunction<K> implements java.io.Serializable, Cloneable {

		private static final long serialVersionUID = -7046029254386353129L;

		protected final K key;
		protected final long value;

		protected Singleton(final K key, final long value) {
			this.key = key;
			this.value = value;
		}

		public boolean containsKey(final Object k) {
			return ((key) == null ? (k) == null : (key).equals(k));
		}

		public long getLong(final Object k) {
			if (((key) == null ? (k) == null : (key).equals(k)))
				return value;
			return defRetValue;
		}

		public int size() {
			return 1;
		}

		public Object clone() {
			return this;
		}
	}

	/**
	 * Returns a type-specific immutable function containing only the specified
	 * pair. The returned function is serializable and cloneable.
	 *
	 * <P>
	 * Note that albeit the returned function is immutable, its default return value
	 * may be changed.
	 *
	 * @param key   the only key of the returned function.
	 * @param value the only value of the returned function.
	 * @return a type-specific immutable function containing just the pair
	 *         <code>&lt;key,value></code>.
	 */

	public static <K> Object2LongFunction<K> singleton(final K key, long value) {
		return new Singleton<K>(key, value);
	}

	/**
	 * Returns a type-specific immutable function containing only the specified
	 * pair. The returned function is serializable and cloneable.
	 *
	 * <P>
	 * Note that albeit the returned function is immutable, its default return value
	 * may be changed.
	 *
	 * @param key   the only key of the returned function.
	 * @param value the only value of the returned function.
	 * @return a type-specific immutable function containing just the pair
	 *         <code>&lt;key,value></code>.
	 */

	public static <K> Object2LongFunction<K> singleton(final K key, final Long value) {
		return new Singleton<K>((key), ((value).longValue()));
	}

	/** A synchronized wrapper class for functions. */

	public static class SynchronizedFunction<K> extends AbstractObject2LongFunction<K> implements java.io.Serializable {

		private static final long serialVersionUID = -7046029254386353129L;

		protected final Object2LongFunction<K> function;
		protected final Object sync;

		protected SynchronizedFunction(final Object2LongFunction<K> f, final Object sync) {
			if (f == null)
				throw new NullPointerException();
			this.function = f;
			this.sync = sync;
		}

		protected SynchronizedFunction(final Object2LongFunction<K> f) {
			if (f == null)
				throw new NullPointerException();
			this.function = f;
			this.sync = this;
		}

		public int size() {
			synchronized (sync) {
				return function.size();
			}
		}

		public boolean containsKey(final Object k) {
			synchronized (sync) {
				return function.containsKey(k);
			}
		}

		public long defaultReturnValue() {
			synchronized (sync) {
				return function.defaultReturnValue();
			}
		}

		public void defaultReturnValue(final long defRetValue) {
			synchronized (sync) {
				function.defaultReturnValue(defRetValue);
			}
		}

		public long put(final K k, final long v) {
			synchronized (sync) {
				return function.put(k, v);
			}
		}

		public void clear() {
			synchronized (sync) {
				function.clear();
			}
		}

		public String toString() {
			synchronized (sync) {
				return function.toString();
			}
		}

		public Long put(final K k, final Long v) {
			synchronized (sync) {
				return function.put(k, v);
			}
		}

		public Long get(final Object k) {
			synchronized (sync) {
				return function.get(k);
			}
		}

		public Long remove(final Object k) {
			synchronized (sync) {
				return function.remove(k);
			}
		}

		public long removeLong(final Object k) {
			synchronized (sync) {
				return function.removeLong(k);
			}
		}

		public long getLong(final Object k) {
			synchronized (sync) {
				return function.getLong(k);
			}
		}
	}

	/**
	 * Returns a synchronized type-specific function backed by the given
	 * type-specific function.
	 *
	 * @param f the function to be wrapped in a synchronized function.
	 * @return a synchronized view of the specified function.
	 * @see java.util.Collections#synchronizedMap(java.util.Map)
	 */
	public static <K> Object2LongFunction<K> synchronize(final Object2LongFunction<K> f) {
		return new SynchronizedFunction<K>(f);
	}

	/**
	 * Returns a synchronized type-specific function backed by the given
	 * type-specific function, using an assigned object to synchronize.
	 *
	 * @param f    the function to be wrapped in a synchronized function.
	 * @param sync an object that will be used to synchronize the access to the
	 *             function.
	 * @return a synchronized view of the specified function.
	 * @see java.util.Collections#synchronizedMap(java.util.Map)
	 */
	public static <K> Object2LongFunction<K> synchronize(final Object2LongFunction<K> f, final Object sync) {
		return new SynchronizedFunction<K>(f, sync);
	}

	/** An unmodifiable wrapper class for functions. */
	public static class UnmodifiableFunction<K> extends AbstractObject2LongFunction<K> implements java.io.Serializable {
		private static final long serialVersionUID = -7046029254386353129L;
		protected final Object2LongFunction<K> function;

		protected UnmodifiableFunction(final Object2LongFunction<K> f) {
			if (f == null)
				throw new NullPointerException();
			this.function = f;
		}

		public int size() {
			return function.size();
		}

		public boolean containsKey(final Object k) {
			return function.containsKey(k);
		}

		public long defaultReturnValue() {
			return function.defaultReturnValue();
		}

		public void defaultReturnValue(final long defRetValue) {
			throw new UnsupportedOperationException();
		}

		public long put(final K k, final long v) {
			throw new UnsupportedOperationException();
		}

		public void clear() {
			throw new UnsupportedOperationException();
		}

		public String toString() {
			return function.toString();
		}

		public long removeLong(final Object k) {
			throw new UnsupportedOperationException();
		}

		public long getLong(final Object k) {
			return function.getLong(k);
		}
	}

	/**
	 * Returns an unmodifiable type-specific function backed by the given
	 * type-specific function.
	 *
	 * @param f the function to be wrapped in an unmodifiable function.
	 * @return an unmodifiable view of the specified function.
	 * @see java.util.Collections#unmodifiableMap(java.util.Map)
	 */
	public static <K> Object2LongFunction<K> unmodifiable(final Object2LongFunction<K> f) {
		return new UnmodifiableFunction<K>(f);
	}
}