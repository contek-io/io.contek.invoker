package io.contek.invoker.binancefutures.api.websocket.user;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class LeverageUpdateChannel extends UserWebSocketChannel<LeverageUpdateEvent> {

  @Override
  protected String getDisplayName() {
    return "LeverageUpdateChannel";
  }

  @Override
  protected Class<LeverageUpdateEvent> getMessageType() {
    return LeverageUpdateEvent.class;
  }
}
