package io.contek.invoker.hbdmlinear.api.websocket.common.marketdata;

import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;

public abstract class MarketDataWebSocketChannelId<
        Message extends MarketDataWebSocketChannelMessage>
    extends BaseWebSocketChannelId<Message> {

  protected MarketDataWebSocketChannelId(String channel) {
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
