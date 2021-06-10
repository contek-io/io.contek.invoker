package io.contek.invoker.hbdmlinear.api.websocket.common.notification;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class NotificationWebSocketPong extends NotificationWebSocketRequest {

  public Long ts;
}
