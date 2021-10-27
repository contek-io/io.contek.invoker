package io.contek.invoker.ftx.api.websocket;

import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;
import io.contek.invoker.ftx.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class WebSocketChannelId<Message extends WebSocketChannelMessage<?>>
    extends BaseWebSocketChannelId<Message> {

  private final String channel;

  protected WebSocketChannelId(String channel, @Nullable String suffix) {
    super(combine(channel, suffix));
    this.channel = channel;
  }

  public final String getChannel() {
    return channel;
  }

  private static String combine(String channel, @Nullable String suffix) {
    if (suffix == null) {
      return channel;
    }
    return String.join(".", channel, suffix);
  }
}
