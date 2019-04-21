package org.apache.spark.api.java.function;

import java.io.Serializable;
import java.util.Iterator;

import org.apache.spark.annotation.Evolving;
import org.apache.spark.annotation.Experimental;
import org.apache.spark.sql.streaming.GroupState;

/**
 * ::Experimental::
 * Base interface for a map function used in
 * {@link org.apache.spark.sql.KeyValueGroupedDataset#mapGroupsWithState(
 * MapGroupsWithStateFunction, org.apache.spark.sql.Encoder, org.apache.spark.sql.Encoder)}
 * @since 2.1.1
 */
@Experimental
@Evolving
public interface MapGroupsWithStateFunction<K, V, S, R> extends Serializable {
  R call(K key, Iterator<V> values, GroupState<S> state) throws Exception;
}
