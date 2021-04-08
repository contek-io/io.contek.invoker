package io.contek.invoker.binancefutures.api.websocket.user;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import java.util.List;

public final class AccountUpdateEvent extends AnyWebSocketMessage {
  public String e; // event type
  public Long E; // event time
  public Long T; // transaction

  public static final class UpdateData {
    public String m; // event reason type
    public List<BalanceUpdate> B; // balance updates
    public List<PositionUpdate> P; // position updates
  }
}
