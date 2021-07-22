package io.contek.invoker.binancedelivery.api.common;

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

  @Override
  public String toString() {
    return "_AccountPosition{"
        + "symbol='"
        + symbol
        + '\''
        + ", initialMargin="
        + initialMargin
        + ", maintMargin="
        + maintMargin
        + ", unrealizedProfit="
        + unrealizedProfit
        + ", positionInitialMargin="
        + positionInitialMargin
        + ", openOrderInitialMargin="
        + openOrderInitialMargin
        + ", leverage="
        + leverage
        + ", isolated="
        + isolated
        + ", positionSide='"
        + positionSide
        + '\''
        + ", entryPrice="
        + entryPrice
        + ", maxQty="
        + maxQty
        + '}';
  }
}
