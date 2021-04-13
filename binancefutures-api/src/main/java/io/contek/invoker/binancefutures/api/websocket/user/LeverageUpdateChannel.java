package io.contek.invoker.binancefutures.api.websocket.user;

public class LeverageUpdateChannel extends UserWebSocketChannel<LeverageUpdateEvent> {
  @Override
  protected String getDisplayName() {
    return "LeverageUpdateChannel";
  }

  @Override
  protected Class<LeverageUpdateEvent> getMessageType() {
    return LeverageUpdateEvent.class;
  }

  @Override
  protected boolean accepts(LeverageUpdateEvent leverageUpdateEvent) {
    return true;
  }
}
