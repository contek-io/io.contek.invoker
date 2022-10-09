package io.contek.invoker.okx.api.websocket.user;

import io.contek.invoker.okx.api.websocket.WebSocketChannel;
import io.contek.invoker.okx.api.websocket.WebSocketChannelId;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelPushData;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class WebSocketUserChannel<Message extends WebSocketChannelPushData<Data>, Data>
    extends WebSocketChannel<Message, Data> {

  protected WebSocketUserChannel(WebSocketChannelId<Message> id) {
    super(id);
  }
}
