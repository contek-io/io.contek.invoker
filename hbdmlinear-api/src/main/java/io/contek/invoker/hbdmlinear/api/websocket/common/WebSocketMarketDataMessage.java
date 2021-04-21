package io.contek.invoker.hbdmlinear.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketMarketDataMessage<T> extends AnyWebSocketMessage {

  public String ch;
  public Long ts;
  public T tick;
}
