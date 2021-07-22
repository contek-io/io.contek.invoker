package io.contek.invoker.deribit.api.common;

public class _Stats {
  public double high;
  public double low;
  public double price_change;
  public double volume;

  @Override
  public String toString() {
    return "_Stats{" +
            "high=" + high +
            ", low=" + low +
            ", price_change=" + price_change +
            ", volume=" + volume +
            '}';
  }
}
