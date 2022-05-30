package io.contek.invoker.commons.buffer;

import io.netty.buffer.ByteBufInputStream;
import io.vertx.core.buffer.Buffer;

import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BufferInputStream extends InputStream implements DataInput {

  private final ByteBufInputStream inputStream;

  public BufferInputStream(Buffer buffer) {
    this.inputStream = new ByteBufInputStream(buffer.getByteBuf());
  }

  public BufferInputStream(Buffer buffer, boolean releaseOnClose) {
    this.inputStream = new ByteBufInputStream(buffer.getByteBuf(), releaseOnClose);
  }

  public BufferInputStream(Buffer buffer, int length) {
    this.inputStream = new ByteBufInputStream(buffer.getByteBuf(), length);
  }

  public BufferInputStream(Buffer buffer, int length, boolean releaseOnClose) {
    this.inputStream = new ByteBufInputStream(buffer.getByteBuf(), length, releaseOnClose);
  }

  public static InputStream nullInputStream() {
    return InputStream.nullInputStream();
  }

  public int readBytes() {
    return inputStream.readBytes();
  }

  @Override
  public void close() throws IOException {
    inputStream.close();
  }

  @Override
  public int available() throws IOException {
    return inputStream.available();
  }

  @Override
  public void mark(int readlimit) {
    inputStream.mark(readlimit);
  }

  @Override
  public boolean markSupported() {
    return inputStream.markSupported();
  }

  @Override
  public int read() throws IOException {
    return inputStream.read();
  }

  @Override
  public int read(byte[] b, int off, int len) throws IOException {
    return inputStream.read(b, off, len);
  }

  @Override
  public void reset() throws IOException {
    inputStream.reset();
  }

  @Override
  public long skip(long n) throws IOException {
    return inputStream.skip(n);
  }

  @Override
  public boolean readBoolean() throws IOException {
    return inputStream.readBoolean();
  }

  @Override
  public byte readByte() throws IOException {
    return inputStream.readByte();
  }

  @Override
  public char readChar() throws IOException {
    return inputStream.readChar();
  }

  @Override
  public double readDouble() throws IOException {
    return inputStream.readDouble();
  }

  @Override
  public float readFloat() throws IOException {
    return inputStream.readFloat();
  }

  @Override
  public void readFully(byte[] b) throws IOException {
    inputStream.readFully(b);
  }

  @Override
  public void readFully(byte[] b, int off, int len) throws IOException {
    inputStream.readFully(b, off, len);
  }

  @Override
  public int readInt() throws IOException {
    return inputStream.readInt();
  }

  @Override
  public String readLine() throws IOException {
    return inputStream.readLine();
  }

  @Override
  public long readLong() throws IOException {
    return inputStream.readLong();
  }

  @Override
  public short readShort() throws IOException {
    return inputStream.readShort();
  }

  @Override
  public String readUTF() throws IOException {
    return inputStream.readUTF();
  }

  @Override
  public int readUnsignedByte() throws IOException {
    return inputStream.readUnsignedByte();
  }

  @Override
  public int readUnsignedShort() throws IOException {
    return inputStream.readUnsignedShort();
  }

  @Override
  public int skipBytes(int n) throws IOException {
    return inputStream.skipBytes(n);
  }

  @Override
  public int read(byte[] b) throws IOException {
    return inputStream.read(b);
  }

  @Override
  public byte[] readAllBytes() throws IOException {
    return inputStream.readAllBytes();
  }

  @Override
  public byte[] readNBytes(int len) throws IOException {
    return inputStream.readNBytes(len);
  }

  @Override
  public int readNBytes(byte[] b, int off, int len) throws IOException {
    return inputStream.readNBytes(b, off, len);
  }

  @Override
  public void skipNBytes(long n) throws IOException {
    inputStream.skipNBytes(n);
  }

  @Override
  public long transferTo(OutputStream out) throws IOException {
    return inputStream.transferTo(out);
  }
}
