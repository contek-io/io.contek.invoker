package io.contek.invoker.binancedelivery.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _InitialLeverageInfo {

  public int leverage;
  public double maxNotionalValue;
  public String symbol;

  @Override
  public String toString() {
    return "_InitialLeverageInfo{" +
            "leverage=" + leverage +
            ", maxNotionalValue=" + maxNotionalValue +
            ", symbol='" + symbol + '\'' +
            '}';
  }
}
