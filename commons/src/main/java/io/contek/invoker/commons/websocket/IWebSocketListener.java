package io.contek.invoker.commons.websocket;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface IWebSocketListener {

  void onMessage(AnyWebSocketMessage message, WebSocketSession session);

  void afterDisconnect();
}
