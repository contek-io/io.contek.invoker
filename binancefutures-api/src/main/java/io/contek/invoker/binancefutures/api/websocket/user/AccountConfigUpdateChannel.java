package io.contek.invoker.binancefutures.api.websocket.user;

import io.contek.invoker.binancefutures.api.websocket.common.WebSocketEventMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancefutures.api.websocket.user.constants.UserEventTypeKeys._ACCOUNT_CONFIG_UPDATE;

@ThreadSafe
public final class AccountConfigUpdateChannel
    extends UserWebSocketChannel<
        AccountConfigUpdateChannel.Id, AccountConfigUpdateChannel.Message> {

  AccountConfigUpdateChannel() {
    super(Id.INSTANCE);
  }

  @Override
  protected Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends UserWebSocketChannelId<AccountConfigUpdateChannel.Message> {

    private static final Id INSTANCE = new Id();

    private Id() {
      super(_ACCOUNT_CONFIG_UPDATE);
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
