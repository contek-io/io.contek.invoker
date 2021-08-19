package io.contek.invoker.binancedelivery.api.websocket.market;

import io.contek.invoker.binancedelivery.api.websocket.common.WebSocketEventMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class MiniTickerEvent extends WebSocketEventMessage {

  public String s; // Symbol
  public String ps; // Pair
  public Double c; // Close price
  public Double o; // Open price
  public Double h; // High price
  public Double l; // Low price
  public Double v; // Total traded base asset volume
  public Double q; // Total traded quote asset volume

  @Override
  public String toString() {
    return "MiniTickerEvent{" +
            "s='" + s + '\'' +
            ", ps='" + ps + '\'' +
            ", c=" + c +
            ", o=" + o +
            ", h=" + h +
            ", l=" + l +
            ", v=" + v +
            ", q=" + q +
            '}';
  }
}
