package io.contek.invoker.commons.websocket;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface IWebSocketMessageParser {

  AnyWebSocketMessage parse(String text);
}
