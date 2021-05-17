package io.contek.invoker.kraken.api.websocket;

import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;
import io.contek.invoker.kraken.api.websocket.common.WebSocketChannelDataMessage;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.util.Objects;

@Immutable
public abstract class WebSocketChannelId<Message extends WebSocketChannelDataMessage<?>>
    extends BaseWebSocketChannelId<Message> {

  private final String channelName;
  private final String pair;

  protected WebSocketChannelId(String channelName, String pair) {
    super(combine(channelName, pair));
    this.channelName = channelName;
    this.pair = pair;
  }

  public final String getChannelName() {
    return channelName;
  }

  public final String getPair() {
    return pair;
  }

  @Override
  public final boolean accepts(Message message) {
    return Objects.equals(pair, message.pair);
  }

  private static String combine(String channel, @Nullable String market) {
    if (market == null) {
      return channel;
    }
    return String.join(".", channel, market);
  }
}
