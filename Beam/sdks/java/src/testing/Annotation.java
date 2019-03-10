package org.apache.beam.sdk.testing;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import org.apache.beam.vendor.guava.v20_0.com.google.common.base.Predicate;
import org.apache.beam.vendor.guava.v20_0.com.google.common.collect.FluentIterable;
import org.junit.experimental.categories.Category;

/** A utility class for querying annotations. */
class Annotations {

  /** Annotation predicates. */
  static class Predicates {

    static Predicate<Annotation> isAnnotationOfType(final Class<? extends Annotation> clazz) {
      return annotation ->
          annotation.annotationType() != null && annotation.annotationType().equals(clazz);
    }

    static Predicate<Annotation> isCategoryOf(final Class<?> value, final boolean allowDerived) {
      return category ->
          FluentIterable.from(Arrays.asList(((Category) category).value()))
              .anyMatch(
                  aClass -> allowDerived ? value.isAssignableFrom(aClass) : value.equals(aClass));
    }
  }
}
