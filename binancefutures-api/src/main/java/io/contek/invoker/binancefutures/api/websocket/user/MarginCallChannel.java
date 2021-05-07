package io.contek.invoker.binancefutures.api.websocket.user;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class MarginCallChannel extends UserWebSocketChannel<MarginCallEvent> {

  @Override
  protected String getDisplayName() {
    return "MarginCallChannel";
  }

  @Override
  protected Class<MarginCallEvent> getMessageType() {
    return MarginCallEvent.class;
  }
}
