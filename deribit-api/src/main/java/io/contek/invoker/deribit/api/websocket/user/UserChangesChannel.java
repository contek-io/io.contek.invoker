package io.contek.invoker.deribit.api.websocket.user;

import io.contek.invoker.deribit.api.common._Order;
import io.contek.invoker.deribit.api.common._Position;
import io.contek.invoker.deribit.api.common._Trade;
import io.contek.invoker.deribit.api.websocket.WebSocketChannelId;
import io.contek.invoker.deribit.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.deribit.api.websocket.common.WebSocketSingleChannelMessage;

import java.util.List;

import static java.lang.String.format;

public final class UserChangesChannel
    extends UserWebSocketChannel<UserChangesChannel.Id, UserChangesChannel.Message> {

  UserChangesChannel(Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<UserChangesChannel.Message> getMessageType() {
    return UserChangesChannel.Message.class;
  }

  public static final class Id extends WebSocketChannelId<UserChangesChannel.Message> {

    private Id(String value) {
      super(value);
    }

    public static Id of(String instrumentName, String interval) {
      return new Id(format("user.changes.%s.%s", instrumentName, interval));
    }
  }

  public static final class Message extends WebSocketSingleChannelMessage<Data> {}

  public static final class Data {

    public String instrument_name;
    public List<_Order> orders;
    public List<_Position> positions;
    public List<_Trade> trades;
  }
}
