package io.contek.invoker.bybit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _OrderBookLevel {

  public String symbol;
  public Double price;
  public Double size;
  public String side;
}
