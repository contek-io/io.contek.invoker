package io.contek.invoker.commons.websocket;

import io.contek.invoker.commons.json.Json;
import io.vertx.core.http.WebSocket;

public record WebSocketSession(WebSocket webSocket) {

  public void send(AnyWebSocketMessage message) {
    if (message instanceof IWebSocketRawTextMessage) {
      IWebSocketRawTextMessage casted = (IWebSocketRawTextMessage) message;
      webSocket.writeFinalTextFrame(casted.getRawText());
      return;
    }

    webSocket.writeFinalTextFrame(Json.encode(message));
  }

  void close() {
    webSocket.close((short) 1000);
  }
}
