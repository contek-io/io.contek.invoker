package io.contek.invoker.hbdmlinear.api.common;

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

  @Override
  public String toString() {
    return "_Order{" +
            "contract_code='" + contract_code + '\'' +
            ", direction='" + direction + '\'' +
            ", offset='" + offset + '\'' +
            ", price='" + price + '\'' +
            ", lever_rate=" + lever_rate +
            ", volume=" + volume +
            ", order_price_type='" + order_price_type + '\'' +
            ", tp_trigger_price=" + tp_trigger_price +
            ", tp_order_price=" + tp_order_price +
            ", tp_order_price_type='" + tp_order_price_type + '\'' +
            ", sl_trigger_price='" + sl_trigger_price + '\'' +
            ", sl_order_price='" + sl_order_price + '\'' +
            ", sl_order_price_type='" + sl_order_price_type + '\'' +
            '}';
  }
}
