package io.contek.invoker.deribit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class _Position {
  public double average_price;
  public double average_price_usd;
  public double delta;
  public String direction;
  public double estimated_liquidation_price;
  public double floating_profit_loss;
  public double floating_profit_loss_usd;
  public double gamma;
  public double index_price;
  public double initial_margin;
  public String instrument_name;
  public String kind;
  public int leverage;
  public double maintenance_margin;
  public double mark_price;
  public double open_orders_margin;
  public double realized_funding;
  public double realized_profit_loss;
  public double settlement_price;
  public int size;
  public String size_currency;
  public double theta;
  public double total_profit_loss;
  public double vega;
}
