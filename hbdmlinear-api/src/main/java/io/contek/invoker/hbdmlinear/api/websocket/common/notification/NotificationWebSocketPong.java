package io.contek.invoker.hbdmlinear.api.websocket.common.notification;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class NotificationWebSocketPong extends AnyWebSocketMessage {

  public long pong;
}
