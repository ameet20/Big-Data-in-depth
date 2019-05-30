package org.apache.beam.runners.core;

import org.apache.beam.sdk.util.common.ElementByteSizeObserver;

/**
 * An interface for things that allow observing the size in bytes of encoded values of type {@code
 * T}.
 *
 * @param <T> the type of the values being observed
 */
public interface ElementByteSizeObservable<T> {
  /**
   * Returns whether {@link #registerByteSizeObserver} is cheap enough to call for every element,
   * that is, if this {@code ElementByteSizeObservable} can calculate the byte size of the element
   * to be coded in roughly constant time (or lazily).
   */
  boolean isRegisterByteSizeObserverCheap(T value);

  /**
   * Notifies the {@code ElementByteSizeObserver} about the byte size of the encoded value using
   * this {@code ElementByteSizeObservable}.
   */
  void registerByteSizeObserver(T value, ElementByteSizeObserver observer) throws Exception;
}
