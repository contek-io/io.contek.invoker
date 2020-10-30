package io.contek.invoker.ftx.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class _OrderBook {

  public List<_OrderBookLevel> asks;
  public List<_OrderBookLevel> bids;
}
