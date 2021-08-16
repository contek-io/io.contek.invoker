package io.contek.invoker.binancespot.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _AccountBalance {

  public String asset;
  public String free;
  public String locked;

  @Override
  public String toString() {
    return "_AccountBalance{" +
            "asset='" + asset + '\'' +
            ", free='" + free + '\'' +
            ", locked='" + locked + '\'' +
            '}';
  }
}
