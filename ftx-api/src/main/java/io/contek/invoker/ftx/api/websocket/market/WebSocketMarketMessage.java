package io.contek.invoker.ftx.api.websocket.market;

import io.contek.invoker.ftx.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketMarketMessage<T> extends WebSocketChannelMessage<T> {

  public String market;
}
