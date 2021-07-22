package io.contek.invoker.bybit.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class _UserTradingRecords {

  public List<_UserTrade> trade_list;

  @Override
  public String toString() {
    return "_UserTradingRecords{" + "trade_list=" + trade_list + '}';
  }
}
