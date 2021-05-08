package io.contek.invoker.deribit.api.websocket.market;

import io.contek.invoker.deribit.api.common._OrderBookLevel;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public final class OrderBook {

  public long timestamp;
  public String instrument_name;
  public long change_id;
  public List<_OrderBookLevel> bids;
  public List<_OrderBookLevel> asks;
}
