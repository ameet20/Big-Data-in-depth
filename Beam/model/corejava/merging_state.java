package org.apache.beam.runners.core;

import java.util.Map;
import org.apache.beam.sdk.annotations.Experimental;
import org.apache.beam.sdk.annotations.Experimental.Kind;
import org.apache.beam.sdk.state.State;
import org.apache.beam.sdk.transforms.windowing.BoundedWindow;

/**
 * Interface for accessing persistent state while windows are merging.
 *
 * <p>For internal use only.
 */
@Experimental(Kind.STATE)
public interface MergingStateAccessor<K, W extends BoundedWindow> extends StateAccessor<K> {
  /**
   * Analogous to {@link #access}, but returned as a map from each window which is about to be
   * merged to the corresponding state. Only includes windows which are known to have state.
   */
  <StateT extends State> Map<W, StateT> accessInEachMergingWindow(StateTag<StateT> address);
}
