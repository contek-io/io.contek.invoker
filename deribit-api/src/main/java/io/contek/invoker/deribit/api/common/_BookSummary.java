package io.contek.invoker.deribit.api.common;

public class _BookSummary {

  public String instrument_name;
  public String base_currency;
  public String quote_currency;
  public Double ask_price;
  public Double bid_price;
  public Double mid_price;
  public Double mark_price;
  public Double volume;
  public Double volume_usd;
  public Double price_change;
  public Double open_interest;
  public Double low;
  public Double last;
  public Double high;
  public Double estimated_delivery_price;
  public Long creation_timestamp;

  @Override
  public String toString() {
    return "_BookSummary{" +
            "instrument_name='" + instrument_name + '\'' +
            ", base_currency='" + base_currency + '\'' +
            ", quote_currency='" + quote_currency + '\'' +
            ", ask_price=" + ask_price +
            ", bid_price=" + bid_price +
            ", mid_price=" + mid_price +
            ", mark_price=" + mark_price +
            ", volume=" + volume +
            ", volume_usd=" + volume_usd +
            ", price_change=" + price_change +
            ", open_interest=" + open_interest +
            ", low=" + low +
            ", last=" + last +
            ", high=" + high +
            ", estimated_delivery_price=" + estimated_delivery_price +
            ", creation_timestamp=" + creation_timestamp +
            '}';
  }
}
