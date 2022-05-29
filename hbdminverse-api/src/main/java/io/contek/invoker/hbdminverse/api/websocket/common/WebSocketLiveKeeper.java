package io.contek.invoker.hbdminverse.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketLiveKeeper;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.commons.websocket.WebSocketSessionInactiveException;

public final class WebSocketLiveKeeper implements IWebSocketLiveKeeper {

  private WebSocketLiveKeeper() {}

  public static WebSocketLiveKeeper getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public void onHeartbeat(WebSocketSession session) throws WebSocketSessionInactiveException {}

  @Override
  public void onMessage(AnyWebSocketMessage message, WebSocketSession session) {
    if (message instanceof WebSocketPing) {
      WebSocketPing ping = (WebSocketPing) message;
      WebSocketPong pong = new WebSocketPong();
      pong.pong = ping.ping;
      session.send(pong);
    }
  }

  @Override
  public void afterDisconnect() {}

  private static final class InstanceHolder {

    private static final WebSocketLiveKeeper INSTANCE = new WebSocketLiveKeeper();
  }
}
