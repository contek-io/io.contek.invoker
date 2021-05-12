package io.contek.invoker.hbdminverse.api.websocket.market;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketTickMessage<T> extends MarketWebSocketChannelMessage {

  public T tick;
}
