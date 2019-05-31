package org.apache.beam.runners.core;

import java.util.Collection;
import org.apache.beam.sdk.transforms.windowing.BoundedWindow;
import org.apache.beam.sdk.transforms.windowing.PaneInfo;
import org.apache.beam.sdk.values.TupleTag;
import org.joda.time.Instant;

/**
 * An object that can output a value with all of its windowing information to the main output or any
 * tagged output.
 */
public interface OutputWindowedValue<OutputT> {
  /** Outputs a value with windowing information to the main output. */
  void outputWindowedValue(
      OutputT output,
      Instant timestamp,
      Collection<? extends BoundedWindow> windows,
      PaneInfo pane);

  /** Outputs a value with windowing information to a tagged output. */
  <AdditionalOutputT> void outputWindowedValue(
      TupleTag<AdditionalOutputT> tag,
      AdditionalOutputT output,
      Instant timestamp,
      Collection<? extends BoundedWindow> windows,
      PaneInfo pane);
}
