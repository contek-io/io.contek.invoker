package io.contek.invoker.bybit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _LotSizeFilter {

  public int max_trading_qty;
  public int min_trading_qty;
  public int qty_step;
}
