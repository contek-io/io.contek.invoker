package io.contek.invoker.binancefutures.api.websocket.user;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class UserDataStreamExpiredEvent extends AnyWebSocketMessage {
  public String e;
  public String E;
}
