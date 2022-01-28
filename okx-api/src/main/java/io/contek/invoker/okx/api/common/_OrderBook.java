package io.contek.invoker.okx.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class _OrderBook {

  public List<_OrderBookLevel> asks;
  public List<_OrderBookLevel> bids;
}
