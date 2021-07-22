package io.contek.invoker.binancefutures.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _PositionRisk {

  public Double entryPrice;
  public String marginType;
  public Boolean isAutoAddMargin;
  public Double isolatedMargin;
  public Double leverage;
  public Double liquidationPrice;
  public Double markPrice;
  public Double positionAmt;
  public String symbol;
  public Double unRealizedProfit;
  public String positionSide;

  @Override
  public String toString() {
    return "_PositionRisk{"
        + "entryPrice="
        + entryPrice
        + ", marginType='"
        + marginType
        + '\''
        + ", isAutoAddMargin="
        + isAutoAddMargin
        + ", isolatedMargin="
        + isolatedMargin
        + ", leverage="
        + leverage
        + ", liquidationPrice="
        + liquidationPrice
        + ", markPrice="
        + markPrice
        + ", positionAmt="
        + positionAmt
        + ", symbol='"
        + symbol
        + '\''
        + ", unRealizedProfit="
        + unRealizedProfit
        + ", positionSide='"
        + positionSide
        + '\''
        + '}';
  }
}
