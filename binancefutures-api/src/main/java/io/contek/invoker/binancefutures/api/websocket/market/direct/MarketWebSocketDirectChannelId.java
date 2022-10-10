package io.contek.invoker.binancefutures.api.websocket.market.direct;

import io.contek.invoker.binancefutures.api.websocket.common.WebSocketEventData;
import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;

import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class MarketWebSocketDirectChannelId<Message extends WebSocketEventData>
    extends BaseWebSocketChannelId<Message> {

  protected MarketWebSocketDirectChannelId(String streamName) {
    super(streamName);
  }

  @Override
  public final boolean accepts(Message message) {
    return true;
  }

  protected abstract Class<Message> getType();
}
