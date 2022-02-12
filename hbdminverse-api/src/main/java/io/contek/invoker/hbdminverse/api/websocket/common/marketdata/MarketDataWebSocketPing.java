package io.contek.invoker.hbdminverse.api.websocket.common.marketdata;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class MarketDataWebSocketPing extends AnyWebSocketMessage {

  public long ping;
}
