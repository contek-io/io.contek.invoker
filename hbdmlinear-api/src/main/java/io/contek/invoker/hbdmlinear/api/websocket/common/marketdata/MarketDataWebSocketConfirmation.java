package io.contek.invoker.hbdmlinear.api.websocket.common.marketdata;

import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketInboundMessage;

public abstract class MarketDataWebSocketConfirmation extends WebSocketInboundMessage {

  public String id;
  public String status;
}
