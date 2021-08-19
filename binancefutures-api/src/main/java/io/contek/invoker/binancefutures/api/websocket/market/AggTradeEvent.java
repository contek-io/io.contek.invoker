package io.contek.invoker.binancefutures.api.websocket.market;

import io.contek.invoker.binancefutures.api.websocket.common.WebSocketEventMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class AggTradeEvent extends WebSocketEventMessage {

  public String s; // Symbol
  public Long a; // Aggregate trade ID
  public Double p; // Price
  public Double q; // Quantity
  public Long f; // First trade ID
  public Long l; // Last trade ID
  public Long T; // Trade time
  public Boolean m; // Is the buyer the market maker?

  @Override
  public String toString() {
    return "AggTradeEvent{" +
            "s='" + s + '\'' +
            ", a=" + a +
            ", p=" + p +
            ", q=" + q +
            ", f=" + f +
            ", l=" + l +
            ", T=" + T +
            ", m=" + m +
            '}';
  }
}
