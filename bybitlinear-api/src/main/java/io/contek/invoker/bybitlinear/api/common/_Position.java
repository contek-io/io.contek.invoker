package io.contek.invoker.bybitlinear.api.common;

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
}
