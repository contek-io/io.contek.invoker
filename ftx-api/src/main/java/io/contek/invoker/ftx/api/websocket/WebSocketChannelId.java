package io.contek.invoker.ftx.api.websocket;

import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;
import io.contek.invoker.ftx.api.websocket.common.WebSocketChannelMessage;

public abstract class WebSocketChannelId<Message extends WebSocketChannelMessage<?>>
    extends BaseWebSocketChannelId<Message> {

  private final String channel;

  protected WebSocketChannelId(String channel, String suffix) {
    super(combine(channel, suffix));
    this.channel = channel;
  }

  private static String combine(String channel, String suffix) {
    if (suffix == null) {
      return channel;
    }
    return String.join(".", channel, suffix);
  }

  public final String getChannel() {
    return channel;
  }
}
