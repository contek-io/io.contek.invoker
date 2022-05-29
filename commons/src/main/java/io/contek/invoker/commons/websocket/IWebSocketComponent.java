package io.contek.invoker.commons.websocket;

public interface IWebSocketComponent extends IWebSocketListener {

  void heartbeat(WebSocketSession session);

  ConsumerState getState();
}
