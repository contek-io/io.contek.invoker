package io.contek.invoker.hbdminverse.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
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
}
