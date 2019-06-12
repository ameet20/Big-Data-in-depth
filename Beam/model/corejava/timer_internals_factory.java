package org.apache.beam.runners.core;

import java.io.Serializable;
import org.apache.beam.sdk.annotations.Experimental;
import org.apache.beam.sdk.annotations.Experimental.Kind;

/**
 * A factory for providing {@link TimerInternals} for a particular key.
 *
 * <p>Because it will generally be embedded in a {@link org.apache.beam.sdk.transforms.DoFn DoFn},
 * albeit at execution time, it is marked {@link Serializable}.
 */
@Experimental(Kind.STATE)
public interface TimerInternalsFactory<K> {

  /** Returns {@link TimerInternals} for the provided key. */
  TimerInternals timerInternalsForKey(K key);
}
