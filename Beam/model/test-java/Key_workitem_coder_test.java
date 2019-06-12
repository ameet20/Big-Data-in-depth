package org.apache.beam.runners.core;

import org.apache.beam.runners.core.TimerInternals.TimerData;
import org.apache.beam.sdk.coders.StringUtf8Coder;
import org.apache.beam.sdk.coders.VarIntCoder;
import org.apache.beam.sdk.state.TimeDomain;
import org.apache.beam.sdk.testing.CoderProperties;
import org.apache.beam.sdk.transforms.windowing.GlobalWindow;
import org.apache.beam.sdk.util.WindowedValue;
import org.apache.beam.vendor.guava.v20_0.com.google.common.collect.ImmutableList;
import org.joda.time.Instant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** Tests for {@link KeyedWorkItems}. */
@RunWith(JUnit4.class)
public class KeyedWorkItemCoderTest {
  @Test
  public void testCoderProperties() throws Exception {
    CoderProperties.coderSerializable(
        KeyedWorkItemCoder.of(StringUtf8Coder.of(), VarIntCoder.of(), GlobalWindow.Coder.INSTANCE));
  }

  @Test
  public void testEncodeDecodeEqual() throws Exception {
    Iterable<TimerData> timers =
        ImmutableList.of(
            TimerData.of(StateNamespaces.global(), new Instant(500L), TimeDomain.EVENT_TIME));
    Iterable<WindowedValue<Integer>> elements =
        ImmutableList.of(
            WindowedValue.valueInGlobalWindow(1),
            WindowedValue.valueInGlobalWindow(4),
            WindowedValue.valueInGlobalWindow(8));

    KeyedWorkItemCoder<String, Integer> coder =
        KeyedWorkItemCoder.of(StringUtf8Coder.of(), VarIntCoder.of(), GlobalWindow.Coder.INSTANCE);

    CoderProperties.coderDecodeEncodeEqual(coder, KeyedWorkItems.workItem("foo", timers, elements));
    CoderProperties.coderDecodeEncodeEqual(coder, KeyedWorkItems.elementsWorkItem("foo", elements));
    CoderProperties.coderDecodeEncodeEqual(coder, KeyedWorkItems.timersWorkItem("foo", timers));
  }

  @Test
  public void testCoderIsSerializableWithWellKnownCoderType() throws Exception {
    CoderProperties.coderSerializable(
        KeyedWorkItemCoder.of(
            GlobalWindow.Coder.INSTANCE, GlobalWindow.Coder.INSTANCE, GlobalWindow.Coder.INSTANCE));
  }
}
