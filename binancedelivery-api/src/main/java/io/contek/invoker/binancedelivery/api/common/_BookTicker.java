package io.contek.invoker.binancedelivery.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _BookTicker {

  public String symbol;
  public String pair;
  public Double bidPrice;
  public Double bidQty;
  public Double askPrice;
  public Double askQty;
  public Long time;
}
