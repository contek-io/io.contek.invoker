package io.contek.invoker.hbdminverse.api.common;

public class _OrderInfo {

  public String symbol;
  public String contract_code;
  public double volume;
  public Double price;
  public String order_price_type;
  public int order_type;
  public String direction;
  public String offset;
  public int lever_rate;
  public long order_id;
  public Long client_order_id;
  public long created_at;
  public double trade_volume;
  public double trade_turnover;
  public double fee;
  public Double trade_avg_price;
  public double margin_frozen;
  public double profit;
  public int status;
  public String order_source;
  public String order_id_str;
  public String fee_asset;
  public String liquidation_type;
  public long canceled_at;
  public double real_profit;
  public int is_tpsl;

  @Override
  public String toString() {
    return "_OrderInfo{" +
            "symbol='" + symbol + '\'' +
            ", contract_code='" + contract_code + '\'' +
            ", volume=" + volume +
            ", price=" + price +
            ", order_price_type='" + order_price_type + '\'' +
            ", order_type=" + order_type +
            ", direction='" + direction + '\'' +
            ", offset='" + offset + '\'' +
            ", lever_rate=" + lever_rate +
            ", order_id=" + order_id +
            ", client_order_id=" + client_order_id +
            ", created_at=" + created_at +
            ", trade_volume=" + trade_volume +
            ", trade_turnover=" + trade_turnover +
            ", fee=" + fee +
            ", trade_avg_price=" + trade_avg_price +
            ", margin_frozen=" + margin_frozen +
            ", profit=" + profit +
            ", status=" + status +
            ", order_source='" + order_source + '\'' +
            ", order_id_str='" + order_id_str + '\'' +
            ", fee_asset='" + fee_asset + '\'' +
            ", liquidation_type='" + liquidation_type + '\'' +
            ", canceled_at=" + canceled_at +
            ", real_profit=" + real_profit +
            ", is_tpsl=" + is_tpsl +
            '}';
  }
}
