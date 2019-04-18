package org.apache.spark.storage;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.spark.annotation.Private;
import org.apache.spark.shuffle.ShuffleWriteMetricsReporter;

/**
 * Intercepts write calls and tracks total time spent writing in order to update shuffle write
 * metrics. Not thread safe.
 */
@Private
public final class TimeTrackingOutputStream extends OutputStream {

  private final ShuffleWriteMetricsReporter writeMetrics;
  private final OutputStream outputStream;

  public TimeTrackingOutputStream(
      ShuffleWriteMetricsReporter writeMetrics, OutputStream outputStream) {
    this.writeMetrics = writeMetrics;
    this.outputStream = outputStream;
  }

  @Override
  public void write(int b) throws IOException {
    final long startTime = System.nanoTime();
    outputStream.write(b);
    writeMetrics.incWriteTime(System.nanoTime() - startTime);
  }

  @Override
  public void write(byte[] b) throws IOException {
    final long startTime = System.nanoTime();
    outputStream.write(b);
    writeMetrics.incWriteTime(System.nanoTime() - startTime);
  }

  @Override
  public void write(byte[] b, int off, int len) throws IOException {
    final long startTime = System.nanoTime();
    outputStream.write(b, off, len);
    writeMetrics.incWriteTime(System.nanoTime() - startTime);
  }

  @Override
  public void flush() throws IOException {
    final long startTime = System.nanoTime();
    outputStream.flush();
    writeMetrics.incWriteTime(System.nanoTime() - startTime);
  }

  @Override
  public void close() throws IOException {
    final long startTime = System.nanoTime();
    outputStream.close();
    writeMetrics.incWriteTime(System.nanoTime() - startTime);
  }
}
