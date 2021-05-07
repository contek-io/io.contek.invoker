package io.contek.invoker.binancefutures.api.websocket.user;

import io.contek.invoker.binancefutures.api.websocket.common.WebSocketEventData;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class LeverageUpdateEvent extends WebSocketEventData {

  public Long T; // transaction time
  public AccountConfig ac;

  public static class AccountConfig {
    public String s; // symbol
    public Integer l; // leverage
  }
}
