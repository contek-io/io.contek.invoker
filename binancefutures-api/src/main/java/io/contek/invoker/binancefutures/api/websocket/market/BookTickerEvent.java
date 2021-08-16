package io.contek.invoker.binancefutures.api.websocket.market;

import io.contek.invoker.binancefutures.api.websocket.common.WebSocketEventMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class BookTickerEvent extends WebSocketEventMessage {

  public Long u; // order book updateId
  public Long T; // transaction time
  public String s; // symbol
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
            ", b=" + b +
            ", B=" + B +
            ", a=" + a +
            ", A=" + A +
            '}';
  }
}
