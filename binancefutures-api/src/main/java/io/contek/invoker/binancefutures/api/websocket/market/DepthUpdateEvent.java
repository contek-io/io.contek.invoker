package io.contek.invoker.binancefutures.api.websocket.market;

import io.contek.invoker.binancefutures.api.common._OrderBookLevel;
import io.contek.invoker.commons.api.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class DepthUpdateEvent extends AnyWebSocketMessage {

  public String e;
  public long E; // Event time
  public long T; // transaction time
  public String s; // Symbol
  public long U; // first update Id from last stream
  public long u; // last update Id from last stream
  public long pu; // last update Id in last stream (ie ‘u’ in last stream)
  public List<_OrderBookLevel> b; // Bids to be updated [Price level to be updated, Quantity]
  public List<_OrderBookLevel> a; // Asks to be updated [Price level to be updated, Quantity]
}
