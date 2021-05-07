package io.contek.invoker.binancefutures.api.websocket.user;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class AccountUpdateChannel extends UserWebSocketChannel<AccountUpdateEvent> {

  @Override
  protected String getDisplayName() {
    return "accountUpdateChannel";
  }

  @Override
  protected Class<AccountUpdateEvent> getMessageType() {
    return AccountUpdateEvent.class;
  }

  @Override
  protected boolean accepts(AccountUpdateEvent message) {
    return true;
  }
}
