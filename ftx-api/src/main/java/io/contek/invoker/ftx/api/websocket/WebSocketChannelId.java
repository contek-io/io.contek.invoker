package io.contek.invoker.ftx.api.websocket;

import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class WebSocketChannelId extends BaseWebSocketChannelId {

  private final String channel;
  private final String market;

  public WebSocketChannelId(String channel, @Nullable String market) {
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

  private static String combine(String channel, @Nullable String market) {
    if (market == null) {
      return channel;
    }
    return String.join(".", channel, market);
  }
}
