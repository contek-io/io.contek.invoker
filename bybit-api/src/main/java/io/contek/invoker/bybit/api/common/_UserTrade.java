package io.contek.invoker.bybit.api.common;

public class _UserTrade {

  public Double closed_size;
  public Long cross_seq;
  public String exec_fee;
  public String exec_id;
  public String exec_price;
  public Double exec_qty;
  public String exec_type;
  public String exec_value;
  public String fee_rate;
  public String last_liquidity_ind;
  public Double leaves_qty;
  public Integer nth_fill;
  public String order_id;
  public String order_link_id;
  public String order_price;
  public Double order_qty;
  public String order_type;
  public String side;
  public String symbol;
  public Long user_id;
  public Long trade_time_ms;

  @Override
  public String toString() {
    return "_UserTrade{" +
            "closed_size=" + closed_size +
            ", cross_seq=" + cross_seq +
            ", exec_fee='" + exec_fee + '\'' +
            ", exec_id='" + exec_id + '\'' +
            ", exec_price='" + exec_price + '\'' +
            ", exec_qty=" + exec_qty +
            ", exec_type='" + exec_type + '\'' +
            ", exec_value='" + exec_value + '\'' +
            ", fee_rate='" + fee_rate + '\'' +
            ", last_liquidity_ind='" + last_liquidity_ind + '\'' +
            ", leaves_qty=" + leaves_qty +
            ", nth_fill=" + nth_fill +
            ", order_id='" + order_id + '\'' +
            ", order_link_id='" + order_link_id + '\'' +
            ", order_price='" + order_price + '\'' +
            ", order_qty=" + order_qty +
            ", order_type='" + order_type + '\'' +
            ", side='" + side + '\'' +
            ", symbol='" + symbol + '\'' +
            ", user_id=" + user_id +
            ", trade_time_ms=" + trade_time_ms +
            '}';
  }
}
