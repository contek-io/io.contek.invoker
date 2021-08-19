package io.contek.invoker.binancedelivery.api.websocket.market;

import io.contek.invoker.binancedelivery.api.common._OrderBookLevel;
import io.contek.invoker.binancedelivery.api.websocket.common.WebSocketEventMessage;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class DepthUpdateEvent extends WebSocketEventMessage {

  public long T; // transaction time
  public String s; // Symbol
  public String ps; // Pair
  public long U; // first update Id from last stream
  public long u; // last update Id from last stream
  public long pu; // last update Id in last stream (ie ‘u’ in last stream)
  public List<_OrderBookLevel> b; // Bids to be updated [Price level to be updated, Quantity]
  public List<_OrderBookLevel> a; // Asks to be updated [Price level to be updated, Quantity]

  @Override
  public String toString() {
    return "DepthUpdateEvent{" +
            "T=" + T +
            ", s='" + s + '\'' +
            ", ps='" + ps + '\'' +
            ", U=" + U +
            ", u=" + u +
            ", pu=" + pu +
            ", b=" + b +
            ", a=" + a +
            '}';
  }
}
