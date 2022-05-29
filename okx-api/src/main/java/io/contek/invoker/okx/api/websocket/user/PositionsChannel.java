package io.contek.invoker.okx.api.websocket.user;

import io.contek.invoker.okx.api.common._Position;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelPushData;

import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketChannelKeys._positions;

public final class PositionsChannel
    extends WebSocketUserChannel<PositionsChannel.Id, PositionsChannel.Message> {

  PositionsChannel(PositionsChannel.Id id) {
    super(id);
  }

  @Override
  public Class<Message> getMessageType() {
    return PositionsChannel.Message.class;
  }

  public static final class Id extends WebSocketUserChannelId<PositionsChannel.Message> {

    private Id(String instId) {
      super(_positions, instId);
    }

    public static PositionsChannel.Id of(String instId) {
      return new PositionsChannel.Id(instId);
    }
  }

  public static final class Data extends _Position {}

  public static final class Message extends WebSocketChannelPushData<Data> {}
}
