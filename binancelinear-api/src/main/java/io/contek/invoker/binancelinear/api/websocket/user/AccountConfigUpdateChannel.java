package io.contek.invoker.binancelinear.api.websocket.user;

import io.contek.invoker.binancelinear.api.websocket.common.WebSocketEventData;
import io.contek.invoker.binancelinear.api.websocket.user.constants.UserEventTypeKeys;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class AccountConfigUpdateChannel
    extends UserWebSocketChannel<AccountConfigUpdateChannel.Data> {

  AccountConfigUpdateChannel() {
    super(Id.INSTANCE);
  }

  @Override
  public Class<Data> getMessageType() {
    return Data.class;
  }

  @Immutable
  public static final class Id extends UserWebSocketChannelId<Data> {

    private static final Id INSTANCE = new Id();

    private Id() {
      super(UserEventTypeKeys._ACCOUNT_CONFIG_UPDATE);
    }
  }

  @NotThreadSafe
  public static final class Data extends WebSocketEventData {

    public Long T; // transaction time
    public AccountConfig ac;

    @NotThreadSafe
    public static class AccountConfig {

      public String s; // symbol
      public Integer l; // leverage
    }
  }
}
