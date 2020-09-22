package io.contek.invoker.commons.api.actor.http;

import okhttp3.*;

import javax.annotation.concurrent.ThreadSafe;
import java.io.IOException;
import java.net.InetAddress;

@ThreadSafe
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
