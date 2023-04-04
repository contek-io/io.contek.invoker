package io.contek.invoker.hbdminverse.api.websocket.common.marketdata;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketLiveKeeper;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.commons.websocket.WebSocketSessionInactiveException;

import javax.annotation.concurrent.Immutable;

@Immutable
final class MarketDataWebSocketLiveKeeper implements IWebSocketLiveKeeper {

  static MarketDataWebSocketLiveKeeper getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public void onHeartbeat(WebSocketSession session) throws WebSocketSessionInactiveException {}

  @Override
  public void onMessage(AnyWebSocketMessage message, WebSocketSession session) {
    if (message instanceof MarketDataWebSocketPing ping) {
      MarketDataWebSocketPong pong = new MarketDataWebSocketPong();
      pong.pong = ping.ping;
      session.send(pong);
    }
  }

  @Override
  public void afterDisconnect() {}

  private MarketDataWebSocketLiveKeeper() {}

  @Immutable
  private static final class InstanceHolder {

    private static final MarketDataWebSocketLiveKeeper INSTANCE =
        new MarketDataWebSocketLiveKeeper();
  }
}
