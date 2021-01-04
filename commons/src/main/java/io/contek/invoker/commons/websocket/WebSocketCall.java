package io.contek.invoker.commons.websocket;

import io.contek.invoker.commons.actor.http.IHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketCall {

  private final String url;

  private WebSocketCall(String url) {
    this.url = url;
  }

  public static WebSocketCall fromUrl(String url) {
    return new WebSocketCall(url);
  }

  WebSocketSession submit(IHttpClient client, WebSocketListener listener) {
    WebSocket ws = client.submit(createRequest(), listener);
    return new WebSocketSession(ws);
  }

  private Request createRequest() {
    return new Request.Builder().url(url).build();
  }
}
