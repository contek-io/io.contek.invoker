package io.contek.invoker.hbdminverse.api.websocket.common.notification;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class NotificationWebSocketChannelMessage
    extends NotificationWebSocketInboundMessage {

  public String topic;
}
