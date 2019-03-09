package org.apache.beam.sdk.function;

import java.util.function.Function;

/**
 * A {@link Function} which can throw {@link Exception}s.
 *
 * <p>Used to expand the allowed set of method references to be used by Java 8 functional
 * interfaces.
 */
@FunctionalInterface
public interface ThrowingFunction<T1, T2> {
  T2 apply(T1 value) throws Exception;
}
