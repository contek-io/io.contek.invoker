package io.contek.invoker.binancefutures.api.websocket.market;

import io.contek.invoker.commons.api.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class BookTickerEvent extends AnyWebSocketMessage {

  public Long u; // order book updateId
  public Long E; // event time
  public Long T; // transaction time
  public String s; // symbol
  public String ps; // pair
  public Double b; // best bid price
  public Double B; // best bid qty
  public Double a; // best ask price
  public Double A; // best ask qty
}
