package io.contek.invoker.hbdmlinear.api.websocket.common.notification;

import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;

public abstract class NotificationWebSocketChannelId<
        Message extends NotificationWebSocketChannelMessage>
    extends BaseWebSocketChannelId<Message> {

  protected NotificationWebSocketChannelId(String channel) {
    super(channel);
  }

  public final String getTopic() {
    return getValue();
  }

  @Override
  public final boolean accepts(Message message) {
    return getTopic().equals(message.topic);
  }
}
