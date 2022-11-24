package io.contek.invoker.binancelinear.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class _Account {

  public Integer feeTier;
  public Boolean canTrade;
  public Boolean canDeposit;
  public Boolean canWithdraw;
  public Long updateTime;
  public Boolean multiAssetsMargin;
  public String totalInitialMargin;
  public String totalMaintMargin;
  public String totalWalletBalance;
  public String totalUnrealizedProfit;
  public String totalMarginBalance;
  public String totalPositionInitialMargin;
  public String totalOpenOrderInitialMargin;
  public String totalCrossWalletBalance;
  public String totalCrossUnPnl;
  public String availableBalance;
  public String maxWithdrawAmount;
  public List<_AccountAsset> assets;
  public List<_AccountPosition> positions;
}
