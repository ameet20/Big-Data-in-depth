package org.apache.beam.runners.core;

import javax.annotation.Nullable;
import org.apache.beam.sdk.transforms.windowing.BoundedWindow;
import org.apache.beam.sdk.values.PCollectionView;

/**
 * The interface to objects that provide side inputs. Particular implementations may read a side
 * input directly or use appropriate sorts of caching, etc.
 */
public interface SideInputReader {
  /**
   * Returns the value of the given {@link PCollectionView} for the given {@link BoundedWindow}.
   *
   * <p>It is valid for a side input to be {@code null}. It is <i>not</i> valid for this to return
   * {@code null} for any other reason.
   */
  @Nullable
  <T> T get(PCollectionView<T> view, BoundedWindow window);

  /** Returns true if the given {@link PCollectionView} is valid for this reader. */
  <T> boolean contains(PCollectionView<T> view);

  /** Returns true if there are no side inputs in this reader. */
  boolean isEmpty();
}
