package io.contek.invoker.bitmex.api.websocket.user;

import io.contek.invoker.bitmex.api.common._Position;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannel;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannelId;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketTableDataMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class PositionChannel extends WebSocketChannel<PositionChannel.Message, _Position> {

  PositionChannel() {
    super(Id.INSTANCE);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<Message> {

    private static final Id INSTANCE = new Id();

    private Id() {
      super("position");
    }

    @Override
    public boolean accepts(Message message) {
      return true;
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTableDataMessage<_Position> {}
}
