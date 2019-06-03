package org.apache.beam.runners.core;

import org.apache.beam.sdk.transforms.windowing.BoundedWindow;
import org.apache.beam.sdk.values.PCollectionView;

/**
 * A {@link SideInputReader} that allows callers to check to see if a {@link PCollectionView} has
 * had its contents set in a window.
 */
public interface ReadyCheckingSideInputReader extends SideInputReader {
  /** Returns true if the {@link PCollectionView} is ready in the provided {@link BoundedWindow}. */
  boolean isReady(PCollectionView<?> view, BoundedWindow window);
}
