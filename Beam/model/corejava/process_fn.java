package org.apache.beam.runners.core;

import static org.apache.beam.vendor.guava.v20_0.com.google.common.base.Preconditions.checkArgument;

import java.util.Collection;
import java.util.Collections;
import org.apache.beam.runners.core.StateNamespaces.WindowNamespace;
import org.apache.beam.runners.core.TimerInternals.TimerData;
import org.apache.beam.sdk.state.TimeDomain;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.windowing.BoundedWindow;
import org.apache.beam.sdk.transforms.windowing.GlobalWindow;
import org.apache.beam.sdk.util.WindowedValue;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollectionView;
import org.apache.beam.vendor.guava.v20_0.com.google.common.collect.Iterables;
import org.joda.time.Instant;

/**
 * Runs a {@link SplittableParDoViaKeyedWorkItems.ProcessFn} by constructing the appropriate
 * contexts and passing them in.
 */
public class ProcessFnRunner<InputT, OutputT, RestrictionT>
    implements PushbackSideInputDoFnRunner<
        KeyedWorkItem<byte[], KV<InputT, RestrictionT>>, OutputT> {
  private final DoFnRunner<KeyedWorkItem<byte[], KV<InputT, RestrictionT>>, OutputT> underlying;
  private final Collection<PCollectionView<?>> views;
  private final ReadyCheckingSideInputReader sideInputReader;

  public ProcessFnRunner(
      DoFnRunner<KeyedWorkItem<byte[], KV<InputT, RestrictionT>>, OutputT> underlying,
      Collection<PCollectionView<?>> views,
      ReadyCheckingSideInputReader sideInputReader) {
    this.underlying = underlying;
    this.views = views;
    this.sideInputReader = sideInputReader;
  }

  @Override
  public DoFn<KeyedWorkItem<byte[], KV<InputT, RestrictionT>>, OutputT> getFn() {
    return underlying.getFn();
  }

  @Override
  public void startBundle() {
    underlying.startBundle();
  }

  @Override
  public Iterable<WindowedValue<KeyedWorkItem<byte[], KV<InputT, RestrictionT>>>>
      processElementInReadyWindows(
          WindowedValue<KeyedWorkItem<byte[], KV<InputT, RestrictionT>>> windowedKWI) {
    checkTrivialOuterWindows(windowedKWI);
    BoundedWindow window = getUnderlyingWindow(windowedKWI.getValue());
    if (!isReady(window)) {
      return Collections.singletonList(windowedKWI);
    }
    underlying.processElement(windowedKWI);
    return Collections.emptyList();
  }

  @Override
  public void finishBundle() {
    underlying.finishBundle();
  }

  @Override
  public void onTimer(
      String timerId, BoundedWindow window, Instant timestamp, TimeDomain timeDomain) {
    throw new UnsupportedOperationException("User timers unsupported in ProcessFn");
  }

  private static <T> void checkTrivialOuterWindows(
      WindowedValue<KeyedWorkItem<byte[], T>> windowedKWI) {
    // In practice it will be in 0 or 1 windows (ValueInEmptyWindows or ValueInGlobalWindow)
    Collection<? extends BoundedWindow> outerWindows = windowedKWI.getWindows();
    if (!outerWindows.isEmpty()) {
      checkArgument(
          outerWindows.size() == 1,
          "The KeyedWorkItem itself must not be in multiple windows, but was in: %s",
          outerWindows);
      BoundedWindow onlyWindow = Iterables.getOnlyElement(outerWindows);
      checkArgument(
          onlyWindow instanceof GlobalWindow,
          "KeyedWorkItem must be in the Global window, but was in: %s",
          onlyWindow);
    }
  }

  private static <T> BoundedWindow getUnderlyingWindow(KeyedWorkItem<byte[], T> kwi) {
    if (Iterables.isEmpty(kwi.elementsIterable())) {
      // ProcessFn sets only a single timer.
      TimerData timer = Iterables.getOnlyElement(kwi.timersIterable());
      return ((WindowNamespace) timer.getNamespace()).getWindow();
    } else {
      // KWI must have a single element in elementsIterable, because it follows a GBK by a
      // uniquely generated key.
      // Additionally, windows must be exploded before GBKIntoKeyedWorkItems, so there's also
      // only a single window.
      WindowedValue<T> value = Iterables.getOnlyElement(kwi.elementsIterable());
      return Iterables.getOnlyElement(value.getWindows());
    }
  }

  private boolean isReady(BoundedWindow mainInputWindow) {
    for (PCollectionView<?> view : views) {
      BoundedWindow sideInputWindow = view.getWindowMappingFn().getSideInputWindow(mainInputWindow);
      if (!sideInputReader.isReady(view, sideInputWindow)) {
        return false;
      }
    }
    return true;
  }
}
