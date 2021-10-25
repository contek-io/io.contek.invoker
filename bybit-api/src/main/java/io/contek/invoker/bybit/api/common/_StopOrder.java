package io.contek.invoker.bybit.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Map;

@NotThreadSafe
public class _StopOrder {

  public long user_id;
  public String stop_order_status;
  public String symbol;
  public String side;
  public String order_type;
  public double price;
  public int qty;
  public String time_in_force;
  public String stop_order_type;
  public String trigger_by;
  public double base_price;
  public String remark;
  public String reject_reason;
  public double stop_px;
  public String stop_order_id;
  public String order_status;
  public Map<String, String> ext_fields;
  public int leaves_qty;
  public double leaves_value;
  public int cum_exec_qty;
  public double cum_exec_value;
  public double cum_exec_fee;
  public String order_id;
  public String order_link_id;
  public double take_profit;
  public double stop_loss;
  public String created_at;
  public String updated_at;
  public String tp_trigger_by;
  public String sl_trigger_by;
}
