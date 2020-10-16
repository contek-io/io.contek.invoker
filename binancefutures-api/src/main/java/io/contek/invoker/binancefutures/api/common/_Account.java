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
}
