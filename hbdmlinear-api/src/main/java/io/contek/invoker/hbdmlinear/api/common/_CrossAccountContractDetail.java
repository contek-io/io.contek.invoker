package io.contek.invoker.hbdmlinear.api.common;

public class _CrossAccountContractDetail {

  public String symbol;
  public String contract_code;
  public double margin_position;
  public double margin_frozen;
  public double margin_available;
  public double profit_unreal;
  public Double liquidation_price;
  public int lever_rate;
  public double adjust_factor;

  @Override
  public String toString() {
    return "_CrossAccountContractDetail{" +
            "symbol='" + symbol + '\'' +
            ", contract_code='" + contract_code + '\'' +
            ", margin_position=" + margin_position +
            ", margin_frozen=" + margin_frozen +
            ", margin_available=" + margin_available +
            ", profit_unreal=" + profit_unreal +
            ", liquidation_price=" + liquidation_price +
            ", lever_rate=" + lever_rate +
            ", adjust_factor=" + adjust_factor +
            '}';
  }
}
