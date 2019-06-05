package org.apache.beam.runners.core;

import org.apache.beam.sdk.annotations.Experimental;
import org.apache.beam.sdk.annotations.Experimental.Kind;
import org.apache.beam.sdk.state.State;

/**
 * Interface for accessing a {@link StateTag} in the current context.
 *
 * <p>For internal use only.
 */
@Experimental(Kind.STATE)
public interface StateAccessor<K> {
  /**
   * Access the storage for the given {@code address} in the current window.
   *
   * <p>Never accounts for merged windows. When windows are merged, any state accessed via this
   * method must be eagerly combined and written into the result window.
   */
  <StateT extends State> StateT access(StateTag<StateT> address);
}
