package io.contek.invoker.binancelinear.api.websocket.market;

import io.contek.invoker.binancelinear.api.websocket.common.WebSocketEventData;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class ForceOrderEvent extends WebSocketEventData {

  public Order o;

  @NotThreadSafe
  public static class Order {

    public String s; // Symbol
    public String S; // Side
    public String o; // Order Type
    public String f; // Time in Force
    public Double q; // Original Quantity
    public Double p; // Price
    public Double a; // Average Price
    public String X; // Order Status
    public Double l; // Order Last Filled Quantity
    public Double z; // Order Filled Accumulated Quantity
    public Long T; // Order Trade Time
  }
}
