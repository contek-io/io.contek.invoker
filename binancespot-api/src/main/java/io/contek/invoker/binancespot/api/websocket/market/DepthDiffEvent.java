package io.contek.invoker.binancespot.api.websocket.market;

import io.contek.invoker.binancespot.api.common._OrderBookLevel;
import io.contek.invoker.binancespot.api.websocket.common.WebSocketEventMessage;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class DepthDiffEvent extends WebSocketEventMessage {

  public long T; // transaction time
  public String s; // Symbol
  public long U; // first update Id from last stream
  public long u; // last update Id from last stream
  public List<_OrderBookLevel> b; // Bids to be updated [Price level to be updated, Quantity]
  public List<_OrderBookLevel> a; // Asks to be updated [Price level to be updated, Quantity]
}
