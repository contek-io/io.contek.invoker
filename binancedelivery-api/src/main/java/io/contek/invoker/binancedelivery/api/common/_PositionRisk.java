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

  @Override
  public String toString() {
    return "_PositionRisk{"
        + "symbol='"
        + symbol
        + '\''
        + ", positionAmt="
        + positionAmt
        + ", entryPrice="
        + entryPrice
        + ", markPrice="
        + markPrice
        + ", unRealizedProfit="
        + unRealizedProfit
        + ", liquidationPrice="
        + liquidationPrice
        + ", leverage="
        + leverage
        + ", maxQty="
        + maxQty
        + ", marginType='"
        + marginType
        + '\''
        + ", isolatedMargin="
        + isolatedMargin
        + ", isAutoAddMargin="
        + isAutoAddMargin
        + ", positionSide='"
        + positionSide
        + '\''
        + '}';
  }
}
