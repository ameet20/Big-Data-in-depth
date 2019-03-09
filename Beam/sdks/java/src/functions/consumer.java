package org.apache.beam.sdk.function;

import java.util.function.Consumer;

/**
 * A {@link Consumer} which can throw {@link Exception}s.
 *
 * <p>Used to expand the allowed set of method references to be used by Java 8 functional
 * interfaces.
 */
@FunctionalInterface
public interface ThrowingConsumer<ExceptionT extends Exception, T> {
  void accept(T t) throws ExceptionT;
}
