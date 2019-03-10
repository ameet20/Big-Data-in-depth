package org.apache.beam.sdk.testing;

/** Category tags for tests which validate that a Beam runner can handle keys up to a given size. */
public interface LargeKeys {
  /** Tests if a runner supports 10KB keys. */
  public interface Above10KB {}

  /** Tests if a runner supports 100KB keys. */
  public interface Above100KB extends Above10KB {}

  /** Tests if a runner supports 1MB keys. */
  public interface Above1MB extends Above100KB {}

  /** Tests if a runner supports 10MB keys. */
  public interface Above10MB extends Above1MB {}

  /** Tests if a runner supports 100MB keys. */
  public interface Above100MB extends Above10MB {}
}
