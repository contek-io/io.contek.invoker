package io.contek.invoker.binancefutures.api.websocket.user;

import io.contek.invoker.binancefutures.api.websocket.common.WebSocketEventData;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public final class AccountUpdateEvent extends WebSocketEventData {

  public Long T; // transaction
  public UpdateData a; // account update

  @NotThreadSafe
  public static final class UpdateData {

    public String m; // event reason type
    public List<BalanceUpdate> B; // balance updates
    public List<PositionUpdate> P; // position updates
  }
}
