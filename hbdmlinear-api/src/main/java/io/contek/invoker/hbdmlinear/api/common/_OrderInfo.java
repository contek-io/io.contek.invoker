package io.contek.invoker.hbdmlinear.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _OrderInfo {

  public String symbol;
  public String contract_code;
  public double volume;
  public double price;
  public String order_price_type;
  public int order_type;
  public String direction;
  public String offset;
  public int lever_rate;
  public long order_id;
  public long client_order_id;
  public long created_at;
  public double trade_volume;
  public double trade_turnover;
  public double fee;
  public double trade_avg_price;
  public double margin_frozen;
  public double profit;
  public int status;
  public String order_source;
  public String order_id_str;
  public String fee_asset;
  public String liquidation_type;
  public long canceled_at;
  public String margin_asset;
  public String margin_mode;
  public String margin_account;
  public int is_tpsl;
  public double real_profit;
}
