package io.contek.invoker.binancedelivery.api.websocket.market;

import io.contek.invoker.commons.api.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class MiniTickerEvent extends AnyWebSocketMessage {

  public String e;
  public Long E; // Event time
  public String s; // Symbol
  public String ps; // Pair
  public Double c; // Close price
  public Double o; // Open price
  public Double h; // High price
  public Double l; // Low price
  public Double v; // Total traded base asset volume
  public Double q; // Total traded quote asset volume
}
