package io.contek.invoker.hbdmlinear.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Order {

  public String contract_code;
  public String direction;
  public String offset;
  public String price;
  public int lever_rate;
  public double volume;
  public String order_price_type;
  public double tp_trigger_price;
  public double tp_order_price;
  public String tp_order_price_type;
  public String sl_trigger_price;
  public String sl_order_price;
  public String sl_order_price_type;
}
