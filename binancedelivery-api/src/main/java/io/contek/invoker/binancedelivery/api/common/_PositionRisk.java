package io.contek.invoker.binancedelivery.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _PositionRisk {

  public String symbol;
  public Double positionAmt;
  public Double entryPrice;
  public Double markPrice;
  public Double unRealizedProfit;
  public Double liquidationPrice;
  public Double leverage;
  public Double maxQty;
  public String marginType;
  public Double isolatedMargin;
  public Boolean isAutoAddMargin;
  public String positionSide;
  public Long updateTime;
}
