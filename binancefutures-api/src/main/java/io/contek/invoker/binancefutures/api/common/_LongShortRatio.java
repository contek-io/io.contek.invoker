package io.contek.invoker.binancefutures.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class _LongShortRatio {

  public String symbol;
  public double longShortRatio;
  public double longAccount;
  public double shortAccount;
  public long timestamp;

  @Override
  public String toString() {
    return "_LongShortRatio{"
        + "symbol='"
        + symbol
        + '\''
        + ", longShortRatio="
        + longShortRatio
        + ", longAccount="
        + longAccount
        + ", shortAccount="
        + shortAccount
        + ", timestamp="
        + timestamp
        + '}';
  }
}
