package io.contek.invoker.bybitlinear.api.websocket;

import io.contek.invoker.bybitlinear.api.websocket.common.WebSocketTopicMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;

import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class WebSocketChannelId<Message extends WebSocketTopicMessage>
    extends BaseWebSocketChannelId<Message> {

  protected WebSocketChannelId(String topic) {
    super(topic);
  }

  public final String getTopic() {
    return getValue();
  }

  @Override
  public final boolean accepts(Message message) {
    return getTopic().equals(message.topic);
  }
}
