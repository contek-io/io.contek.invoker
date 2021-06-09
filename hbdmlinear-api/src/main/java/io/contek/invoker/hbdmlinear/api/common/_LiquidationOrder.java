package io.contek.invoker.hbdmlinear.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _LiquidationOrder {

  public String contract_code;
  public String symbol;
  public String direction;
  public String offset;
  public double volume;
  public long created_at;
  public double amount;
  public double trade_turnover;
}
