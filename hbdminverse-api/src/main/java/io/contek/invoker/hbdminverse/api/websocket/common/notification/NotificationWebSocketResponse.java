package io.contek.invoker.hbdminverse.api.websocket.common.notification;

public abstract class NotificationWebSocketResponse extends NotificationWebSocketInboundMessage {

  public String cid;

  public int err_code;

  public String err_msg;
}
