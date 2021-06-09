package io.contek.invoker.hbdmlinear.api.websocket.common.notification;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class NotificationWebSocketDataMessage<T>
    extends NotificationWebSocketChannelMessage {

  public T data;
}
