package org.apache.beam.runners.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import org.apache.beam.runners.core.TimerInternals.TimerData;
import org.apache.beam.runners.core.TimerInternals.TimerDataCoder;
import org.apache.beam.sdk.coders.Coder;
import org.apache.beam.sdk.coders.CoderException;
import org.apache.beam.sdk.coders.IterableCoder;
import org.apache.beam.sdk.coders.StructuredCoder;
import org.apache.beam.sdk.transforms.windowing.BoundedWindow;
import org.apache.beam.sdk.util.WindowedValue;
import org.apache.beam.sdk.util.WindowedValue.FullWindowedValueCoder;
import org.apache.beam.vendor.guava.v20_0.com.google.common.collect.ImmutableList;

/** A {@link Coder} for {@link KeyedWorkItem KeyedWorkItems}. */
public class KeyedWorkItemCoder<K, ElemT> extends StructuredCoder<KeyedWorkItem<K, ElemT>> {
  /**
   * Create a new {@link KeyedWorkItemCoder} with the provided key coder, element coder, and window
   * coder.
   */
  public static <K, ElemT> KeyedWorkItemCoder<K, ElemT> of(
      Coder<K> keyCoder, Coder<ElemT> elemCoder, Coder<? extends BoundedWindow> windowCoder) {
    return new KeyedWorkItemCoder<>(keyCoder, elemCoder, windowCoder);
  }

  private final Coder<K> keyCoder;
  private final Coder<ElemT> elemCoder;
  private final Coder<? extends BoundedWindow> windowCoder;
  private final Coder<Iterable<TimerData>> timersCoder;
  private final Coder<Iterable<WindowedValue<ElemT>>> elemsCoder;

  private KeyedWorkItemCoder(
      Coder<K> keyCoder, Coder<ElemT> elemCoder, Coder<? extends BoundedWindow> windowCoder) {
    this.keyCoder = keyCoder;
    this.elemCoder = elemCoder;
    this.windowCoder = windowCoder;
    this.timersCoder = IterableCoder.of(TimerDataCoder.of(windowCoder));
    this.elemsCoder = IterableCoder.of(FullWindowedValueCoder.of(elemCoder, windowCoder));
  }

  public Coder<K> getKeyCoder() {
    return keyCoder;
  }

  public Coder<ElemT> getElementCoder() {
    return elemCoder;
  }

  @Override
  public void encode(KeyedWorkItem<K, ElemT> value, OutputStream outStream)
      throws CoderException, IOException {
    keyCoder.encode(value.key(), outStream);
    timersCoder.encode(value.timersIterable(), outStream);
    elemsCoder.encode(value.elementsIterable(), outStream);
  }

  @Override
  public KeyedWorkItem<K, ElemT> decode(InputStream inStream) throws CoderException, IOException {
    K key = keyCoder.decode(inStream);
    Iterable<TimerData> timers = timersCoder.decode(inStream);
    Iterable<WindowedValue<ElemT>> elems = elemsCoder.decode(inStream);
    return KeyedWorkItems.workItem(key, timers, elems);
  }

  @Override
  public List<? extends Coder<?>> getCoderArguments() {
    return ImmutableList.of(keyCoder, elemCoder, windowCoder);
  }

  @Override
  public void verifyDeterministic() throws Coder.NonDeterministicException {
    keyCoder.verifyDeterministic();
    timersCoder.verifyDeterministic();
    elemsCoder.verifyDeterministic();
  }

  /**
   * {@inheritDoc}.
   *
   * <p>{@link KeyedWorkItemCoder} is not consistent with equals as it can return a {@link
   * KeyedWorkItem} of a type different from the originally encoded type.
   */
  @Override
  public boolean consistentWithEquals() {
    return false;
  }
}
