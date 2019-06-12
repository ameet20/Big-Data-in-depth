package org.apache.beam.runners.core;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.beam.sdk.transforms.windowing.BoundedWindow;
import org.apache.beam.sdk.transforms.windowing.FixedWindows;
import org.apache.beam.sdk.transforms.windowing.GlobalWindow;
import org.apache.beam.sdk.transforms.windowing.IntervalWindow;
import org.apache.beam.sdk.values.WindowingStrategy;
import org.hamcrest.Matchers;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** Tests for {@link LateDataUtils}. */
@RunWith(JUnit4.class)
public class LateDataUtilsTest {
  @Test
  public void beforeEndOfGlobalWindowSame() {
    FixedWindows windowFn = FixedWindows.of(Duration.standardMinutes(5));
    Duration allowedLateness = Duration.standardMinutes(2);
    WindowingStrategy<?, ?> strategy =
        WindowingStrategy.globalDefault()
            .withWindowFn(windowFn)
            .withAllowedLateness(allowedLateness);

    IntervalWindow window = windowFn.assignWindow(new Instant(10));
    assertThat(
        LateDataUtils.garbageCollectionTime(window, strategy),
        equalTo(window.maxTimestamp().plus(allowedLateness)));
  }

  @Test
  public void garbageCollectionTimeAfterEndOfGlobalWindow() {
    FixedWindows windowFn = FixedWindows.of(Duration.standardMinutes(5));
    WindowingStrategy<?, ?> strategy = WindowingStrategy.globalDefault().withWindowFn(windowFn);

    IntervalWindow window = windowFn.assignWindow(new Instant(BoundedWindow.TIMESTAMP_MAX_VALUE));
    assertThat(window.maxTimestamp(), equalTo(GlobalWindow.INSTANCE.maxTimestamp()));
    assertThat(
        LateDataUtils.garbageCollectionTime(window, strategy),
        equalTo(GlobalWindow.INSTANCE.maxTimestamp()));
  }

  @Test
  public void garbageCollectionTimeAfterEndOfGlobalWindowWithLateness() {
    FixedWindows windowFn = FixedWindows.of(Duration.standardMinutes(5));
    Duration allowedLateness = Duration.millis(Long.MAX_VALUE);
    WindowingStrategy<?, ?> strategy =
        WindowingStrategy.globalDefault()
            .withWindowFn(windowFn)
            .withAllowedLateness(allowedLateness);

    IntervalWindow window = windowFn.assignWindow(new Instant(-100));
    assertThat(
        window.maxTimestamp().plus(allowedLateness),
        Matchers.greaterThan(GlobalWindow.INSTANCE.maxTimestamp()));
    assertThat(
        LateDataUtils.garbageCollectionTime(window, strategy),
        equalTo(GlobalWindow.INSTANCE.maxTimestamp()));
  }
}
