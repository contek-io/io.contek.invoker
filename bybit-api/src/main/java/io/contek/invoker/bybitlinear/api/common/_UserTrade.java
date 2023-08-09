package io.contek.invoker.bybitlinear.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _UserTrade {

  public String order_id;
  public String order_link_id;
  public String side;
  public String symbol;
  public String exec_id;
  public String order_price;
  public Double order_qty;
  public String order_type;
  public String fee_rate;
  public String exec_price;
  public String exec_type;
  public String exec_fee;
  public String exec_value;
  public Double exec_qty;
  public Double leaves_qty;
  public Double closed_size;
  public String last_liquidity_ind;
  public Long trade_time_ms;
}
