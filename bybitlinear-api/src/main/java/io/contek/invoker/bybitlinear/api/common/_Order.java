package io.contek.invoker.bybitlinear.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Order {

  public String order_id;
  public Long user_id;
  public String symbol;
  public String side;
  public String order_type;
  public Double price;
  public Double qty;
  public String time_in_force;
  public String order_status;
  public String last_exec_price;
  public Double cum_exec_qty;
  public Double cum_exec_value;
  public Double cum_exec_fee;
  public String order_link_id;
  public Boolean reduce_only;
  public Boolean close_on_trigger;
  public String created_time;
  public String updated_time;
}
