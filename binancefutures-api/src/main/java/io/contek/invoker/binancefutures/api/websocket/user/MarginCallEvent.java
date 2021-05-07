package io.contek.invoker.binancefutures.api.websocket.user;

import io.contek.invoker.binancefutures.api.websocket.common.WebSocketEventData;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public final class MarginCallEvent extends WebSocketEventData {

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
