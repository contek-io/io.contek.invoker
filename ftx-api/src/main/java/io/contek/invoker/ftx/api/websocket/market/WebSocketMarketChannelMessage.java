package io.contek.invoker.ftx.api.websocket.market;

import io.contek.invoker.ftx.api.websocket.common.WebSocketChannelMessage;

public abstract class WebSocketMarketChannelMessage<T> extends WebSocketChannelMessage<T> {

  public String market;
}
