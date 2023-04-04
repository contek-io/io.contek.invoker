package io.contek.invoker.binancespot.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Order {

  public String symbol;
  public Long orderId;
  public Long orderListId;
  public String clientOrderId;
  public Long transactTime;
  public String price;
  public String origQty;
  public String executedQty;
  public String cummulativeQuoteQty;
  public String status;
  public String timeInForce;
  public String type;
  public String side;
  public String stopPrice;
  public String icebergQty;
  public Long time;
  public Long updateTime;
  public Boolean isWorking;
  public Long workingTime;
  public String origClientOrderId;
  public String origQuoteOrderQty;
}
