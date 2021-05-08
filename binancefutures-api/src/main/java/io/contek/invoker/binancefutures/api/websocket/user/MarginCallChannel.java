package io.contek.invoker.binancefutures.api.websocket.user;

import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class MarginCallChannel extends UserWebSocketChannel<MarginCallEvent> {

  @Override
  protected BaseWebSocketChannelId getId() {
    return "MarginCallChannel";
  }

  @Override
  protected Class<MarginCallEvent> getMessageType() {
    return MarginCallEvent.class;
  }
}
