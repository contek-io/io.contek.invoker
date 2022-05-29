package io.contek.invoker.bybit.api.common;

public class _AccountRatio {

  public String symbol;
  public double buy_ratio;
  public double sell_ratio;
  public double timestamp;

  @Override
  public String toString() {
    return "_AccountRatio{" +
            "symbol='" + symbol + '\'' +
            ", buy_ratio=" + buy_ratio +
            ", sell_ratio=" + sell_ratio +
            ", timestamp=" + timestamp +
            '}';
  }
}
