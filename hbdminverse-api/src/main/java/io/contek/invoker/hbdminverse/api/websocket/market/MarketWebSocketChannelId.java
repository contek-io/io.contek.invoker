package io.contek.invoker.hbdminverse.api.websocket.market;

import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;

import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class MarketWebSocketChannelId<Message extends MarketWebSocketChannelMessage>
    extends BaseWebSocketChannelId<Message> {

  protected MarketWebSocketChannelId(String channel) {
    super(channel);
  }

  public final String getChannel() {
    return getValue();
  }

  @Override
  public final boolean accepts(Message message) {
    return getChannel().equals(message.ch);
  }
}
