package io.contek.invoker.deribit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
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
}
