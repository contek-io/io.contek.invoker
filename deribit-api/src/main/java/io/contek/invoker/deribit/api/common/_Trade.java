package io.contek.invoker.deribit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Trade {

  public long amount;
  public String block_trade_id;
  public String direction;
  public double fee;
  public String fee_currency;
  public double index_price;
  public String instrument_name;
  public double iv;
  public String label;
  public String liquidation;
  public String liquidity;
  public double mark_price;
  public String matching_id;
  public String order_id;
  public String order_type;
  public String post_only;
  public double price;
  public double profit_loss;
  public boolean reduce_only;
  public double stop_price;
  public String trigger;
  public String advanced;
  public boolean mmp;

}
