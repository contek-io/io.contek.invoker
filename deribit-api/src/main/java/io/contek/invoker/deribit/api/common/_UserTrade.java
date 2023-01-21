package io.contek.invoker.deribit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _UserTrade {

  public Long amount;
  public String block_trade_id;
  public String direction;
  public Double fee;
  public String fee_currency;
  public Double index_price;
  public String instrument_name;
  public Double iv;
  public String label;
  public String liquidation;
  public String liquidity;
  public Double mark_price;
  public String matching_id;
  public String order_id;
  public String order_type;
  public String post_only;
  public Double price;
  public Double profit_loss;
  public Boolean reduce_only;
  public Double stop_price;
  public String trigger;
  public String advanced;
  public Boolean mmp;
}
