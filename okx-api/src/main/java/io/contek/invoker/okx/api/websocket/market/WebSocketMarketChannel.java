package io.contek.invoker.okx.api.websocket.market;

import io.contek.invoker.okx.api.websocket.WebSocketChannel;
import io.contek.invoker.okx.api.websocket.WebSocketChannelId;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelPushData;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class WebSocketMarketChannel<Message extends WebSocketChannelPushData<Data>, Data>
    extends WebSocketChannel<Message, Data> {

  protected WebSocketMarketChannel(WebSocketChannelId<Message> id) {
    super(id);
  }
}
