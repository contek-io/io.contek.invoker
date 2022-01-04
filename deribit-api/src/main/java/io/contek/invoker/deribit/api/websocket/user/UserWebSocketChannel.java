package io.contek.invoker.deribit.api.websocket.user;

import io.contek.invoker.deribit.api.websocket.WebSocketChannel;
import io.contek.invoker.deribit.api.websocket.WebSocketChannelId;
import io.contek.invoker.deribit.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.deribit.api.websocket.common.WebSocketSingleChannelMessage;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class UserWebSocketChannel<
        Id extends WebSocketChannelId<Message>, Message extends WebSocketSingleChannelMessage<?>>
    extends WebSocketChannel<Id, Message> {

  UserWebSocketChannel(Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, "private", requestIdGenerator);
  }
}
