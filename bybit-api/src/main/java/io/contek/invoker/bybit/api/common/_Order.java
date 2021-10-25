package io.contek.invoker.bybit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Order {

  public long user_id;
  public String order_id;
  public String symbol;
  public String side;
  public String order_type;
  public double price;
  public int qty;
  public String time_in_force;
  public String order_status;
  public String last_exec_time;
  public double last_exec_price;
  public int leaves_qty;
  public double leaves_value;
  public int cum_exec_qty;
  public double cum_exec_value;
  public double cum_exec_fee;
  public String reject_reason;
  public String order_link_id;
  public String created_at;
  public String updated_at;
  public double take_profit;
  public double stop_loss;
  public String tp_trigger_by;
  public String sl_trigger_by;
}
