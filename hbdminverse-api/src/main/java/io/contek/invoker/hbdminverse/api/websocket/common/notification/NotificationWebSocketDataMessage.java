package io.contek.invoker.hbdminverse.api.websocket.common.notification;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class NotificationWebSocketDataMessage<T>
    extends NotificationWebSocketChannelMessage {

  public T data;
}
