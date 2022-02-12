package io.contek.invoker.hbdmlinear.api.websocket.common.marketdata;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class MarketDataWebSocketPong extends AnyWebSocketMessage {

  public long pong;
}
