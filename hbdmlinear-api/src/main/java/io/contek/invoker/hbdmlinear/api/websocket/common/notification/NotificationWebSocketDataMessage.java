package io.contek.invoker.hbdmlinear.api.websocket.common.notification;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public abstract class NotificationWebSocketDataMessage<T>
    extends NotificationWebSocketChannelMessage {

  public List<T> data;
}
