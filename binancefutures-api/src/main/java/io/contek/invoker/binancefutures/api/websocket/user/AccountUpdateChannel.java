package io.contek.invoker.binancefutures.api.websocket.user;

public class AccountUpdateChannel extends UserWebSocketChannel<AccountUpdateEvent> {

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
