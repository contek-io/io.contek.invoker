package io.contek.invoker.ftx.api.websocket;

import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.ftx.api.websocket.common.WebSocketInboundMessage;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class WebSocketChannel<Message extends WebSocketInboundMessage>
    extends BaseWebSocketChannel<Message> {

  @Override
  protected final void reset() {}
}
