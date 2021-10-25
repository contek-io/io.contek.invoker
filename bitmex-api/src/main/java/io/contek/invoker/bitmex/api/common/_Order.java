package io.contek.invoker.bitmex.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Order {

  public String orderID;
  public String clOrdID;
  public Long account;
  public String symbol;
  public String side;
  public Double orderQty;
  public Double price;
  public Double displayQty;
  public Double stopPx;
  public Double pegOffsetValue;
  public String pegPriceType;
  public String currency;
  public String settlCurrency;
  public String ordType;
  public String timeInForce;
  public String execInst;
  public String ordStatus;
  public Double leavesQty;
  public Double cumQty;
  public Double avgPx;
  public String transactTime;
  public String timestamp;
  public String error;
}
