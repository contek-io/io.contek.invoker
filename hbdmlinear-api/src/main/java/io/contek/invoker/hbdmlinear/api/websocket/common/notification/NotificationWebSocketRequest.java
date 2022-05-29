package io.contek.invoker.hbdmlinear.api.websocket.common.notification;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

public abstract class NotificationWebSocketRequest extends AnyWebSocketMessage {

  public String op;
  public String cid;
}
