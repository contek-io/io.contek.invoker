package io.contek.invoker.bitstamp.api.websocket;

import io.contek.invoker.bitstamp.api.websocket.common.WebSocketChannelMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;

public abstract class WebSocketChannelId<Message extends WebSocketChannelMessage<?>>
    extends BaseWebSocketChannelId<Message> {

  protected WebSocketChannelId(String topic) {
    super(topic);
  }

  public final String getChannel() {
    return getValue();
  }

  @Override
  public final boolean accepts(Message message) {
    return getChannel().equals(message.channel);
  }
}
