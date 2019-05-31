package org.apache.beam.runners.core;

import java.util.Collections;
import java.util.Set;
import org.apache.beam.sdk.transforms.windowing.BoundedWindow;
import org.apache.beam.sdk.values.PCollectionView;
import org.apache.beam.vendor.guava.v20_0.com.google.common.collect.Sets;

/**
 * A {@link SideInputReader} representing a well-defined set of views, but not storing any values
 * for them. Used to check if a side input is present when the data itself comes from elsewhere.
 */
public class NullSideInputReader implements SideInputReader {

  private Set<PCollectionView<?>> views;

  public static NullSideInputReader empty() {
    return new NullSideInputReader(Collections.emptySet());
  }

  public static NullSideInputReader of(Iterable<? extends PCollectionView<?>> views) {
    return new NullSideInputReader(views);
  }

  private NullSideInputReader(Iterable<? extends PCollectionView<?>> views) {
    this.views = Sets.newHashSet(views);
  }

  @Override
  public <T> T get(PCollectionView<T> view, BoundedWindow window) {
    throw new IllegalArgumentException("cannot call NullSideInputReader.get()");
  }

  @Override
  public boolean isEmpty() {
    return views.isEmpty();
  }

  @Override
  public <T> boolean contains(PCollectionView<T> view) {
    return views.contains(view);
  }
}
