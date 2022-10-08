package io.contek.invoker.binancefutures.api.websocket.market;

import io.contek.invoker.binancefutures.api.websocket.common.WebSocketEventData;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class MarkPriceUpdateEvent extends WebSocketEventData {

  public String s; // Symbol
  public Double p; // Mark price
  public Double r; // Funding rate
  public Long T; // Next funding time
}
