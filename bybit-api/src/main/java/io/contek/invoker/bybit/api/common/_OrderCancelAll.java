package io.contek.invoker.bybit.api.common;

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

  @Override
  public String toString() {
    return "_OrderCancelAll{" +
            "clOrdID='" + clOrdID + '\'' +
            ", user_id=" + user_id +
            ", symbol='" + symbol + '\'' +
            ", side='" + side + '\'' +
            ", order_type='" + order_type + '\'' +
            ", price=" + price +
            ", qty=" + qty +
            ", time_in_force='" + time_in_force + '\'' +
            ", create_type='" + create_type + '\'' +
            ", cancel_type='" + cancel_type + '\'' +
            ", order_status='" + order_status + '\'' +
            ", leaves_qty=" + leaves_qty +
            ", leaves_value=" + leaves_value +
            ", created_at='" + created_at + '\'' +
            ", updated_at='" + updated_at + '\'' +
            ", cross_status='" + cross_status + '\'' +
            ", cross_seq=" + cross_seq +
            '}';
  }
}
