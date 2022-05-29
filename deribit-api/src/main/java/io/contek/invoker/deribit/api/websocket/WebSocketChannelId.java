package io.contek.invoker.deribit.api.websocket;

import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;
import io.contek.invoker.deribit.api.websocket.common.WebSocketSingleChannelMessage;

public abstract class WebSocketChannelId<Message extends WebSocketSingleChannelMessage<?>>
    extends BaseWebSocketChannelId<Message> {

  protected WebSocketChannelId(String channel) {
    super(channel);
  }

  public final String getChannel() {
    return getValue();
  }

  @Override
  public final boolean accepts(Message message) {
    return getChannel().equals(message.params.channel);
  }
}
