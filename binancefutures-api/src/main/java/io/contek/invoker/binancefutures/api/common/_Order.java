package io.contek.invoker.binancefutures.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Order {

  public String symbol;
  public Long orderId;
  public String clientOrderId;
  public Double price;
  public Double avgPrice;
  public Double origQty;
  public Double executedQty;
  public String status;
  public String timeInForce;
  public String type;
  public Boolean reduceOnly;
  public String side;
  public String positionSide;
  public Double stopPrice;
  public Long updateTime;
  public String workingType;

  @Override
  public String toString() {
    return "_Order{"
        + "symbol='"
        + symbol
        + '\''
        + ", orderId="
        + orderId
        + ", clientOrderId='"
        + clientOrderId
        + '\''
        + ", price="
        + price
        + ", avgPrice="
        + avgPrice
        + ", origQty="
        + origQty
        + ", executedQty="
        + executedQty
        + ", status='"
        + status
        + '\''
        + ", timeInForce='"
        + timeInForce
        + '\''
        + ", type='"
        + type
        + '\''
        + ", reduceOnly="
        + reduceOnly
        + ", side='"
        + side
        + '\''
        + ", positionSide='"
        + positionSide
        + '\''
        + ", stopPrice="
        + stopPrice
        + ", updateTime="
        + updateTime
        + ", workingType='"
        + workingType
        + '\''
        + '}';
  }
}
