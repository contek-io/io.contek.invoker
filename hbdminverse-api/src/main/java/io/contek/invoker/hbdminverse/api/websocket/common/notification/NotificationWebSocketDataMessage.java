package io.contek.invoker.hbdminverse.api.websocket.common.notification;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public abstract class NotificationWebSocketDataMessage<T>
    extends NotificationWebSocketChannelMessage {

  public List<T> data;
}
