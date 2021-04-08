package io.contek.invoker.binancefutures.api.websocket.user;

import io.contek.invoker.binancefutures.api.websocket.UserWebSocketChannel;

public class MarginCallChannel extends UserWebSocketChannel<MarginCallEvent> {
  @Override
  protected String getDisplayName() {
    return "MarginCallChannel";
  }

  @Override
  protected Class<MarginCallEvent> getMessageType() {
    return MarginCallEvent.class;
  }

  @Override
  protected boolean accepts(MarginCallEvent marginCallEvent) {
    return true;
  }
}
