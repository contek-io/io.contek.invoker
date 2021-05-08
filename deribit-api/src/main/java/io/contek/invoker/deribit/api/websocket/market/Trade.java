package io.contek.invoker.deribit.api.websocket.market;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class Trade {

  public long amount;
  public String block_trade_id;
  public String direction;
  public double index_price;
  public String instrument_name;
  public double iv;
  public String liquidation;
  public double mark_price;
  public double price;
  public int tick_direction;
  public long timestamp;
  public String trade_id;
  public long trade_seq;
}
