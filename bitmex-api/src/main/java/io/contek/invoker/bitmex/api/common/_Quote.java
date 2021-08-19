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

  @Override
  public String toString() {
    return "_Quote{" +
            "timestamp='" + timestamp + '\'' +
            ", symbol='" + symbol + '\'' +
            ", bidSize=" + bidSize +
            ", bidPrice=" + bidPrice +
            ", askSize=" + askSize +
            ", askPrice=" + askPrice +
            '}';
  }
}
