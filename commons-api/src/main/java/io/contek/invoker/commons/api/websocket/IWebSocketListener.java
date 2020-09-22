package io.contek.invoker.commons.api.websocket;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface IWebSocketListener {

  void onMessage(AnyWebSocketMessage message);

  void afterDisconnect();
}
