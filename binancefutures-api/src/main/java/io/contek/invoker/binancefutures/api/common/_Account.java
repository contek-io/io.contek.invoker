package io.contek.invoker.binancefutures.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class _Account {

  public Boolean canDeposit;
  public Boolean canTrade;
  public Boolean canWithdraw;
  public Integer feeTier;
  public Double maxWithdrawAmount;
  public Double totalInitialMargin;
  public Double totalMaintMargin;
  public Double totalMarginBalance;
  public Double totalOpenOrderInitialMargin;
  public Double totalPositionInitialMargin;
  public Double totalUnrealizedProfit;
  public Double totalWalletBalance;
  public Long updateTime;
  public List<_AccountAsset> assets;
  public List<_AccountPosition> positions;
}
