package io.contek.invoker.ftx.api.websocket.market;

import io.contek.invoker.ftx.api.websocket.WebSocketChannelId;

import javax.annotation.concurrent.Immutable;
import java.util.Objects;

@Immutable
public abstract class WebSocketMarketChannelId<Message extends WebSocketMarketChannelMessage<?>>
    extends WebSocketChannelId<Message> {

  private final String market;

  protected WebSocketMarketChannelId(String channel, String market) {
    super(channel, market);
    this.market = market;
  }

  public String getMarket() {
    return market;
  }

  @Override
  public final boolean accepts(Message message) {
    return Objects.equals(getChannel(), message.channel) && Objects.equals(market, message.market);
  }
}
