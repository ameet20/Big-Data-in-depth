package org.apache.beam.runners.core;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.beam.sdk.state.State;
import org.apache.beam.sdk.state.WatermarkHoldState;
import org.joda.time.Instant;

/** Simulates state like {@link InMemoryStateInternals} and provides some extra helper methods. */
public class TestInMemoryStateInternals<K> extends InMemoryStateInternals<K> {
  public TestInMemoryStateInternals(K key) {
    super(key);
  }

  public Set<StateTag> getTagsInUse(StateNamespace namespace) {
    Set<StateTag> inUse = new HashSet<>();
    for (Map.Entry<StateTag, State> entry : inMemoryState.getTagsInUse(namespace).entrySet()) {
      if (!isEmptyForTesting(entry.getValue())) {
        inUse.add(entry.getKey());
      }
    }
    return inUse;
  }

  public Set<StateNamespace> getNamespacesInUse() {
    return inMemoryState.getNamespacesInUse();
  }

  /** Return the earliest output watermark hold in state, or null if none. */
  public Instant earliestWatermarkHold() {
    Instant minimum = null;
    for (State storage : inMemoryState.values()) {
      if (storage instanceof WatermarkHoldState) {
        Instant hold = ((WatermarkHoldState) storage).read();
        if (minimum == null || (hold != null && hold.isBefore(minimum))) {
          minimum = hold;
        }
      }
    }
    return minimum;
  }
}
