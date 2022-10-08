package io.contek.invoker.binancefutures.api.websocket.market.raw;

import io.contek.invoker.binancefutures.api.websocket.common.WebSocketEventData;
import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;

import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class MarketWebSocketRawChannelId<Message extends WebSocketEventData>
    extends BaseWebSocketChannelId<Message> {

  protected MarketWebSocketRawChannelId(String streamName) {
    super(streamName);
  }

  @Override
  public final boolean accepts(Message message) {
    return true;
  }

  protected abstract Class<Message> getType();
}
