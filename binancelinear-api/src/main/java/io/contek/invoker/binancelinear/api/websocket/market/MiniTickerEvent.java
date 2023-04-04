package io.contek.invoker.binancelinear.api.websocket.market;

import io.contek.invoker.binancelinear.api.websocket.common.WebSocketEventData;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class MiniTickerEvent extends WebSocketEventData {

  public String s; // Symbol
  public Double c; // Close price
  public Double o; // Open price
  public Double h; // High price
  public Double l; // Low price
  public Double v; // Total traded base asset volume
  public Double q; // Total traded quote asset volume
}
