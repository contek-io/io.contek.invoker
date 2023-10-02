package io.contek.invoker.bybit.api.websocket.user;

import io.contek.invoker.bybit.api.websocket.common.WebSocketTopicMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketUserMessage<Data> extends WebSocketTopicMessage<Data> {

  public Long creationTime;
  public String id;
}
