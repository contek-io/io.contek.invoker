package io.contek.invoker.bybit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _OrderBookLevel {

  public String symbol;
  public double price;
  public int size;
  public String side;
}
