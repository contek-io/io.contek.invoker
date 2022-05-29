package io.contek.invoker.binancedelivery.api.websocket.market;

import io.contek.invoker.binancedelivery.api.websocket.common.WebSocketEventMessage;

public class BookTickerEvent extends WebSocketEventMessage {

  public Long u; // order book updateId
  public Long T; // transaction time
  public String s; // symbol
  public String ps; // pair
  public Double b; // best bid price
  public Double B; // best bid qty
  public Double a; // best ask price
  public Double A; // best ask qty

  @Override
  public String toString() {
    return "BookTickerEvent{" +
            "u=" + u +
            ", T=" + T +
            ", s='" + s + '\'' +
            ", ps='" + ps + '\'' +
            ", b=" + b +
            ", B=" + B +
            ", a=" + a +
            ", A=" + A +
            '}';
  }
}
