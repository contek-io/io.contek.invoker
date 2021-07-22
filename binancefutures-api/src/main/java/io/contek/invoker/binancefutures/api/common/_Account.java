package io.contek.invoker.binancefutures.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class _Account {

  public Integer feeTier;
  public Boolean canDeposit;
  public Boolean canTrade;
  public Boolean canWithdraw;
  public Long updateTime;
  public Double totalInitialMargin;
  public Double totalMaintMargin;
  public Double totalWalletBalance;
  public Double totalUnrealizedProfit;
  public Double totalMarginBalance;
  public Double totalPositionInitialMargin;
  public Double totalOpenOrderInitialMargin;
  public Double totalCrossWalletBalance;
  public Double totalCrossUnPnl;
  public Double availableBalance;
  public Double maxWithdrawAmount;
  public List<_AccountAsset> assets;
  public List<_AccountPosition> positions;

  @Override
  public String toString() {
    return "_Account{"
        + "feeTier="
        + feeTier
        + ", canDeposit="
        + canDeposit
        + ", canTrade="
        + canTrade
        + ", canWithdraw="
        + canWithdraw
        + ", updateTime="
        + updateTime
        + ", totalInitialMargin="
        + totalInitialMargin
        + ", totalMaintMargin="
        + totalMaintMargin
        + ", totalWalletBalance="
        + totalWalletBalance
        + ", totalUnrealizedProfit="
        + totalUnrealizedProfit
        + ", totalMarginBalance="
        + totalMarginBalance
        + ", totalPositionInitialMargin="
        + totalPositionInitialMargin
        + ", totalOpenOrderInitialMargin="
        + totalOpenOrderInitialMargin
        + ", totalCrossWalletBalance="
        + totalCrossWalletBalance
        + ", totalCrossUnPnl="
        + totalCrossUnPnl
        + ", availableBalance="
        + availableBalance
        + ", maxWithdrawAmount="
        + maxWithdrawAmount
        + ", assets="
        + assets
        + ", positions="
        + positions
        + '}';
  }
}
