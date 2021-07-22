package io.contek.invoker.binancefutures.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _BookTicker {

  public String symbol;
  public Double bidPrice;
  public Double bidQty;
  public Double askPrice;
  public Double askQty;
  public Long time;

  @Override
  public String toString() {
    return "_BookTicker{"
        + "symbol='"
        + symbol
        + '\''
        + ", bidPrice="
        + bidPrice
        + ", bidQty="
        + bidQty
        + ", askPrice="
        + askPrice
        + ", askQty="
        + askQty
        + ", time="
        + time
        + '}';
  }
}
