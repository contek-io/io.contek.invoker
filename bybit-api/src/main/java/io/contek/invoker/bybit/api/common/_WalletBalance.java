package io.contek.invoker.bybit.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class _WalletBalance {

  public String totalEquity;
  public String accountIMRate;
  public String totalMarginBalance;
  public String totalInitialMargin;
  public String accountType;
  public String totalAvailableBalance;
  public String accountMMRate;
  public String totalPerpUPL;
  public String totalWalletBalance;
  public String accountLTV;
  public String totalMaintenanceMargin;
  public List<_CoinBalance> coin;
}
