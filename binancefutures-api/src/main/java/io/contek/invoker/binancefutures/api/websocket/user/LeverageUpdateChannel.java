package io.contek.invoker.binancefutures.api.websocket.user;

import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class LeverageUpdateChannel extends UserWebSocketChannel<LeverageUpdateEvent> {

  @Override
  protected BaseWebSocketChannelId getId() {
    return "LeverageUpdateChannel";
  }

  @Override
  protected Class<LeverageUpdateEvent> getMessageType() {
    return LeverageUpdateEvent.class;
  }
}
