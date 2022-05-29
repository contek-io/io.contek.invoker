package io.contek.invoker.commons.actor.http;

import okhttp3.*;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;

public final class SimpleHttpClient implements IHttpClient {

  private final OkHttpClient delegate;
  private final InetAddress boundLocalAddress;

  public SimpleHttpClient(OkHttpClient delegate, InetAddress boundLocalAddress) {
    this.delegate = delegate;
    this.boundLocalAddress = boundLocalAddress;
  }

  @Override
  public Response submit(Request request) throws HttpConnectionException {
    try {
      return delegate.newCall(request).execute();
    } catch (InterruptedIOException e) {
      throw new HttpInterruptedException(e);
    } catch (IOException e) {
      throw new HttpConnectionException(e);
    }
  }

  @Override
  public WebSocket submit(Request request, WebSocketListener listener) throws AnyHttpException {
    return delegate.newWebSocket(request, listener);
  }

  @Override
  public InetAddress getBoundLocalAddress() {
    return boundLocalAddress;
  }
}
