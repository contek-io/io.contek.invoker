package io.contek.invoker.binancefutures.api.websocket.user;

import java.math.BigDecimal;

public final class BalanceUpdate {
  public String a; // asset
  public BigDecimal wb; // wallet balance
  public BigDecimal cw; // cross wallet balance
}
