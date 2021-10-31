package io.contek.invoker.commons.websocket;

import com.google.gson.Gson;
import io.contek.invoker.commons.GsonFactory;
import okhttp3.WebSocket;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class WebSocketSession {

  private static final Gson gson = GsonFactory.buildGson();

  private final WebSocket ws;

  WebSocketSession(WebSocket ws) {
    this.ws = ws;
  }

  public void send(AnyWebSocketMessage message) {
    ws.send(gson.toJson(message));
  }

  void close() {
    ws.close(1000, null);
  }
}
