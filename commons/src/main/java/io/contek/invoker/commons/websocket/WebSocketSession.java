package io.contek.invoker.commons.websocket;

import com.google.gson.Gson;
import okhttp3.WebSocket;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class WebSocketSession {

  private static final Gson gson = new Gson();

  private final WebSocket ws;

  WebSocketSession(WebSocket ws) {
    this.ws = ws;
  }

  public void send(AnyWebSocketMessage message) {
    if (message instanceof IWebSocketRawTextMessage casted) {
      ws.send(casted.getRawText());
      return;
    }

    ws.send(gson.toJson(message));
  }

  void close() {
    ws.close(1000, null);
  }
}
