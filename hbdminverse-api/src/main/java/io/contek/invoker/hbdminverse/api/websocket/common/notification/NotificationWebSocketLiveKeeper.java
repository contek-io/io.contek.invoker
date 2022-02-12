package io.contek.invoker.hbdminverse.api.websocket.common.notification;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketLiveKeeper;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.commons.websocket.WebSocketSessionInactiveException;

import javax.annotation.concurrent.Immutable;

import static io.contek.invoker.hbdminverse.api.websocket.user.constants.OpKeys._pong;

@Immutable
final class NotificationWebSocketLiveKeeper implements IWebSocketLiveKeeper {

  static NotificationWebSocketLiveKeeper getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public void onHeartbeat(WebSocketSession session) throws WebSocketSessionInactiveException {}

  @Override
  public void onMessage(AnyWebSocketMessage message, WebSocketSession session) {
    if (message instanceof NotificationWebSocketPing) {
      NotificationWebSocketPing ping = (NotificationWebSocketPing) message;
      NotificationWebSocketPong pong = new NotificationWebSocketPong();
      pong.op = _pong;
      pong.ts = ping.ts;
      session.send(pong);
    }
  }

  @Override
  public void afterDisconnect() {}

  private NotificationWebSocketLiveKeeper() {}

  @Immutable
  private static final class InstanceHolder {

    private static final NotificationWebSocketLiveKeeper INSTANCE =
        new NotificationWebSocketLiveKeeper();
  }
}
