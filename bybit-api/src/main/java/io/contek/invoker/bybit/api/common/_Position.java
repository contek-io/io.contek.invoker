package io.contek.invoker.bybit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Position {

  public long id;
  public long user_id;
  public long risk_id;
  public String symbol;
  public String side;
  public int size;
  public double position_value;
  public double entry_price;
  public boolean is_isolated;
  public int auto_add_margin;
  public double leverage;
  public double effective_leverage;
  public double position_margin;
  public double liq_price;
  public double bust_price;
  public double occ_closing_fee;
  public double occ_funding_fee;
  public double take_profit;
  public double stop_loss;
  public double trailing_stop;
  public String position_status;
  public int deleverage_indicator;
  public String oc_calc_data;
  public double order_margin;
  public double wallet_balance;
  public double realised_pnl;
  public double unrealised_pnl;
  public double cum_realised_pnl;
  public long cross_seq;
  public long position_seq;
  public String created_at;
  public String updated_at;

  @Override
  public String toString() {
    return "_Position{" +
            "id=" + id +
            ", user_id=" + user_id +
            ", risk_id=" + risk_id +
            ", symbol='" + symbol + '\'' +
            ", side='" + side + '\'' +
            ", size=" + size +
            ", position_value=" + position_value +
            ", entry_price=" + entry_price +
            ", is_isolated=" + is_isolated +
            ", auto_add_margin=" + auto_add_margin +
            ", leverage=" + leverage +
            ", effective_leverage=" + effective_leverage +
            ", position_margin=" + position_margin +
            ", liq_price=" + liq_price +
            ", bust_price=" + bust_price +
            ", occ_closing_fee=" + occ_closing_fee +
            ", occ_funding_fee=" + occ_funding_fee +
            ", take_profit=" + take_profit +
            ", stop_loss=" + stop_loss +
            ", trailing_stop=" + trailing_stop +
            ", position_status='" + position_status + '\'' +
            ", deleverage_indicator=" + deleverage_indicator +
            ", oc_calc_data='" + oc_calc_data + '\'' +
            ", order_margin=" + order_margin +
            ", wallet_balance=" + wallet_balance +
            ", realised_pnl=" + realised_pnl +
            ", unrealised_pnl=" + unrealised_pnl +
            ", cum_realised_pnl=" + cum_realised_pnl +
            ", cross_seq=" + cross_seq +
            ", position_seq=" + position_seq +
            ", created_at='" + created_at + '\'' +
            ", updated_at='" + updated_at + '\'' +
            '}';
  }
}
