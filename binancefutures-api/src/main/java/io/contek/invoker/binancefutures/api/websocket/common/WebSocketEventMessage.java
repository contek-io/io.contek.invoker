package io.contek.invoker.binancefutures.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketEventMessage extends AnyWebSocketMessage {

  public String e;
  public Long E; // Event time
}
