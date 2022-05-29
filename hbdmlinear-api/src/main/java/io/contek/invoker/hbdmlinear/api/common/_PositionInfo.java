package io.contek.invoker.hbdmlinear.api.common;

public class _PositionInfo {

  public String symbol;
  public String contract_code;
  public double volume;
  public double available;
  public double frozen;
  public Double cost_open;
  public Double cost_hold;
  public double profit_unreal;
  public double profit_rate;
  public int lever_rate;
  public double position_margin;
  public String direction;
  public double profit;
  public Double last_price;
  public String margin_asset;
  public String margin_mode;
  public String margin_account;

  @Override
  public String toString() {
    return "_PositionInfo{" +
            "symbol='" + symbol + '\'' +
            ", contract_code='" + contract_code + '\'' +
            ", volume=" + volume +
            ", available=" + available +
            ", frozen=" + frozen +
            ", cost_open=" + cost_open +
            ", cost_hold=" + cost_hold +
            ", profit_unreal=" + profit_unreal +
            ", profit_rate=" + profit_rate +
            ", lever_rate=" + lever_rate +
            ", position_margin=" + position_margin +
            ", direction='" + direction + '\'' +
            ", profit=" + profit +
            ", last_price=" + last_price +
            ", margin_asset='" + margin_asset + '\'' +
            ", margin_mode='" + margin_mode + '\'' +
            ", margin_account='" + margin_account + '\'' +
            '}';
  }
}
