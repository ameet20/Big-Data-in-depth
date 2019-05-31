package org.apache.beam.runners.core;

import java.util.Collection;
import java.util.Set;
import org.apache.beam.sdk.transforms.windowing.BoundedWindow;
import org.apache.beam.sdk.transforms.windowing.WindowFn;
import org.apache.beam.vendor.guava.v20_0.com.google.common.annotations.VisibleForTesting;
import org.apache.beam.vendor.guava.v20_0.com.google.common.collect.ImmutableSet;

/**
 * Implementation of {@link ActiveWindowSet} used with {@link WindowFn WindowFns} that don't support
 * merging.
 *
 * @param <W> the types of windows being managed
 */
public class NonMergingActiveWindowSet<W extends BoundedWindow> implements ActiveWindowSet<W> {
  @Override
  public void cleanupTemporaryWindows() {}

  @Override
  public void persist() {}

  @Override
  public Set<W> getActiveAndNewWindows() {
    // Only supported when merging.
    throw new java.lang.UnsupportedOperationException();
  }

  @Override
  public boolean isActive(W window) {
    // Windows should never disappear, since we don't support merging.
    return true;
  }

  @Override
  public boolean isActiveOrNew(W window) {
    return true;
  }

  @Override
  public void ensureWindowExists(W window) {}

  @Override
  public void ensureWindowIsActive(W window) {}

  @Override
  @VisibleForTesting
  public void addActiveForTesting(W window) {}

  @Override
  public void remove(W window) {}

  @Override
  public void merge(MergeCallback<W> mergeCallback) throws Exception {}

  @Override
  public void merged(W window) {}

  @Override
  public Set<W> readStateAddresses(W window) {
    return ImmutableSet.of(window);
  }

  @Override
  public W writeStateAddress(W window) {
    return window;
  }

  @Override
  public W mergedWriteStateAddress(Collection<W> toBeMerged, W mergeResult) {
    return mergeResult;
  }
}
