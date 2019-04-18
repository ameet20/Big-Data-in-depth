package org.apache.spark.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import scala.reflect.ClassTag;

import org.apache.spark.annotation.Private;
import org.apache.spark.unsafe.Platform;

/**
 * Unfortunately, we need a serializer instance in order to construct a DiskBlockObjectWriter.
 * Our shuffle write path doesn't actually use this serializer (since we end up calling the
 * `write() OutputStream methods), but DiskBlockObjectWriter still calls some methods on it. To work
 * around this, we pass a dummy no-op serializer.
 */
@Private
public final class DummySerializerInstance extends SerializerInstance {

  public static final DummySerializerInstance INSTANCE = new DummySerializerInstance();

  private DummySerializerInstance() { }

  @Override
  public SerializationStream serializeStream(final OutputStream s) {
    return new SerializationStream() {
      @Override
      public void flush() {
        // Need to implement this because DiskObjectWriter uses it to flush the compression stream
        try {
          s.flush();
        } catch (IOException e) {
          Platform.throwException(e);
        }
      }

      @Override
      public <T> SerializationStream writeObject(T t, ClassTag<T> ev1) {
        throw new UnsupportedOperationException();
      }

      @Override
      public void close() {
        // Need to implement this because DiskObjectWriter uses it to close the compression stream
        try {
          s.close();
        } catch (IOException e) {
          Platform.throwException(e);
        }
      }
    };
  }

  @Override
  public <T> ByteBuffer serialize(T t, ClassTag<T> ev1) {
    throw new UnsupportedOperationException();
  }

  @Override
  public DeserializationStream deserializeStream(InputStream s) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> T deserialize(ByteBuffer bytes, ClassLoader loader, ClassTag<T> ev1) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> T deserialize(ByteBuffer bytes, ClassTag<T> ev1) {
    throw new UnsupportedOperationException();
  }
}
