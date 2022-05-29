package io.contek.invoker.hbdminverse.api.websocket.common.marketdata;

public abstract class MarketDataWebSocketTickMessage<T> extends MarketDataWebSocketChannelMessage {

  public T tick;
}
