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
}
