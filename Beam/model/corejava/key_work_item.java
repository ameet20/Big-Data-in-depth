package org.apache.beam.runners.core;

import org.apache.beam.runners.core.TimerInternals.TimerData;
import org.apache.beam.sdk.util.WindowedValue;

/**
 * Interface that contains all the timers and elements associated with a specific work item.
 *
 * @param <K> the key type
 * @param <ElemT> the element type
 */
public interface KeyedWorkItem<K, ElemT> {
  /** Returns the key. */
  K key();

  /** Returns an iterable containing the timers. */
  Iterable<TimerData> timersIterable();

  /** Returns an iterable containing the elements. */
  Iterable<WindowedValue<ElemT>> elementsIterable();
}
