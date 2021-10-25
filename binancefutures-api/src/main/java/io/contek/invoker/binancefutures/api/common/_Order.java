package io.contek.invoker.binancefutures.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Order {

  public String clientOrderId;
  public Double cumQuote;
  public Double cumBase;
  public Double executedQty;
  public Long orderId;
  public Double avgPrice;
  public Double origQty;
  public Double price;
  public Boolean reduceOnly;
  public String side;
  public String positionSide;
  public String status;
  public Double stopPrice;
  public Boolean closePosition;
  public String symbol;
  public String timeInForce;
  public String type;
  public String origType;
  public Double activatePrice;
  public Double priceRate;
  public Long updateTime;
  public String workingType;
  public Boolean priceProtect;
}
