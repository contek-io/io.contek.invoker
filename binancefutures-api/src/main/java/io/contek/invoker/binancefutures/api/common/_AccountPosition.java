package io.contek.invoker.binancefutures.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _AccountPosition {

  public String symbol;
  public Double initialMargin;
  public Double maintMargin;
  public Double unrealizedProfit;
  public Double positionInitialMargin;
  public Integer leverage;
  public Boolean isolated;
  public Double openOrderInitialMargin;
  public Double entryPrice;
  public Double maxNotional;
  public String positionSide;

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
        + ", leverage="
        + leverage
        + ", isolated="
        + isolated
        + ", openOrderInitialMargin="
        + openOrderInitialMargin
        + ", entryPrice="
        + entryPrice
        + ", maxNotional="
        + maxNotional
        + ", positionSide='"
        + positionSide
        + '\''
        + '}';
  }
}
