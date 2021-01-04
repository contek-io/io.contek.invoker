package io.contek.invoker.commons.websocket;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface IWebSocketApi {

  void attach(IWebSocketComponent component);
}
