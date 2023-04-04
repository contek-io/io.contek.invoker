package io.contek.invoker.binanceinverse.api.websocket.market;

import io.contek.invoker.binanceinverse.api.common._OrderBookLevel;
import io.contek.invoker.binanceinverse.api.websocket.common.WebSocketEventData;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class DepthUpdateEvent extends WebSocketEventData {

  public long T; // transaction time
  public String s; // Symbol
  public String ps; // Pair
  public long U; // first update Id from last stream
  public long u; // last update Id from last stream
  public long pu; // last update Id in last stream (ie 'u' in last stream)
  public List<_OrderBookLevel> b; // Bids to be updated [Price level to be updated, Quantity]
  public List<_OrderBookLevel> a; // Asks to be updated [Price level to be updated, Quantity]
}
