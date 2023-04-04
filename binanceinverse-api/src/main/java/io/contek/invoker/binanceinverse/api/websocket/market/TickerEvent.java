package io.contek.invoker.binanceinverse.api.websocket.market;

import io.contek.invoker.binanceinverse.api.websocket.common.WebSocketEventData;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class TickerEvent extends WebSocketEventData {

  public String s; // Symbol
  public Double p; // Price change
  public Double P; // Price change percent
  public Double w; // Weighted average price
  public Double c; // Last price
  public Double Q; // Last quantity
  public Double o; // Open price
  public Double h; // High price
  public Double l; // Low price
  public Double v; // Total traded base asset volume
  public Double q; // Total traded quote asset volume
  public Long O; // Statistics open time
  public Long C; // Statistics close time
  public Long F; // First trade ID
  public Long L; // Last trade Id
  public Integer n; // Total number of trades
}
