package io.contek.invoker.deribit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _UserTrade {

  public Long trade_seq;
  public String trade_id;
  public Long timestamp;
  public Integer tick_direction;
  public String state;
  public Boolean self_trade;
  public Boolean reduce_only;
  public Double price;
  public Boolean post_only;
  public String order_type;
  public String order_id;
  public String matching_id;
  public Double mark_price;
  public String liquidity;
  public String instrument_name;
  public Double index_price;
  public String fee_currency;
  public Double fee;
  public String direction;
  public Double amount;
  public String advanced;
  public String block_trade_id;
  public Double iv;
  public String label;
  public String liquidation;
  public Double profit_loss;
  public Double stop_price;
  public String trigger;
  public Boolean mmp;
}
