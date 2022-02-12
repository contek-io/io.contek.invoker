package io.contek.invoker.hbdminverse.api.websocket.common.marketdata;

import io.contek.invoker.hbdminverse.api.websocket.common.WebSocketInboundMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class MarketDataWebSocketConfirmation extends WebSocketInboundMessage {

  public String id;
  public String status;
}
