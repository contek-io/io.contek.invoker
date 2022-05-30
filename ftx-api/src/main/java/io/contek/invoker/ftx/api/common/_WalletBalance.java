package io.contek.invoker.ftx.api.common;

import java.io.Serializable;

public class _WalletBalance implements Serializable {

  public String coin;
  public Double free;
  public Double total;

  @Override
  public String toString() {
    return "_WalletBalance{" +
            "coin='" + coin + '\'' +
            ", free=" + free +
            ", total=" + total +
            '}';
  }
}
