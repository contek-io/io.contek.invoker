package io.contek.invoker.commons.websocket;

public interface IWebSocketListener {

  void onMessage(AnyWebSocketMessage message, WebSocketSession session);

  void afterDisconnect();
}
