package io.contek.invoker.hbdminverse.api.websocket.common.notification;

import io.contek.invoker.hbdminverse.api.websocket.common.WebSocketInboundMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class NotificationWebSocketInboundMessage extends WebSocketInboundMessage {

  public String op;
}
