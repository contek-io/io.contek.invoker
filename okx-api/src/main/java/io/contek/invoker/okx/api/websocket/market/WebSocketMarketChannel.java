package io.contek.invoker.okx.api.websocket.market;

import io.contek.invoker.okx.api.websocket.WebSocketChannel;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelPushData;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class WebSocketMarketChannel<
        Id extends WebSocketMarketChannelId<Message>, Message extends WebSocketChannelPushData<?>>
    extends WebSocketChannel<Id, Message> {

  protected WebSocketMarketChannel(Id id) {
    super(id);
  }
}
