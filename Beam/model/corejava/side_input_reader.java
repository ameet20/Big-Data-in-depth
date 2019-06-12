package org.apache.beam.runners.core;

import org.apache.beam.sdk.transforms.windowing.BoundedWindow;
import org.apache.beam.sdk.values.PCollectionView;

/** An implementation of a {@link SideInputReader} that actually does not support side-inputs. */
public class UnsupportedSideInputReader implements SideInputReader {
  private final String transformName;

  public UnsupportedSideInputReader(String transformName) {
    this.transformName = transformName;
  }

  @Override
  public <T> T get(PCollectionView<T> view, BoundedWindow window) {
    throw new UnsupportedOperationException(
        String.format("%s does not support side inputs.", transformName));
  }

  @Override
  public <T> boolean contains(PCollectionView<T> view) {
    throw new UnsupportedOperationException(
        String.format("%s does not support side inputs.", transformName));
  }

  @Override
  public boolean isEmpty() {
    throw new UnsupportedOperationException(
        String.format("%s does not support side inputs.", transformName));
  }
}
