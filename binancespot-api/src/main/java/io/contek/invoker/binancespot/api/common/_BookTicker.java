package io.contek.invoker.binancespot.api.common;

public class _BookTicker {

  public String symbol;
  public Double bidPrice;
  public Double bidQty;
  public Double askPrice;
  public Double askQty;

  @Override
  public String toString() {
    return "_BookTicker{" +
            "symbol='" + symbol + '\'' +
            ", bidPrice=" + bidPrice +
            ", bidQty=" + bidQty +
            ", askPrice=" + askPrice +
            ", askQty=" + askQty +
            '}';
  }
}
