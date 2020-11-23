package io.contek.invoker.binancefutures.api.websocket.market;

import io.contek.invoker.commons.api.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class MarkPriceUpdateEvent extends AnyWebSocketMessage {

  public String e;
  public Long E; // Event time
  public String s; // Symbol
  public Double p; // Mark price
  public Double r; // Funding rate
  public Long T; // Next funding time
}
