package io.contek.invoker.hbdmlinear.api.websocket.user;

import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketInboundMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class UserWebSocketInboundMessage extends WebSocketInboundMessage {

  public String op;
}
