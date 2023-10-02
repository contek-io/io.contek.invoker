package io.contek.invoker.bybit.api.websocket.market;

import io.contek.invoker.bybit.api.websocket.common.WebSocketTopicMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketMarketMessage<Data> extends WebSocketTopicMessage<Data> {

  public Long ts;
  public String type;
}
