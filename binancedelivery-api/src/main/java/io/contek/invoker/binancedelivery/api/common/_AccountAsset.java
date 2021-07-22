package io.contek.invoker.binancedelivery.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _AccountAsset {

  public String asset;
  public Double walletBalance;
  public Double unrealizedProfit;
  public Double marginBalance;
  public Double maintMargin;
  public Double initialMargin;
  public Double positionInitialMargin;
  public Double openOrderInitialMargin;
  public Double maxWithdrawAmount;
  public Double crossWalletBalance;
  public Double crossUnPnl;
  public Double availableBalance;

  @Override
  public String toString() {
    return "_AccountAsset{"
        + "asset='"
        + asset
        + '\''
        + ", walletBalance="
        + walletBalance
        + ", unrealizedProfit="
        + unrealizedProfit
        + ", marginBalance="
        + marginBalance
        + ", maintMargin="
        + maintMargin
        + ", initialMargin="
        + initialMargin
        + ", positionInitialMargin="
        + positionInitialMargin
        + ", openOrderInitialMargin="
        + openOrderInitialMargin
        + ", maxWithdrawAmount="
        + maxWithdrawAmount
        + ", crossWalletBalance="
        + crossWalletBalance
        + ", crossUnPnl="
        + crossUnPnl
        + ", availableBalance="
        + availableBalance
        + '}';
  }
}
