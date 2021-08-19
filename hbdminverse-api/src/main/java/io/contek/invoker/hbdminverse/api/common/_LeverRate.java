package io.contek.invoker.hbdminverse.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _LeverRate {

  public String contract_code;
  public int lever_rate;

  @Override
  public String toString() {
    return "_LeverRate{" +
            "contract_code='" + contract_code + '\'' +
            ", lever_rate=" + lever_rate +
            '}';
  }
}
