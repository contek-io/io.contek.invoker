package io.contek.invoker.hbdmlinear.api.websocket.common.marketdata;

public abstract class MarketDataWebSocketTickMessage<T> extends MarketDataWebSocketChannelMessage {

  public T tick;
}
