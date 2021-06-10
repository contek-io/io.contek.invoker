package io.contek.invoker.hbdminverse.api.websocket.common.notification;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class NotificationWebSocketRequest extends AnyWebSocketMessage {

  public String op;
  public String cid;
}
