package org.apache.beam.runners.core;

import java.io.IOException;
import java.util.Objects;

/** A simple {@link StateNamespace} used for testing. */
public class StateNamespaceForTest implements StateNamespace {
  private String key;

  public StateNamespaceForTest(String key) {
    this.key = key;
  }

  @Override
  public String stringKey() {
    return key;
  }

  @Override
  public Object getCacheKey() {
    return key;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof StateNamespaceForTest)) {
      return false;
    }

    return Objects.equals(this.key, ((StateNamespaceForTest) obj).key);
  }

  @Override
  public int hashCode() {
    return key.hashCode();
  }

  @Override
  public void appendTo(Appendable sb) throws IOException {
    sb.append(key);
  }
}
