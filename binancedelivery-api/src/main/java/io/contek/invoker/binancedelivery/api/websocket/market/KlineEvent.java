package io.contek.invoker.binancedelivery.api.websocket.market;

import io.contek.invoker.binancedelivery.api.websocket.common.WebSocketEventMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class KlineEvent extends WebSocketEventMessage {

  public String s; // Symbol
  public String ps; // Pair
  public Candle k;

  @Override
  public String toString() {
    return "KlineEvent{" +
            "s='" + s + '\'' +
            ", ps='" + ps + '\'' +
            ", k=" + k +
            '}';
  }

  @NotThreadSafe
  public static class Candle {

    public Long t; // Kline start time
    public Long T; // Kline close time
    public String s; // Symbol
    public String i; // Interval
    public Long f; // First trade ID
    public Long L; // Last trade ID
    public Double o; // Open price
    public Double c; // Close price
    public Double h; // High price
    public Double l; // Low price
    public Double v; // Base asset volume
    public Integer n; // Number of trades
    public Boolean x; // Is this kline closed?
    public Double q; // Quote asset volume
    public Double V; // Taker buy base asset volume
    public Double Q; // Taker buy quote asset volume

    @Override
    public String toString() {
      return "Candle{" +
              "t=" + t +
              ", T=" + T +
              ", s='" + s + '\'' +
              ", i='" + i + '\'' +
              ", f=" + f +
              ", L=" + L +
              ", o=" + o +
              ", c=" + c +
              ", h=" + h +
              ", l=" + l +
              ", v=" + v +
              ", n=" + n +
              ", x=" + x +
              ", q=" + q +
              ", V=" + V +
              ", Q=" + Q +
              '}';
    }
  }
}
