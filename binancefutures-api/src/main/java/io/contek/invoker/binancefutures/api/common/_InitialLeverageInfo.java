package io.contek.invoker.binancefutures.api.common;

public class _InitialLeverageInfo {

  public int leverage;
  public String maxNotionalValue;
  public String symbol;

  @Override
  public String toString() {
    return "_InitialLeverageInfo{" +
            "leverage=" + leverage +
            ", maxNotionalValue='" + maxNotionalValue + '\'' +
            ", symbol='" + symbol + '\'' +
            '}';
  }
}
