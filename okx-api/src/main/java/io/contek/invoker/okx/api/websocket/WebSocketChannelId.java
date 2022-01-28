package io.contek.invoker.okx.api.websocket;

import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelArg;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelPushData;

import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class WebSocketChannelId<Message extends WebSocketChannelPushData<?>>
    extends BaseWebSocketChannelId<Message> {

  private final String channel;

  protected WebSocketChannelId(String channel, String suffix) {
    super(combine(channel, suffix));
    this.channel = channel;
  }

  public final String getChannel() {
    return channel;
  }

  public abstract WebSocketChannelArg toChannelArg();

  private static String combine(String channel, String suffix) {
    return String.join(".", channel, suffix);
  }
}
