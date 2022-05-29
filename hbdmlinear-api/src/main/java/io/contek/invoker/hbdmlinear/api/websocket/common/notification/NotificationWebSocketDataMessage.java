package io.contek.invoker.hbdmlinear.api.websocket.common.notification;

public abstract class NotificationWebSocketDataMessage<T>
    extends NotificationWebSocketChannelMessage {

  public T data;
}
