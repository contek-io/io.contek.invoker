package io.contek.invoker.commons.api.websocket;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface IWebSocketComponent extends IWebSocketListener {

  void heartbeat(WebSocketSession session);

  ConsumerState getState();
}
