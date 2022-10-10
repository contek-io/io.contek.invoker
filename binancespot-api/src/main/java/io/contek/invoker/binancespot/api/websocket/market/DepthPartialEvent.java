package io.contek.invoker.binancespot.api.websocket.market;

import io.contek.invoker.binancespot.api.common._OrderBookLevel;
import io.contek.invoker.binancespot.api.websocket.common.WebSocketEventData;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class DepthPartialEvent extends WebSocketEventData {

  public long lastUpdateId; // last update Id from last stream
  public List<_OrderBookLevel> bids; // Bids to be updated [Price level to be updated, Quantity]
  public List<_OrderBookLevel> asks; // Asks to be updated [Price level to be updated, Quantity]
}
