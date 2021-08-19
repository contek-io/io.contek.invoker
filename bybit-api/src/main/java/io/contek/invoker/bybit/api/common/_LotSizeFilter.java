package io.contek.invoker.bybit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _LotSizeFilter {

  public double max_trading_qty;
  public double min_trading_qty;
  public double qty_step;

  @Override
  public String toString() {
    return "_LotSizeFilter{" +
            "max_trading_qty=" + max_trading_qty +
            ", min_trading_qty=" + min_trading_qty +
            ", qty_step=" + qty_step +
            '}';
  }
}
