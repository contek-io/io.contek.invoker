package io.contek.invoker.bybit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _LeverageFilter {

  public int min_leverage;
  public int max_leverage;
  public double leverage_step;
}
