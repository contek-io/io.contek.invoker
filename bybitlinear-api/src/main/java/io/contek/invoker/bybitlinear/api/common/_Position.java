package io.contek.invoker.bybitlinear.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Position {

  public Long user_id;
  public String symbol;
  public String side;
  public Double size;
  public Double position_value;
  public Double entry_price;
  public Double liq_price;
  public Double bust_price;
  public Double leverage;
  public Boolean is_isolated;
  public Integer auto_add_margin;
  public Double position_margin;
  public Double occ_closing_fee;
  public Double realised_pnl;
  public Double cum_realised_pnl;
  public Double free_qty;
  public String tp_sl_mode;
  public Double unrealised_pnl;
  public Integer deleverage_indicator;
  public Long risk_id;
  public Double stop_loss;
  public Double take_profit;
  public Double trailing_stop;
}
