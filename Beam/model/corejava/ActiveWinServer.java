package org.apache.beam.runners.core;

import java.util.Collection;
import java.util.Set;
import org.apache.beam.sdk.transforms.windowing.BoundedWindow;
import org.apache.beam.sdk.transforms.windowing.WindowFn;
import org.apache.beam.vendor.guava.v20_0.com.google.common.annotations.VisibleForTesting;

public interface ActiveWindowSet<W extends BoundedWindow> {
  /** Callback for {@link #merge}. */
  public interface MergeCallback<W extends BoundedWindow> {
    /**
     * Called when windows are about to be merged, but before any {@link #onMerge} callback has been
     * made.
     *
     * @param toBeMerged the windows about to be merged.
     * @param mergeResult the result window, either a member of {@code toBeMerged} or new.
     */
    void prefetchOnMerge(Collection<W> toBeMerged, W mergeResult) throws Exception;

    /**
     * Called when windows are about to be merged, after all {@link #prefetchOnMerge} calls have
     * been made, but before the active window set has been updated to reflect the merge.
     *
     * @param toBeMerged the windows about to be merged.
     * @param mergeResult the result window, either a member of {@code toBeMerged} or new.
     */
    void onMerge(Collection<W> toBeMerged, W mergeResult) throws Exception;
  }

  /**
   * Remove any remaining NEW windows since they were not promoted to being ACTIVE by {@link
   * #ensureWindowIsActive} and we don't need to record anything about them.
   */
  void cleanupTemporaryWindows();

  /** Save any state changes needed. */
  void persist();

  /** Return (a view of) the set of currently ACTIVE and NEW windows. */
  Set<W> getActiveAndNewWindows();

  /** Return {@code true} if {@code window} is ACTIVE. */
  boolean isActive(W window);

  /** Return {@code true} if {@code window} is ACTIVE or NEW. */
  boolean isActiveOrNew(W window);

  /**
   * Called when an incoming element indicates it is a member of {@code window}, but before we have
   * started processing that element. If {@code window} is not already known to be ACTIVE, then add
   * it as NEW.
   */
  void ensureWindowExists(W window);

  /**
   * Called when a NEW or ACTIVE window is now known to be ACTIVE. Ensure that if it is NEW then it
   * becomes ACTIVE (with itself as its only state address window).
   */
  void ensureWindowIsActive(W window);

  /**
   * If {@code window} is not already known to be ACTIVE then add it as ACTIVE. For testing only.
   */
  @VisibleForTesting
  void addActiveForTesting(W window);

  /** Remove {@code window} from the set. */
  void remove(W window);

  /**
   * Invoke {@link WindowFn#mergeWindows} on the {@code WindowFn} associated with this window set,
   * merging as many of the NEW and ACTIVE windows as possible. {@code mergeCallback} will be
   * invoked for each group of windows that are merged. After this all merge result windows will be
   * ACTIVE, and all windows which have been merged away will be neither ACTIVE nor NEW.
   */
  void merge(MergeCallback<W> mergeCallback) throws Exception;

  /**
   * Signal that all state in {@link #readStateAddresses} for {@code window} has been merged into
   * the {@link #writeStateAddress} for {@code window}.
   */
  void merged(W window);

  /**
   * Return the state address windows for ACTIVE {@code window} from which all state associated
   * should be read and merged.
   */
  Set<W> readStateAddresses(W window);

  /**
   * Return the state address window of ACTIVE {@code window} into which all new state should be
   * written. Always one of the results of {@link #readStateAddresses}.
   */
  W writeStateAddress(W window);

  /**
   * Return the state address window into which all new state should be written after ACTIVE windows
   * {@code toBeMerged} have been merged into {@code mergeResult}.
   */
  W mergedWriteStateAddress(Collection<W> toBeMerged, W mergeResult);
}
