package io.contek.invoker.binancefutures.api.websocket.user;

import javax.annotation.concurrent.NotThreadSafe;
import java.math.BigDecimal;

@NotThreadSafe
public final class PositionUpdate {

  public String s; // symbol
  public BigDecimal pa; // position amount
  public BigDecimal ep; // entry price
  public BigDecimal cr; // pre-fee accumulated realized
  public BigDecimal up; // unrealized PnL
  public String mt; // margin type
  public BigDecimal iw; // isolated wallet
  public String ps; // position side
}
