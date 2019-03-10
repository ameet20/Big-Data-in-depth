package org.apache.beam.sdk.schemas;

import java.io.Serializable;

/** A Factory interface for schema-related objects for a specific Java type. */
public interface Factory<T> extends Serializable {
  T create(Class<?> clazz, Schema schema);
}
