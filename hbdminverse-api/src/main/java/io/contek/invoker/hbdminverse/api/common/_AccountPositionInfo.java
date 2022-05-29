package io.contek.invoker.hbdminverse.api.common;

import java.util.List;

public class _AccountPositionInfo {

  public String symbol;
  public String contract_code;
  public double margin_balance;
  public double margin_position;
  public double margin_frozen;
  public double margin_available;
  public double profit_real;
  public double profit_unreal;
  public Double risk_rate;
  public double withdraw_available;
  public Double liquidation_price;
  public double lever_rate;
  public double adjust_factor;
  public double margin_static;
  public List<_PositionInfo> positions;

  @Override
  public String toString() {
    return "_AccountPositionInfo{" +
            "symbol='" + symbol + '\'' +
            ", contract_code='" + contract_code + '\'' +
            ", margin_balance=" + margin_balance +
            ", margin_position=" + margin_position +
            ", margin_frozen=" + margin_frozen +
            ", margin_available=" + margin_available +
            ", profit_real=" + profit_real +
            ", profit_unreal=" + profit_unreal +
            ", risk_rate=" + risk_rate +
            ", withdraw_available=" + withdraw_available +
            ", liquidation_price=" + liquidation_price +
            ", lever_rate=" + lever_rate +
            ", adjust_factor=" + adjust_factor +
            ", margin_static=" + margin_static +
            ", positions=" + positions +
            '}';
  }
}
