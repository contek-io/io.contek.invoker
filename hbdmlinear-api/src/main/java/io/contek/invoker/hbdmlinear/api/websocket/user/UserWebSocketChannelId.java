package io.contek.invoker.hbdmlinear.api.websocket.user;

import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;

import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class UserWebSocketChannelId<Message extends UserWebSocketChannelMessage>
    extends BaseWebSocketChannelId<Message> {

  protected UserWebSocketChannelId(String channel) {
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
