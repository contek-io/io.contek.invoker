package io.contek.invoker.binancedelivery.api.common;

public final class _LongShortRatio {

  public String pair;
  public double longShortRatio;
  public double longAccount;
  public double shortAccount;
  public long timestamp;

  @Override
  public String toString() {
    return "_LongShortRatio{" +
            "pair='" + pair + '\'' +
            ", longShortRatio=" + longShortRatio +
            ", longAccount=" + longAccount +
            ", shortAccount=" + shortAccount +
            ", timestamp=" + timestamp +
            '}';
  }
}
