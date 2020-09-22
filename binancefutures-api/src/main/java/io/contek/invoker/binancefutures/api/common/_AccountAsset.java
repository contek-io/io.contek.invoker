package io.contek.invoker.binancefutures.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _AccountAsset {

  public String asset;
  public Double initialMargin;
  public Double maintMargin;
  public Double marginBalance;
  public Double maxWithdrawAmount;
  public Double openOrderInitialMargin;
  public Double positionInitialMargin;
  public Double unrealizedProfit;
  public Double walletBalance;
}
