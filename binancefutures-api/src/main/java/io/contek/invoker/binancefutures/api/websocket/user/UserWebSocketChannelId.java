package io.contek.invoker.binancefutures.api.websocket.user;

import io.contek.invoker.binancefutures.api.websocket.common.WebSocketEventMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;

import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class UserWebSocketChannelId<Message extends WebSocketEventMessage>
    extends BaseWebSocketChannelId<Message> {

  protected UserWebSocketChannelId(String eventType) {
    super(eventType);
  }

  public final String getEventType() {
    return getValue();
  }

  @Override
  public final boolean accepts(Message message) {
    return getEventType().equals(message.e);
  }
}
