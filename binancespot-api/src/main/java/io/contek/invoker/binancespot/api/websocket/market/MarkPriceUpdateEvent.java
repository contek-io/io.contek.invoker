package io.contek.invoker.binancespot.api.websocket.market;

import io.contek.invoker.binancespot.api.websocket.common.WebSocketEventMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class MarkPriceUpdateEvent extends WebSocketEventMessage {

  public String s; // Symbol
  public Double p; // Mark price
  public Double r; // Funding rate
  public Long T; // Next funding time
}
