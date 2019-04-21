package org.apache.spark.api.java.function;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.spark.annotation.Evolving;
import org.apache.spark.annotation.Experimental;
import org.apache.spark.sql.streaming.GroupState;

/**
 * ::Experimental::
 * Base interface for a map function used in
 * {@code org.apache.spark.sql.KeyValueGroupedDataset.flatMapGroupsWithState(
 * FlatMapGroupsWithStateFunction, org.apache.spark.sql.streaming.OutputMode,
 * org.apache.spark.sql.Encoder, org.apache.spark.sql.Encoder)}
 * @since 2.1.1
 */
@Experimental
@Evolving
public interface FlatMapGroupsWithStateFunction<K, V, S, R> extends Serializable {
  Iterator<R> call(K key, Iterator<V> values, GroupState<S> state) throws Exception;
}
