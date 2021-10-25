package io.contek.invoker.ftx.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _WalletBalance {

  public String coin;
  public double free;
  public double spotBorrow;
  public double total;
  public double usdValue;
  public double availableWithoutBorrow;
}
