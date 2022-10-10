package io.contek.invoker.binancedelivery.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketEventData extends AnyWebSocketMessage {

  public String e;
  public Long E; // Event time
}
