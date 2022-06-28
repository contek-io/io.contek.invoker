package io.contek.invoker.binancefutures.api.websocket.market;

import io.contek.invoker.binancefutures.api.websocket.common.WebSocketEventMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class TradeEvent extends WebSocketEventMessage {

  public String s; // Symbol
  public Long t; // Trade ID
  public Double p; // Price
  public Double q; // Quantity
  public Long T; // Trade time
  public Boolean m; // Is the buyer the market maker?
}
