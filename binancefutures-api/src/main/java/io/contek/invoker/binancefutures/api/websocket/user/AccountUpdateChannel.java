package io.contek.invoker.binancefutures.api.websocket.user;

import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class AccountUpdateChannel extends UserWebSocketChannel<AccountUpdateEvent> {

  @Override
  protected BaseWebSocketChannelId getId() {
    return "accountUpdateChannel";
  }

  @Override
  protected Class<AccountUpdateEvent> getMessageType() {
    return AccountUpdateEvent.class;
  }
}
