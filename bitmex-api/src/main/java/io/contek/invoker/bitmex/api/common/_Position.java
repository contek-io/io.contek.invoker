package io.contek.invoker.bitmex.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Position {

  public Long account;
  public String symbol;
  public String currency;
  public String underlying;
  public String quoteCurrency;
  public Double commission;
  public Double initMarginReq;
  public Double maintMarginReq;
  public Long riskLimit;
  public Double leverage;
  public Boolean crossMargin;
  public Double deleveragePercentile;
  public Long rebalancedPnl;
  public Long prevRealisedPnl;
  public Long prevUnrealisedPnl;
  public Double prevClosePrice;
  public String openingTimestamp;
  public Double openingQty;
  public Long openingCost;
  public Long openingComm;
  public Double openOrderBuyQty;
  public Long openOrderBuyCost;
  public Long openOrderBuyPremium;
  public Double openOrderSellQty;
  public Long openOrderSellCost;
  public Long openOrderSellPremium;
  public Double execBuyQty;
  public Long execBuyCost;
  public Double execSellQty;
  public Long execSellCost;
  public Double execQty;
  public Long execCost;
  public Long execComm;
  public String currentTimestamp;
  public Double currentQty;
  public Long currentCost;
  public Long currentComm;
  public Long realisedCost;
  public Long unrealisedCost;
  public Long grossOpenCost;
  public Long grossOpenPremium;
  public Long grossExecCost;
  public Boolean isOpen;
  public Double markPrice;
  public Long markValue;
  public Long riskValue;
  public Double homeNotional;
  public Double foreignNotional;
  public Long posCost;
  public Long posCost2;
  public Long posCross;
  public Long posInit;
  public Long posComm;
  public Long posLoss;
  public Long posMargin;
  public Long posMaint;
  public Long posAllowance;
  public Long taxableMargin;
  public Long initMargin;
  public Long maintMargin;
  public Long sessionMargin;
  public Long targetExcessMargin;
  public Long varMargin;
  public Long realisedGrossPnl;
  public Long realisedTax;
  public Long realisedPnl;
  public Long unrealisedGrossPnl;
  public Long unrealisedPnl;
  public Double unrealisedPnlPcnt;
  public Double unrealisedRoePcnt;
  public Double avgCostPrice;
  public Double avgEntryPrice;
  public Double breakEvenPrice;
  public Double marginCallPrice;
  public Double liquidationPrice;
  public Double bankruptPrice;
  public String timestamp;
  public Double lastPrice;
  public Long lastValue;

  @Override
  public String toString() {
    return "_Position{"
        + "account="
        + account
        + ", symbol='"
        + symbol
        + '\''
        + ", currency='"
        + currency
        + '\''
        + ", underlying='"
        + underlying
        + '\''
        + ", quoteCurrency='"
        + quoteCurrency
        + '\''
        + ", commission="
        + commission
        + ", initMarginReq="
        + initMarginReq
        + ", maintMarginReq="
        + maintMarginReq
        + ", riskLimit="
        + riskLimit
        + ", leverage="
        + leverage
        + ", crossMargin="
        + crossMargin
        + ", deleveragePercentile="
        + deleveragePercentile
        + ", rebalancedPnl="
        + rebalancedPnl
        + ", prevRealisedPnl="
        + prevRealisedPnl
        + ", prevUnrealisedPnl="
        + prevUnrealisedPnl
        + ", prevClosePrice="
        + prevClosePrice
        + ", openingTimestamp='"
        + openingTimestamp
        + '\''
        + ", openingQty="
        + openingQty
        + ", openingCost="
        + openingCost
        + ", openingComm="
        + openingComm
        + ", openOrderBuyQty="
        + openOrderBuyQty
        + ", openOrderBuyCost="
        + openOrderBuyCost
        + ", openOrderBuyPremium="
        + openOrderBuyPremium
        + ", openOrderSellQty="
        + openOrderSellQty
        + ", openOrderSellCost="
        + openOrderSellCost
        + ", openOrderSellPremium="
        + openOrderSellPremium
        + ", execBuyQty="
        + execBuyQty
        + ", execBuyCost="
        + execBuyCost
        + ", execSellQty="
        + execSellQty
        + ", execSellCost="
        + execSellCost
        + ", execQty="
        + execQty
        + ", execCost="
        + execCost
        + ", execComm="
        + execComm
        + ", currentTimestamp='"
        + currentTimestamp
        + '\''
        + ", currentQty="
        + currentQty
        + ", currentCost="
        + currentCost
        + ", currentComm="
        + currentComm
        + ", realisedCost="
        + realisedCost
        + ", unrealisedCost="
        + unrealisedCost
        + ", grossOpenCost="
        + grossOpenCost
        + ", grossOpenPremium="
        + grossOpenPremium
        + ", grossExecCost="
        + grossExecCost
        + ", isOpen="
        + isOpen
        + ", markPrice="
        + markPrice
        + ", markValue="
        + markValue
        + ", riskValue="
        + riskValue
        + ", homeNotional="
        + homeNotional
        + ", foreignNotional="
        + foreignNotional
        + ", posCost="
        + posCost
        + ", posCost2="
        + posCost2
        + ", posCross="
        + posCross
        + ", posInit="
        + posInit
        + ", posComm="
        + posComm
        + ", posLoss="
        + posLoss
        + ", posMargin="
        + posMargin
        + ", posMaint="
        + posMaint
        + ", posAllowance="
        + posAllowance
        + ", taxableMargin="
        + taxableMargin
        + ", initMargin="
        + initMargin
        + ", maintMargin="
        + maintMargin
        + ", sessionMargin="
        + sessionMargin
        + ", targetExcessMargin="
        + targetExcessMargin
        + ", varMargin="
        + varMargin
        + ", realisedGrossPnl="
        + realisedGrossPnl
        + ", realisedTax="
        + realisedTax
        + ", realisedPnl="
        + realisedPnl
        + ", unrealisedGrossPnl="
        + unrealisedGrossPnl
        + ", unrealisedPnl="
        + unrealisedPnl
        + ", unrealisedPnlPcnt="
        + unrealisedPnlPcnt
        + ", unrealisedRoePcnt="
        + unrealisedRoePcnt
        + ", avgCostPrice="
        + avgCostPrice
        + ", avgEntryPrice="
        + avgEntryPrice
        + ", breakEvenPrice="
        + breakEvenPrice
        + ", marginCallPrice="
        + marginCallPrice
        + ", liquidationPrice="
        + liquidationPrice
        + ", bankruptPrice="
        + bankruptPrice
        + ", timestamp='"
        + timestamp
        + '\''
        + ", lastPrice="
        + lastPrice
        + ", lastValue="
        + lastValue
        + '}';
  }
}
