package io.contek.invoker.binancespot.api.websocket.market;

import io.contek.invoker.binancespot.api.websocket.common.WebSocketEventData;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class BookTickerEvent extends WebSocketEventData {

  public Long u; // order book updateId
  public String s; // symbol
  public Double b; // best bid price
  public Double B; // best bid qty
  public Double a; // best ask price
  public Double A; // best ask qty
}
