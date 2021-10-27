package io.contek.invoker.ftx.api.websocket.user;

import io.contek.invoker.ftx.api.websocket.WebSocketChannelId;
import io.contek.invoker.ftx.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.Immutable;
import java.util.Objects;

@Immutable
public abstract class WebSocketUserChannelId<Message extends WebSocketChannelMessage<?>>
    extends WebSocketChannelId<Message> {

  protected WebSocketUserChannelId(String channel) {
    super(channel, null);
  }

  @Override
  public final boolean accepts(Message message) {
    return Objects.equals(getChannel(), message.channel);
  }
}
