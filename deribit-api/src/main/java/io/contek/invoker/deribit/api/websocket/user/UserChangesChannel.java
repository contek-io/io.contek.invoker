package io.contek.invoker.deribit.api.websocket.user;

import io.contek.invoker.deribit.api.common._Order;
import io.contek.invoker.deribit.api.common._Position;
import io.contek.invoker.deribit.api.common._Trade;
import io.contek.invoker.deribit.api.websocket.WebSocketChannelId;
import io.contek.invoker.deribit.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.deribit.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import java.util.List;

import static io.contek.invoker.deribit.api.websocket.common.constants.WebSocketChannelKeys._user_changes;
import static java.lang.String.format;

@ThreadSafe
public final class UserChangesChannel
    extends UserWebSocketChannel<UserChangesChannel.Id, UserChangesChannel.Message> {

  UserChangesChannel(Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<UserChangesChannel.Message> getMessageType() {
    return UserChangesChannel.Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<UserChangesChannel.Message> {

    private Id(String value) {
      super(value);
    }

    public static Id of(String instrumentName) {
      return new Id(format("%s.%s.raw", _user_changes, instrumentName));
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<Changes> {}

  @NotThreadSafe
  public static final class Changes {

    public List<_Order> orders;
    public List<_Position> positions;
    public List<_Trade> trades;
  }
}
