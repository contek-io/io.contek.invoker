package io.contek.invoker.bybit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _PriceFilter {

  public double min_price;
  public double max_price;
  public double tick_size;

  @Override
  public String toString() {
    return "_PriceFilter{" +
            "min_price=" + min_price +
            ", max_price=" + max_price +
            ", tick_size=" + tick_size +
            '}';
  }
}
