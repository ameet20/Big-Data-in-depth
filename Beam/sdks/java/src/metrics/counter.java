package org.apache.beam.sdk.metrics;

import org.apache.beam.sdk.annotations.Experimental;
import org.apache.beam.sdk.annotations.Experimental.Kind;

/** A metric that reports a single long value and can be incremented or decremented. */
@Experimental(Kind.METRICS)
public interface Counter extends Metric {

  /** Increment the counter. */
  void inc();

  /** Increment the counter by the given amount. */
  void inc(long n);

  /* Decrement the counter. */
  void dec();

  /* Decrement the counter by the given amount. */
  void dec(long n);
}
