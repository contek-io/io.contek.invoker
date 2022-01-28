package io.contek.invoker.okx.api.websocket.user;

import io.contek.invoker.okx.api.common._Position;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelPushData;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketChannelKeys._positions;

@ThreadSafe
public final class PositionsChannel
    extends WebSocketUserChannel<PositionsChannel.Id, PositionsChannel.Message> {

  PositionsChannel(PositionsChannel.Id id) {
    super(id);
  }

  @Override
  public Class<Message> getMessageType() {
    return PositionsChannel.Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketUserChannelId<PositionsChannel.Message> {

    private Id(String instId) {
      super(_positions, instId);
    }

    public static PositionsChannel.Id of(String instId) {
      return new PositionsChannel.Id(instId);
    }
  }

  @NotThreadSafe
  public static final class Data extends _Position {}

  @NotThreadSafe
  public static final class Message extends WebSocketChannelPushData<Data> {}
}
