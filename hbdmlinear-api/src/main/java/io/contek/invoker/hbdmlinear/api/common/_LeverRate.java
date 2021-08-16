package io.contek.invoker.hbdmlinear.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _LeverRate {

  public String contract_code;
  public int lever_rate;
  public String margin_mode;

  @Override
  public String toString() {
    return "_LeverRate{" +
            "contract_code='" + contract_code + '\'' +
            ", lever_rate=" + lever_rate +
            ", margin_mode='" + margin_mode + '\'' +
            '}';
  }
}
