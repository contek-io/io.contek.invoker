package io.contek.invoker.hbdmlinear.api.websocket.common.notification;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketLiveKeeper;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketPing;
import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketPong;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class NotificationWebSocketLiveKeeper implements IWebSocketLiveKeeper {

  public static NotificationWebSocketLiveKeeper getInstance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public void onHeartbeat(WebSocketSession session) {}

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

  private NotificationWebSocketLiveKeeper() {}

  @Immutable
  private static final class InstanceHolder {

    private static final NotificationWebSocketLiveKeeper INSTANCE =
        new NotificationWebSocketLiveKeeper();
  }
}
