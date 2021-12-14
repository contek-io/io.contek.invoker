package io.contek.invoker.binancedelivery.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _UserTrade {

  public String symbol;
  public Long id;
  public Long orderId;
  public String pair;
  public String side;
  public String price;
  public String qty;
  public String realizedPnl;
  public String marginAsset;
  public String baseQty;
  public String commission;
  public String commissionAsset;
  public Long time;
  public String positionSide;
  public Boolean buyer;
  public Boolean maker;
}
