package io.contek.invoker.deribit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Position {

  public Double average_price;
  public Double average_price_usd;
  public Double delta;
  public String direction;
  public Double estimated_liquidation_price;
  public Double floating_profit_loss;
  public Double floating_profit_loss_usd;
  public Double gamma;
  public Double index_price;
  public Double initial_margin;
  public String instrument_name;
  public String kind;
  public Integer leverage;
  public Double maintenance_margin;
  public Double mark_price;
  public Double open_orders_margin;
  public Double realized_funding;
  public Double realized_profit_loss;
  public Double settlement_price;
  public Integer size;
  public String size_currency;
  public Double theta;
  public Double total_profit_loss;
  public Double vega;
}
