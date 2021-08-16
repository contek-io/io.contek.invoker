package io.contek.invoker.hbdmlinear.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class _CrossAccountPositionInfo {

  public String margin_mode;
  public String margin_account;
  public String margin_asset;
  public double margin_balance;
  public double margin_static;
  public double margin_position;
  public double margin_frozen;
  public double profit_real;
  public double profit_unreal;
  public double withdraw_available;
  public double risk_rate;
  public List<_PositionInfo> positions;
  public List<_CrossAccountContractDetail> contract_detail;

  @Override
  public String toString() {
    return "_CrossAccountPositionInfo{" +
            "margin_mode='" + margin_mode + '\'' +
            ", margin_account='" + margin_account + '\'' +
            ", margin_asset='" + margin_asset + '\'' +
            ", margin_balance=" + margin_balance +
            ", margin_static=" + margin_static +
            ", margin_position=" + margin_position +
            ", margin_frozen=" + margin_frozen +
            ", profit_real=" + profit_real +
            ", profit_unreal=" + profit_unreal +
            ", withdraw_available=" + withdraw_available +
            ", risk_rate=" + risk_rate +
            ", positions=" + positions +
            ", contract_detail=" + contract_detail +
            '}';
  }
}
