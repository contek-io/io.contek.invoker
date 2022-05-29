package io.contek.invoker.bitmex.api.websocket.user;

import io.contek.invoker.bitmex.api.common._Position;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannel;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannelId;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketTableDataMessage;

public final class PositionChannel
    extends WebSocketChannel<PositionChannel.Id, PositionChannel.Message> {

  PositionChannel() {
    super(Id.INSTANCE);
  }

  @Override
  public Class<Message> getMessageType() {
    return PositionChannel.Message.class;
  }

  public static final class Id extends WebSocketChannelId<PositionChannel.Message> {

    private static final Id INSTANCE = new Id();

    private Id() {
      super("position");
    }

    @Override
    public boolean accepts(PositionChannel.Message message) {
      return true;
    }
  }

  public static final class Data extends _Position {}

  public static final class Message extends WebSocketTableDataMessage<Data> {}
}
