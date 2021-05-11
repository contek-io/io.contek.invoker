package io.contek.invoker.binancefutures.api.websocket.user;

import io.contek.invoker.binancefutures.api.websocket.common.WebSocketEventMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancefutures.api.websocket.user.constants.UserEventTypeKeys._ACCOUNT_UPDATE;

@ThreadSafe
public final class LeverageUpdateChannel
    extends UserWebSocketChannel<LeverageUpdateChannel.Message> {

  @Override
  protected BaseWebSocketChannelId getId() {
    return "LeverageUpdateChannel";
  }

  @Override
  protected Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends UserWebSocketChannelId<AccountUpdateChannel.Message> {

    private static final AccountUpdateChannel.Id INSTANCE = new AccountUpdateChannel.Id();

    private Id() {
      super(_ACCOUNT_UPDATE);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketEventMessage {

    public Long T; // transaction time
    public AccountConfig ac;

    @NotThreadSafe
    public static class AccountConfig {

      public String s; // symbol
      public Integer l; // leverage
    }
  }
}
