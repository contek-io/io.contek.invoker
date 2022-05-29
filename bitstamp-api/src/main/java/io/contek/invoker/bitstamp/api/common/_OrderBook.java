package io.contek.invoker.bitstamp.api.common;

import java.util.List;

public class _OrderBook {

  public List<_OrderBookLevel> asks;
  public List<_OrderBookLevel> bids;
  public Long microtimestamp;
  public Long timestamp;

  @Override
  public String toString() {
    return "_OrderBook{" +
            "asks=" + asks +
            ", bids=" + bids +
            ", microtimestamp=" + microtimestamp +
            ", timestamp=" + timestamp +
            '}';
  }
}
