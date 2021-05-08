package io.contek.invoker.ftx.api.websocket;

import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;
import io.contek.invoker.ftx.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.util.Objects;

@Immutable
public abstract class WebSocketChannelId<Message extends WebSocketChannelMessage<?>>
    extends BaseWebSocketChannelId<Message> {

  private final String channel;
  private final String market;

  protected WebSocketChannelId(String channel, @Nullable String market) {
    super(combine(channel, market));
    this.channel = channel;
    this.market = market;
  }

  public final String getChannel() {
    return channel;
  }

  @Nullable
  public final String getMarket() {
    return market;
  }

  @Override
  public final boolean accepts(Message message) {
    return Objects.equals(channel, message.channel) && Objects.equals(market, message.market);
  }

  private static String combine(String channel, @Nullable String market) {
    if (market == null) {
      return channel;
    }
    return String.join(".", channel, market);
  }
}
