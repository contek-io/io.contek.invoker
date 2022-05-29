package io.contek.invoker.deribit.api.common;

public class _Position {

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

  @Override
  public String toString() {
    return "_Position{" +
            "average_price=" + average_price +
            ", average_price_usd=" + average_price_usd +
            ", delta=" + delta +
            ", direction='" + direction + '\'' +
            ", estimated_liquidation_price=" + estimated_liquidation_price +
            ", floating_profit_loss=" + floating_profit_loss +
            ", floating_profit_loss_usd=" + floating_profit_loss_usd +
            ", gamma=" + gamma +
            ", index_price=" + index_price +
            ", initial_margin=" + initial_margin +
            ", instrument_name='" + instrument_name + '\'' +
            ", kind='" + kind + '\'' +
            ", leverage=" + leverage +
            ", maintenance_margin=" + maintenance_margin +
            ", mark_price=" + mark_price +
            ", open_orders_margin=" + open_orders_margin +
            ", realized_funding=" + realized_funding +
            ", realized_profit_loss=" + realized_profit_loss +
            ", settlement_price=" + settlement_price +
            ", size=" + size +
            ", size_currency='" + size_currency + '\'' +
            ", theta=" + theta +
            ", total_profit_loss=" + total_profit_loss +
            ", vega=" + vega +
            '}';
  }
}
