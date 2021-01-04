package io.contek.invoker.binancedelivery.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketStreamMessage<T> extends AnyWebSocketMessage {

  public String stream;
  public T data;
}
