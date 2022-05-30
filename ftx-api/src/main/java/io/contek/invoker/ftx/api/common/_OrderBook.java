package io.contek.invoker.ftx.api.common;

import java.io.Serializable;
import java.util.List;

public class _OrderBook implements Serializable {

  public List<_OrderBookLevel> asks;
  public List<_OrderBookLevel> bids;

  @Override
  public String toString() {
    return "_OrderBook{" +
            "asks=" + asks +
            ", bids=" + bids +
            '}';
  }
}
