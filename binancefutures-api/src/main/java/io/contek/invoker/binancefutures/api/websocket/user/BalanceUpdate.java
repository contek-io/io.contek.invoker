package io.contek.invoker.binancefutures.api.websocket.user;

import javax.annotation.concurrent.NotThreadSafe;
import java.math.BigDecimal;

@NotThreadSafe
public final class BalanceUpdate {

  public String a; // asset
  public BigDecimal wb; // wallet balance
  public BigDecimal cw; // cross wallet balance
}
