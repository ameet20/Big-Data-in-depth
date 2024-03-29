package org.apache.beam.runners.core;

import org.apache.beam.sdk.annotations.Experimental;
import org.apache.beam.sdk.annotations.Experimental.Kind;
import org.apache.beam.sdk.state.State;
import org.apache.beam.sdk.state.StateContext;
import org.apache.beam.sdk.state.StateContexts;
import org.apache.beam.sdk.transforms.GroupByKey;

/**
 * {@code StateInternals} describes the functionality a runner needs to provide for the State API to
 * be supported.
 *
 * <p>The SDK will only use this after elements have been partitioned by key. For instance, after a
 * {@link GroupByKey} operation. The runner implementation must ensure that any writes using {@link
 * StateInternals} are implicitly scoped to the key being processed and the specific step accessing
 * state.
 *
 * <p>The runner implementation must also ensure that any writes to the associated state objects are
 * persisted together with the completion status of the processing that produced these writes.
 *
 * <p>This is a low-level API intended for use by the Beam SDK. It should not be used directly, and
 * is highly likely to change.
 */
@Experimental(Kind.STATE)
public interface StateInternals {

  /** The key for this {@link StateInternals}. */
  Object getKey();

  /** Return the state associated with {@code address} in the specified {@code namespace}. */
  default <T extends State> T state(StateNamespace namespace, StateTag<T> address) {
    return state(namespace, address, StateContexts.nullContext());
  }

  /**
   * Return the state associated with {@code address} in the specified {@code namespace} with the
   * {@link StateContext}.
   */
  <T extends State> T state(StateNamespace namespace, StateTag<T> address, StateContext<?> c);
}
