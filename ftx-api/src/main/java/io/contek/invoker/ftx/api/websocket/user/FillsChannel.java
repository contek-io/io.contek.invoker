package io.contek.invoker.ftx.api.websocket.user;

import io.contek.invoker.ftx.api.common._Fill;
import io.contek.invoker.ftx.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys._fills;

@ThreadSafe
public final class FillsChannel
    extends WebSocketUserChannel<FillsChannel.Id, FillsChannel.Message> {

  FillsChannel() {
    super(Id.INSTANCE);
  }

  @Override
  public Class<Message> getMessageType() {
    return FillsChannel.Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketUserChannelId<FillsChannel.Message> {

    private static final Id INSTANCE = new Id();

    private Id() {
      super(_fills);
    }
  }

  @NotThreadSafe
  public static final class Data extends _Fill {}

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<FillsChannel.Data> {}
}
