package io.contek.invoker.binancespot.api.websocket.market;

import io.contek.invoker.binancespot.api.websocket.common.WebSocketEventMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class MiniTickerEvent extends WebSocketEventMessage {

  public String s; // Symbol
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
            ", c=" + c +
            ", o=" + o +
            ", h=" + h +
            ", l=" + l +
            ", v=" + v +
            ", q=" + q +
            '}';
  }
}
