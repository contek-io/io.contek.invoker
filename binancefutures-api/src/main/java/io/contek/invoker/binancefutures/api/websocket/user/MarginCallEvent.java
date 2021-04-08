package io.contek.invoker.binancefutures.api.websocket.user;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import java.util.List;

public final class MarginCallEvent extends AnyWebSocketMessage {
  public String e; // event type
  public Long E; // event time
  public Double cw; // transaction time
  List<PositionForMarginCall> p;

  public static class PositionForMarginCall {
    public String s; // symbol
    public String ps; // position side
    public String mt; // margin type
    public Long iw; // isolated wallet
    public Double mp; // mark price
    public Double up; // unrealized PnL
    public Double mm; // maintenance margin required
  }
}
