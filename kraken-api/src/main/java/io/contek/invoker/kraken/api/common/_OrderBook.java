package io.contek.invoker.kraken.api.common;


import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public final class _OrderBook {
  // snapshot payload
  public List<_OrderBookLevel> as;
  public List<_OrderBookLevel> bs;
  // update payload
  public List<_OrderBookLevel> a;
  public List<_OrderBookLevel> b;
  public String c;
}
