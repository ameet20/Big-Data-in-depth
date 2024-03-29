package org.apache.beam.runners.core;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import org.apache.beam.runners.core.LateDataDroppingDoFnRunner.LateDataFilter;
import org.apache.beam.runners.core.metrics.MetricsContainerImpl;
import org.apache.beam.sdk.metrics.MetricName;
import org.apache.beam.sdk.metrics.MetricsEnvironment;
import org.apache.beam.sdk.transforms.windowing.FixedWindows;
import org.apache.beam.sdk.transforms.windowing.PaneInfo;
import org.apache.beam.sdk.util.WindowedValue;
import org.apache.beam.sdk.values.WindowingStrategy;
import org.apache.beam.vendor.guava.v20_0.com.google.common.collect.ImmutableList;
import org.apache.beam.vendor.guava.v20_0.com.google.common.collect.Iterables;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/** Unit tests for {@link LateDataDroppingDoFnRunner}. */
@RunWith(JUnit4.class)
public class LateDataDroppingDoFnRunnerTest {
  private static final FixedWindows WINDOW_FN = FixedWindows.of(Duration.millis(10));

  @Mock private TimerInternals mockTimerInternals;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testLateDataFilter() throws Exception {
    MetricsContainerImpl container = new MetricsContainerImpl("any");
    MetricsEnvironment.setCurrentContainer(container);
    when(mockTimerInternals.currentInputWatermarkTime()).thenReturn(new Instant(15L));

    LateDataFilter lateDataFilter =
        new LateDataFilter(WindowingStrategy.of(WINDOW_FN), mockTimerInternals);

    Iterable<WindowedValue<Integer>> actual =
        lateDataFilter.filter(
            "a",
            ImmutableList.of(
                createDatum(13, 13L),
                createDatum(5, 5L), // late element, earlier than 4L.
                createDatum(16, 16L),
                createDatum(18, 18L)));

    Iterable<WindowedValue<Integer>> expected =
        ImmutableList.of(createDatum(13, 13L), createDatum(16, 16L), createDatum(18, 18L));
    assertThat(expected, containsInAnyOrder(Iterables.toArray(actual, WindowedValue.class)));
    long droppedValues =
        container
            .getCounter(
                MetricName.named(
                    LateDataDroppingDoFnRunner.class,
                    LateDataDroppingDoFnRunner.DROPPED_DUE_TO_LATENESS))
            .getCumulative();
    assertEquals(1, droppedValues);
    // Ensure that reiterating returns the same results and doesn't increment the counter again.
    assertThat(expected, containsInAnyOrder(Iterables.toArray(actual, WindowedValue.class)));
    droppedValues =
        container
            .getCounter(
                MetricName.named(
                    LateDataDroppingDoFnRunner.class,
                    LateDataDroppingDoFnRunner.DROPPED_DUE_TO_LATENESS))
            .getCumulative();
    assertEquals(1, droppedValues);
  }

  private <T> WindowedValue<T> createDatum(T element, long timestampMillis) {
    Instant timestamp = new Instant(timestampMillis);
    return WindowedValue.of(
        element, timestamp, Arrays.asList(WINDOW_FN.assignWindow(timestamp)), PaneInfo.NO_FIRING);
  }
}
