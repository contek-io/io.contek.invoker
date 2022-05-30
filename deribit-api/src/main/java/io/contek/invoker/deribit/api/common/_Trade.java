package io.contek.invoker.deribit.api.common;

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

  @Override
  public String toString() {
    return "_Trade{" +
            "amount=" + amount +
            ", block_trade_id='" + block_trade_id + '\'' +
            ", direction='" + direction + '\'' +
            ", fee=" + fee +
            ", fee_currency='" + fee_currency + '\'' +
            ", index_price=" + index_price +
            ", instrument_name='" + instrument_name + '\'' +
            ", iv=" + iv +
            ", label='" + label + '\'' +
            ", liquidation='" + liquidation + '\'' +
            ", liquidity='" + liquidity + '\'' +
            ", mark_price=" + mark_price +
            ", matching_id='" + matching_id + '\'' +
            ", order_id='" + order_id + '\'' +
            ", order_type='" + order_type + '\'' +
            ", post_only='" + post_only + '\'' +
            ", price=" + price +
            ", profit_loss=" + profit_loss +
            ", reduce_only=" + reduce_only +
            ", stop_price=" + stop_price +
            ", trigger='" + trigger + '\'' +
            ", advanced='" + advanced + '\'' +
            ", mmp=" + mmp +
            '}';
  }
}
