package io.contek.invoker.binancefutures.api.websocket.user;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

public class LeverageUpdateEvent extends AnyWebSocketMessage {
  public String e; // event type
  public Long E; // event time
  public Long T; // transaction time
  public AccountConfig ac;

  public static class AccountConfig {
    public String s; // symbol
    public Integer l; // leverage
  }
}
