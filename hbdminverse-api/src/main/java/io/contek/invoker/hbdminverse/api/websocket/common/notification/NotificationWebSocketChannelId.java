package io.contek.invoker.hbdminverse.api.websocket.common.notification;

import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;

import javax.annotation.concurrent.Immutable;

@Immutable
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
