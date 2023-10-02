package io.contek.invoker.bybit.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketTopicMessage<Data> extends AnyWebSocketMessage {

  public String topic;
  public Data data;
}
