package io.contek.invoker.okx.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _LendingInfo {
  public String coin;
  public double lendable;
  public double locked;
  public double minRate;
  public double offered;
}
