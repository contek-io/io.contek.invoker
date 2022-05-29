package io.contek.invoker.hbdminverse.api.websocket.common.notification;

public abstract class NotificationWebSocketDataMessage<T>
    extends NotificationWebSocketChannelMessage {

  public T data;
}
