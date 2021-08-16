package io.contek.invoker.deribit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Order {

  public String order_state;
  public double max_show;
  public boolean api;
  public double amount;
  public boolean web;
  public String instrument_name;
  public String advanced;
  public boolean triggered;
  public boolean block_trade;
  public String original_order_type;
  public double price;
  public String time_in_force;
  public boolean auto_replaced;
  public String stop_order_id;
  public long last_update_timestamp;
  public boolean post_only;
  public boolean replaced;
  public double filled_amount;
  public double average_price;
  public String order_id;
  public boolean reduce_only;
  public double commission;
  public String app_name;
  public double stop_price;
  public String label;
  public long creation_timestamp;
  public String direction;
  public boolean is_liquidation;
  public String order_type;
  public double usd;
  public double profit_loss;
  public double implv;
  public String trigger;

  @Override
  public String toString() {
    return "_Order{" +
            "order_state='" + order_state + '\'' +
            ", max_show=" + max_show +
            ", api=" + api +
            ", amount=" + amount +
            ", web=" + web +
            ", instrument_name='" + instrument_name + '\'' +
            ", advanced='" + advanced + '\'' +
            ", triggered=" + triggered +
            ", block_trade=" + block_trade +
            ", original_order_type='" + original_order_type + '\'' +
            ", price=" + price +
            ", time_in_force='" + time_in_force + '\'' +
            ", auto_replaced=" + auto_replaced +
            ", stop_order_id='" + stop_order_id + '\'' +
            ", last_update_timestamp=" + last_update_timestamp +
            ", post_only=" + post_only +
            ", replaced=" + replaced +
            ", filled_amount=" + filled_amount +
            ", average_price=" + average_price +
            ", order_id='" + order_id + '\'' +
            ", reduce_only=" + reduce_only +
            ", commission=" + commission +
            ", app_name='" + app_name + '\'' +
            ", stop_price=" + stop_price +
            ", label='" + label + '\'' +
            ", creation_timestamp=" + creation_timestamp +
            ", direction='" + direction + '\'' +
            ", is_liquidation=" + is_liquidation +
            ", order_type='" + order_type + '\'' +
            ", usd=" + usd +
            ", profit_loss=" + profit_loss +
            ", implv=" + implv +
            ", trigger='" + trigger + '\'' +
            '}';
  }
}
