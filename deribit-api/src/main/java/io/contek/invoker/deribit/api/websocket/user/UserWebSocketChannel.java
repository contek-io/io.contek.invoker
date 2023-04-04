package io.contek.invoker.deribit.api.websocket.user;

import io.contek.invoker.deribit.api.websocket.WebSocketChannel;
import io.contek.invoker.deribit.api.websocket.WebSocketChannelId;
import io.contek.invoker.deribit.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.deribit.api.websocket.common.WebSocketSingleChannelMessage;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class UserWebSocketChannel<
        Message extends WebSocketSingleChannelMessage<Data>, Data>
    extends WebSocketChannel<Message, Data> {

  UserWebSocketChannel(
      WebSocketChannelId<Message> id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, "private", requestIdGenerator);
  }
}
