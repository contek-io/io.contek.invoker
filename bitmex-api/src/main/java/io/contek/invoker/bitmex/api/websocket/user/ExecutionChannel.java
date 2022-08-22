package io.contek.invoker.bitmex.api.websocket.user;

import io.contek.invoker.bitmex.api.common._Execution;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannel;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannelId;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketTableDataMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class ExecutionChannel
    extends WebSocketChannel<ExecutionChannel.Id, ExecutionChannel.Message> {

  ExecutionChannel() {
    super(Id.INSTANCE);
  }

  @Override
  public Class<Message> getMessageType() {
    return ExecutionChannel.Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<ExecutionChannel.Message> {

    private static final Id INSTANCE = new Id();

    private Id() {
      super("execution");
    }

    @Override
    public boolean accepts(ExecutionChannel.Message message) {
      return true;
    }
  }

  @NotThreadSafe
  public static final class Data extends _Execution {}

  @NotThreadSafe
  public static final class Message extends WebSocketTableDataMessage<Data> {}
}
