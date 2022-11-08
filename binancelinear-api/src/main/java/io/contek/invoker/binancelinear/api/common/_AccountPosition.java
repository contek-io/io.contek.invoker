package io.contek.invoker.binancelinear.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _AccountPosition {

  public String symbol;
  public String initialMargin;
  public String maintMargin;
  public String unrealizedProfit;
  public String positionInitialMargin;
  public String openOrderInitialMargin;
  public String leverage;
  public Boolean isolated;
  public String entryPrice;
  public String maxNotional;
  public String bidNotional;
  public String askNotional;
  public String positionSide;
  public String positionAmt;
  public Long updateTime;
}
