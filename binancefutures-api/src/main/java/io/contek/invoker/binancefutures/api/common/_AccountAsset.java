package io.contek.invoker.binancefutures.api.common;

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
  public Double crossWalletBalance;
  public Double crossUnPnl;
  public Double availableBalance;
  public Double maxWithdrawAmount;

  @Override
  public String toString() {
    return "_AccountAsset{" +
            "asset='" + asset + '\'' +
            ", walletBalance=" + walletBalance +
            ", unrealizedProfit=" + unrealizedProfit +
            ", marginBalance=" + marginBalance +
            ", maintMargin=" + maintMargin +
            ", initialMargin=" + initialMargin +
            ", positionInitialMargin=" + positionInitialMargin +
            ", openOrderInitialMargin=" + openOrderInitialMargin +
            ", crossWalletBalance=" + crossWalletBalance +
            ", crossUnPnl=" + crossUnPnl +
            ", availableBalance=" + availableBalance +
            ", maxWithdrawAmount=" + maxWithdrawAmount +
            '}';
  }
}
