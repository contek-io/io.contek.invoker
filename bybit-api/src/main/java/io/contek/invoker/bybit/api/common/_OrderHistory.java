package io.contek.invoker.bybit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _OrderHistory {

  public String orderId;
  public String orderLinkId;
  public String blockTradeId;
  public String symbol;
  public String price;
  public String qty;
  public String side;
  public String isLeverage;
  public Integer positionIdx;
  public String orderStatus;
  public String cancelType;
  public String rejectReason;
  public String avgPrice;
  public String leavesQty;
  public String leavesValue;
  public String cumExecQty;
  public String cumExecValue;
  public String cumExecFee;
  public String timeInForce;
  public String orderType;
  public String stopOrderType;
  public String orderIv;
  public String triggerPrice;
  public String takeProfit;
  public String stopLoss;
  public String tpTriggerBy;
  public String slTriggerBy;
  public Integer triggerDirection;
  public String triggerBy;
  public String lastPriceOnCreated;
  public Boolean reduceOnly;
  public Boolean closeOnTrigger;
  public String smpType;
  public Integer smpGroup;
  public String smpOrderId;
  public String tpslMode;
  public String tpLimitPrice;
  public String slLimitPrice;
  public String placeType;
  public String createdTime;
  public String updatedTime;
}
