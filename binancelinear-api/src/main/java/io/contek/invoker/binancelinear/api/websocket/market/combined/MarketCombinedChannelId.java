package io.contek.invoker.binancelinear.api.websocket.market.combined;

import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;

import javax.annotation.concurrent.Immutable;

@Immutable
abstract class MarketCombinedChannelId<Message extends WebSocketStreamMessage<?>>
    extends BaseWebSocketChannelId<Message> {

  protected MarketCombinedChannelId(String streamName) {
    super(streamName);
  }

  @Override
  public final boolean accepts(Message message) {
    return getValue().equals(message.stream);
  }
}
