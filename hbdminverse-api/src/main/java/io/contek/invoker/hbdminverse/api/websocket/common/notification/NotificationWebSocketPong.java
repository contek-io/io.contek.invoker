package io.contek.invoker.hbdminverse.api.websocket.common.notification;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class NotificationWebSocketPong extends NotificationWebSocketRequest {

  public Long ts;
}
