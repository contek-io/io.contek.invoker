package io.contek.invoker.hbdmlinear.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
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
}
