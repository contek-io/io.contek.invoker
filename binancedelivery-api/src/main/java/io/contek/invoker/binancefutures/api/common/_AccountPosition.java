package io.contek.invoker.binancefutures.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _AccountPosition {

  public String symbol;
  public Double initialMargin;
  public Double maintMargin;
  public Double unrealizedProfit;
  public Double positionInitialMargin;
  public Double openOrderInitialMargin;
  public Integer leverage;
  public Boolean isolated;
  public String positionSide;
  public Double entryPrice;
  public Double maxQty;
}
