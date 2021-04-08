package io.contek.invoker.binancefutures.api.websocket.user;

import java.math.BigDecimal;

public class PositionUpdate {
  public String s; // symbol
  public BigDecimal pa; // position amount
  public BigDecimal ep; // entry price
  public BigDecimal cr; // pre-fee accumulated realized
  public BigDecimal up; // unrealized PnL
  public String mt; // margin type
  public BigDecimal iw; // isolated wallet
  public String ps; // position side
}
