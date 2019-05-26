package org.apache.beam.runners.core;

import static org.apache.beam.vendor.guava.v20_0.com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.apache.beam.sdk.coders.Coder;
import org.apache.beam.sdk.coders.IterableCoder;
import org.apache.beam.sdk.coders.KvCoder;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.GroupByKey;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.util.WindowedValue;
import org.apache.beam.sdk.util.WindowedValue.WindowedValueCoder;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.WindowingStrategy;

public class GroupByKeyViaGroupByKeyOnly<K, V>
    extends PTransform<PCollection<KV<K, V>>, PCollection<KV<K, Iterable<V>>>> {

  private final GroupByKey<K, V> gbkTransform;

  public GroupByKeyViaGroupByKeyOnly(GroupByKey<K, V> originalTransform) {
    this.gbkTransform = originalTransform;
  }

  @Override
  public PCollection<KV<K, Iterable<V>>> expand(PCollection<KV<K, V>> input) {
    WindowingStrategy<?, ?> windowingStrategy = input.getWindowingStrategy();

    return input
        // Group by just the key.
        // Combiner lifting will not happen regardless of the disallowCombinerLifting value.
        // There will be no combiners right after the GroupByKeyOnly because of the two ParDos
        // introduced in here.
        .apply(new GroupByKeyOnly<>())

        // Sort each key's values by timestamp. GroupAlsoByWindow requires
        // its input to be sorted by timestamp.
        .apply(new SortValuesByTimestamp<>())

        // Group each key's values by window, merging windows as needed.
        .apply(new GroupAlsoByWindow<>(windowingStrategy))

        // And update the windowing strategy as appropriate.
        .setWindowingStrategyInternal(gbkTransform.updateWindowingStrategy(windowingStrategy));
  }

  /**
   * Runner-specific primitive that groups by key only, ignoring any window assignments. A runner
   * that uses {@link GroupByKeyViaGroupByKeyOnly} should have a primitive way to translate or
   * evaluate this class.
   */
  public static class GroupByKeyOnly<K, V>
      extends PTransform<PCollection<KV<K, V>>, PCollection<KV<K, Iterable<WindowedValue<V>>>>> {

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public PCollection<KV<K, Iterable<WindowedValue<V>>>> expand(PCollection<KV<K, V>> input) {
      return PCollection.createPrimitiveOutputInternal(
          input.getPipeline(),
          input.getWindowingStrategy(),
          input.isBounded(),
          (Coder) GroupByKey.getOutputKvCoder(input.getCoder()));
    }
  }

  /** Helper transform that sorts the values associated with each key by timestamp. */
  private static class SortValuesByTimestamp<K, V>
      extends PTransform<
          PCollection<KV<K, Iterable<WindowedValue<V>>>>,
          PCollection<KV<K, Iterable<WindowedValue<V>>>>> {
    @Override
    public PCollection<KV<K, Iterable<WindowedValue<V>>>> expand(
        PCollection<KV<K, Iterable<WindowedValue<V>>>> input) {
      return input
          .apply(
              ParDo.of(
                  new DoFn<KV<K, Iterable<WindowedValue<V>>>, KV<K, Iterable<WindowedValue<V>>>>() {
                    @ProcessElement
                    public void processElement(ProcessContext c) {
                      KV<K, Iterable<WindowedValue<V>>> kvs = c.element();
                      K key = kvs.getKey();
                      Iterable<WindowedValue<V>> unsortedValues = kvs.getValue();
                      List<WindowedValue<V>> sortedValues = new ArrayList<>();
                      for (WindowedValue<V> value : unsortedValues) {
                        sortedValues.add(value);
                      }
                      sortedValues.sort(Comparator.comparing(WindowedValue::getTimestamp));
                      c.output(KV.of(key, sortedValues));
                    }
                  }))
          .setCoder(input.getCoder());
    }
  }

  /**
   * Runner-specific primitive that takes a collection of timestamp-ordered values associated with
   * each key, groups the values by window, merges windows as needed, and for each window in each
   * key, outputs a collection of key/value-list pairs implicitly assigned to the window and with
   * the timestamp derived from that window.
   */
  public static class GroupAlsoByWindow<K, V>
      extends PTransform<
          PCollection<KV<K, Iterable<WindowedValue<V>>>>, PCollection<KV<K, Iterable<V>>>> {
    private final WindowingStrategy<?, ?> windowingStrategy;

    public GroupAlsoByWindow(WindowingStrategy<?, ?> windowingStrategy) {
      this.windowingStrategy = windowingStrategy;
    }

    public WindowingStrategy<?, ?> getWindowingStrategy() {
      return windowingStrategy;
    }

    private KvCoder<K, Iterable<WindowedValue<V>>> getKvCoder(
        Coder<KV<K, Iterable<WindowedValue<V>>>> inputCoder) {
      // Coder<KV<...>> --> KvCoder<...>
      checkArgument(
          inputCoder instanceof KvCoder,
          "%s requires a %s<...> but got %s",
          getClass().getSimpleName(),
          KvCoder.class.getSimpleName(),
          inputCoder);
      @SuppressWarnings("unchecked")
      KvCoder<K, Iterable<WindowedValue<V>>> kvCoder =
          (KvCoder<K, Iterable<WindowedValue<V>>>) inputCoder;
      return kvCoder;
    }

    public Coder<K> getKeyCoder(Coder<KV<K, Iterable<WindowedValue<V>>>> inputCoder) {
      return getKvCoder(inputCoder).getKeyCoder();
    }

    public Coder<V> getValueCoder(Coder<KV<K, Iterable<WindowedValue<V>>>> inputCoder) {
      // Coder<Iterable<...>> --> IterableCoder<...>
      Coder<Iterable<WindowedValue<V>>> iterableWindowedValueCoder =
          getKvCoder(inputCoder).getValueCoder();
      checkArgument(
          iterableWindowedValueCoder instanceof IterableCoder,
          "%s requires a %s<..., %s> but got a %s",
          getClass().getSimpleName(),
          KvCoder.class.getSimpleName(),
          IterableCoder.class.getSimpleName(),
          iterableWindowedValueCoder);
      IterableCoder<WindowedValue<V>> iterableCoder =
          (IterableCoder<WindowedValue<V>>) iterableWindowedValueCoder;

      // Coder<WindowedValue<...>> --> WindowedValueCoder<...>
      Coder<WindowedValue<V>> iterableElementCoder = iterableCoder.getElemCoder();
      checkArgument(
          iterableElementCoder instanceof WindowedValueCoder,
          "%s requires a %s<..., %s<%s>> but got a %s",
          getClass().getSimpleName(),
          KvCoder.class.getSimpleName(),
          IterableCoder.class.getSimpleName(),
          WindowedValueCoder.class.getSimpleName(),
          iterableElementCoder);
      WindowedValueCoder<V> windowedValueCoder = (WindowedValueCoder<V>) iterableElementCoder;

      return windowedValueCoder.getValueCoder();
    }

    @Override
    public PCollection<KV<K, Iterable<V>>> expand(
        PCollection<KV<K, Iterable<WindowedValue<V>>>> input) {
      @SuppressWarnings("unchecked")
      KvCoder<K, Iterable<WindowedValue<V>>> inputKvCoder =
          (KvCoder<K, Iterable<WindowedValue<V>>>) input.getCoder();

      Coder<K> keyCoder = inputKvCoder.getKeyCoder();
      Coder<Iterable<WindowedValue<V>>> inputValueCoder = inputKvCoder.getValueCoder();

      IterableCoder<WindowedValue<V>> inputIterableValueCoder =
          (IterableCoder<WindowedValue<V>>) inputValueCoder;
      Coder<WindowedValue<V>> inputIterableElementCoder = inputIterableValueCoder.getElemCoder();
      WindowedValueCoder<V> inputIterableWindowedValueCoder =
          (WindowedValueCoder<V>) inputIterableElementCoder;

      Coder<V> inputIterableElementValueCoder = inputIterableWindowedValueCoder.getValueCoder();
      Coder<Iterable<V>> outputValueCoder = IterableCoder.of(inputIterableElementValueCoder);
      Coder<KV<K, Iterable<V>>> outputKvCoder = KvCoder.of(keyCoder, outputValueCoder);

      return PCollection.createPrimitiveOutputInternal(
          input.getPipeline(), windowingStrategy, input.isBounded(), outputKvCoder);
    }
  }
}
