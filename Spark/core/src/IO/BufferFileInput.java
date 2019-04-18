package org.apache.spark.io;

import org.apache.spark.storage.StorageUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

public final class NioBufferedFileInputStream extends InputStream {

  private static final int DEFAULT_BUFFER_SIZE_BYTES = 8192;

  private final ByteBuffer byteBuffer;

  private final FileChannel fileChannel;

  public NioBufferedFileInputStream(File file, int bufferSizeInBytes) throws IOException {
    byteBuffer = ByteBuffer.allocateDirect(bufferSizeInBytes);
    fileChannel = FileChannel.open(file.toPath(), StandardOpenOption.READ);
    byteBuffer.flip();
  }

  public NioBufferedFileInputStream(File file) throws IOException {
    this(file, DEFAULT_BUFFER_SIZE_BYTES);
  }
private boolean refill() throws IOException {
    if (!byteBuffer.hasRemaining()) {
      byteBuffer.clear();
      int nRead = 0;
      while (nRead == 0) {
        nRead = fileChannel.read(byteBuffer);
      }
      if (nRead < 0) {
        return false;
      }
      byteBuffer.flip();
    }
    return true;
  }

  @Override
  public synchronized int read() throws IOException {
    if (!refill()) {
      return -1;
    }
    return byteBuffer.get() & 0xFF;
  }
  @Override
  public synchronized int read(byte[] b, int offset, int len) throws IOException {
    if (offset < 0 || len < 0 || offset + len < 0 || offset + len > b.length) {
      throw new IndexOutOfBoundsException();
    }
    if (!refill()) {
      return -1;
    }
    len = Math.min(len, byteBuffer.remaining());
    byteBuffer.get(b, offset, len);
    return len;
  }

  @Override
  public synchronized int available() throws IOException {
    return byteBuffer.remaining();
  }

  @Override
  public synchronized long skip(long n) throws IOException {
    if (n <= 0L) {
      return 0L;
    }
    if (byteBuffer.remaining() >= n) {
      // The buffered content is enough to skip
      byteBuffer.position(byteBuffer.position() + (int) n);
      return n;
    }
    long skippedFromBuffer = byteBuffer.remaining();
    long toSkipFromFileChannel = n - skippedFromBuffer;
    // Discard everything we have read in the buffer.
    byteBuffer.position(0);
    byteBuffer.flip();
    return skippedFromBuffer + skipFromFileChannel(toSkipFromFileChannel);
  }

  private long skipFromFileChannel(long n) throws IOException {
    long currentFilePosition = fileChannel.position();
    long size = fileChannel.size();
    if (n > size - currentFilePosition) {
      fileChannel.position(size);
      return size - currentFilePosition;
    } else {
      fileChannel.position(currentFilePosition + n);
      return n;
    }
  }

  @Override
  public synchronized void close() throws IOException {
    fileChannel.close();
    StorageUtils.dispose(byteBuffer);
  }

  @SuppressWarnings("deprecation")
  @Override
  protected void finalize() throws IOException {
    close();
  }
}
