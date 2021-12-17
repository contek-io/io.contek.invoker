package io.contek.invoker.bybitlinear.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _OrderCancelAll {

  public String clOrdID;
  public long user_id;
  public String symbol;
  public String side;
  public String order_type;
  public double price;
  public int qty;
  public String time_in_force;
  public String create_type;
  public String cancel_type;
  public String order_status;
  public int leaves_qty;
  public double leaves_value;
  public String created_at;
  public String updated_at;
  public String cross_status;
  public long cross_seq;
}
