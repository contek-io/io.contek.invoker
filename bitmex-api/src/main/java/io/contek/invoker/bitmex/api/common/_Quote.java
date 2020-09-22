package io.contek.invoker.bitmex.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Quote {

  public String timestamp;
  public String symbol;
  public Double bidSize;
  public Double bidPrice;
  public Double askSize;
  public Double askPrice;
}
