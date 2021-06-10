package io.contek.invoker.hbdminverse.api.websocket.common.marketdata;

import io.contek.invoker.hbdminverse.api.websocket.common.WebSocketInboundMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class MarketDataWebSocketChannelMessage extends WebSocketInboundMessage {

  public String ch;
}
