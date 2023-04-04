package io.contek.invoker.binanceinverse.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _AccountPosition {

  public String symbol;
  public String positionAmt;
  public String initialMargin;
  public String maintMargin;
  public String unrealizedProfit;
  public String positionInitialMargin;
  public String openOrderInitialMargin;
  public String leverage;
  public Boolean isolated;
  public String positionSide;
  public String entryPrice;
  public String maxQty;
  public Long updateTime;
}
