package io.contek.invoker.hbdmlinear.api.websocket.common.notification;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class NotificationWebSocketChannelMessage
    extends NotificationWebSocketInboundMessage {

  public String topic;
}
