package io.contek.invoker.okx.api.websocket.market;

import io.contek.invoker.okx.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketMarketChannelMessage<T> extends WebSocketChannelMessage<T> {

  public String market;
}
