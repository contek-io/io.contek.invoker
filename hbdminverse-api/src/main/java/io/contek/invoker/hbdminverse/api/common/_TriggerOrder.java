package io.contek.invoker.hbdminverse.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _TriggerOrder {

  public String symbol;
  public String contract_code;
  public String trigger_type;
  public Integer volume;
  public Integer order_type;
  public String direction;
  public String offset;
  public Integer lever_rate;
  public Long order_id;
  public String order_id_str;
  public String relation_order_id;
  public String order_price_type;
  public Integer status;
  public String order_source;
  public Double trigger_price;
  public Double triggered_price;
  public Double order_price;
  public Long created_at;
  public Long triggered_at;
  public Long order_insert_at;
  public Long canceled_at;
  public Integer fail_code;
  public String fail_reason;
}
