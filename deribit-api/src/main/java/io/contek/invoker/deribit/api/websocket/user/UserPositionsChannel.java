package io.contek.invoker.deribit.api.websocket.user;

import io.contek.invoker.deribit.api.common._Position;
import io.contek.invoker.deribit.api.websocket.WebSocketChannelId;
import io.contek.invoker.deribit.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.deribit.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static java.lang.String.format;

@ThreadSafe
public final class UserPositionsChannel
    extends UserWebSocketChannel<UserPositionsChannel.Id, UserPositionsChannel.Message> {

  UserPositionsChannel(Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<UserPositionsChannel.Message> getMessageType() {
    return UserPositionsChannel.Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<UserPositionsChannel.Message> {

    private Id(String value) {
      super(value);
    }

    public static Id of(String instrumentName) {
      return new Id(format("user.positions.%s.raw", instrumentName));
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<Data> {}

  @NotThreadSafe
  public static final class Data extends _Position {}
}
