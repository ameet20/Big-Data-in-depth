package org.apache.beam.runners.core;

import java.util.Collection;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.windowing.BoundedWindow;
import org.apache.beam.sdk.transforms.windowing.PaneInfo;
import org.apache.beam.sdk.values.PCollectionView;
import org.apache.beam.sdk.values.TupleTag;
import org.joda.time.Instant;

/**
 * Interface that may be required by some (internal) {@link DoFn}s to implement windowing. It should
 * not be necessary for general user code to interact with this at all.
 *
 * <p>This interface should be provided by runner implementors to support windowing on their runner.
 *
 * @param <InputT> input type
 * @param <OutputT> output type
 */
public interface WindowingInternals<InputT, OutputT> {

  /**
   * Unsupported state internals. The key type is unknown. It is up to the user to use the correct
   * type of key.
   */
  StateInternals stateInternals();

  /** Output the value at the specified timestamp in the listed windows. */
  void outputWindowedValue(
      OutputT output,
      Instant timestamp,
      Collection<? extends BoundedWindow> windows,
      PaneInfo pane);

  /** Output the value to a tagged output at the specified timestamp in the listed windows. */
  <AdditionalOutputT> void outputWindowedValue(
      TupleTag<AdditionalOutputT> tag,
      AdditionalOutputT output,
      Instant timestamp,
      Collection<? extends BoundedWindow> windows,
      PaneInfo pane);

  /**
   * Return the timer manager provided by the underlying system, or null if Timers need to be
   * emulated.
   */
  TimerInternals timerInternals();

  /** Access the windows the element is being processed in without "exploding" it. */
  Collection<? extends BoundedWindow> windows();

  /** Access the pane of the current window(s). */
  PaneInfo pane();

  /** Return the value of the side input for a particular side input window. */
  <T> T sideInput(PCollectionView<T> view, BoundedWindow sideInputWindow);
}
