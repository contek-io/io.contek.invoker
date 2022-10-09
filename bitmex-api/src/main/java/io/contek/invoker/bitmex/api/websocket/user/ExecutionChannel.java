package io.contek.invoker.bitmex.api.websocket.user;

import io.contek.invoker.bitmex.api.common._Execution;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannel;
import io.contek.invoker.bitmex.api.websocket.WebSocketChannelId;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketTableDataMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class ExecutionChannel extends WebSocketChannel<ExecutionChannel.Message, _Execution> {

  ExecutionChannel() {
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
      super("execution");
    }

    @Override
    public boolean accepts(Message message) {
      return true;
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTableDataMessage<_Execution> {}
}
