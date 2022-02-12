package io.contek.invoker.hbdminverse.api.websocket.common.marketdata;

import io.contek.invoker.hbdminverse.api.websocket.common.WebSocketInboundMessage;

public abstract class MarketDataWebSocketConfirmation extends WebSocketInboundMessage {

  public String id;
  public String status;
}
