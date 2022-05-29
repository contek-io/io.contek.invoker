package io.contek.invoker.okx.api.websocket.user;

import io.contek.invoker.okx.api.websocket.WebSocketChannel;
import io.contek.invoker.okx.api.websocket.WebSocketChannelId;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelPushData;

public abstract class WebSocketUserChannel<
        Id extends WebSocketChannelId<Message>, Message extends WebSocketChannelPushData<?>>
    extends WebSocketChannel<Id, Message> {

  protected WebSocketUserChannel(Id id) {
    super(id);
  }
}
