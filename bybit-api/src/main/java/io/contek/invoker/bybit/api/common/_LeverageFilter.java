package io.contek.invoker.bybit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _LeverageFilter {

  public double min_leverage;
  public double max_leverage;
  public double leverage_step;

  @Override
  public String toString() {
    return "_LeverageFilter{" +
            "min_leverage=" + min_leverage +
            ", max_leverage=" + max_leverage +
            ", leverage_step=" + leverage_step +
            '}';
  }
}
