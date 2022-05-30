package io.contek.invoker.commons.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.vertx.core.buffer.Buffer;

import java.io.DataOutput;
import java.io.IOException;
import java.io.OutputStream;

public class BufferOutputStream extends OutputStream implements DataOutput {

  private final ByteBufOutputStream outputStream;

  public BufferOutputStream(Buffer buffer) {
    this.outputStream = new ByteBufOutputStream(buffer.getByteBuf());
  }

  public static OutputStream nullOutputStream() {
    return OutputStream.nullOutputStream();
  }

  public int writtenBytes() {
    return outputStream.writtenBytes();
  }

  @Override
  public void write(byte[] b, int off, int len) throws IOException {
    outputStream.write(b, off, len);
  }

  @Override
  public void write(byte[] b) throws IOException {
    outputStream.write(b);
  }

  @Override
  public void write(int b) throws IOException {
    outputStream.write(b);
  }

  @Override
  public void writeBoolean(boolean v) throws IOException {
    outputStream.writeBoolean(v);
  }

  @Override
  public void writeByte(int v) throws IOException {
    outputStream.writeByte(v);
  }

  @Override
  public void writeBytes(String s) throws IOException {
    outputStream.writeBytes(s);
  }

  @Override
  public void writeChar(int v) throws IOException {
    outputStream.writeChar(v);
  }

  @Override
  public void writeChars(String s) throws IOException {
    outputStream.writeChars(s);
  }

  @Override
  public void writeDouble(double v) throws IOException {
    outputStream.writeDouble(v);
  }

  @Override
  public void writeFloat(float v) throws IOException {
    outputStream.writeFloat(v);
  }

  @Override
  public void writeInt(int v) throws IOException {
    outputStream.writeInt(v);
  }

  @Override
  public void writeLong(long v) throws IOException {
    outputStream.writeLong(v);
  }

  @Override
  public void writeShort(int v) throws IOException {
    outputStream.writeShort(v);
  }

  @Override
  public void writeUTF(String s) throws IOException {
    outputStream.writeUTF(s);
  }

  public ByteBuf buffer() {
    return outputStream.buffer();
  }

  @Override
  public void close() throws IOException {
    outputStream.close();
  }

  @Override
  public void flush() throws IOException {
    outputStream.flush();
  }
}
