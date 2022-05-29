package io.contek.invoker.binancespot.api.websocket.market;

import io.contek.invoker.binancespot.api.websocket.common.WebSocketEventMessage;

public class TickerEvent extends WebSocketEventMessage {

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

  @Override
  public String toString() {
    return "TickerEvent{" +
            "s='" + s + '\'' +
            ", p=" + p +
            ", P=" + P +
            ", w=" + w +
            ", c=" + c +
            ", Q=" + Q +
            ", o=" + o +
            ", h=" + h +
            ", l=" + l +
            ", v=" + v +
            ", q=" + q +
            ", O=" + O +
            ", C=" + C +
            ", F=" + F +
            ", L=" + L +
            ", n=" + n +
            '}';
  }
}
