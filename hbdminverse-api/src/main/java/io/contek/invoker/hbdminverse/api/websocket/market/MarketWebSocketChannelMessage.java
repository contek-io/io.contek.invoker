package io.contek.invoker.hbdminverse.api.websocket.market;

import io.contek.invoker.hbdminverse.api.websocket.common.WebSocketInboundMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class MarketWebSocketChannelMessage extends WebSocketInboundMessage {

  public String ch;
}
