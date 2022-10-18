package io.contek.invoker.binancefutures.api.websocket.market.direct;

import io.contek.invoker.binancefutures.api.websocket.common.WebSocketEventData;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.websocket.SubscriptionState.SUBSCRIBED;
import static io.contek.invoker.commons.websocket.SubscriptionState.UNSUBSCRIBED;

@ThreadSafe
public final class MarketWebSocketDirectChannel<Data extends WebSocketEventData>
    extends BaseWebSocketChannel<MarketWebSocketDirectChannelId<Data>, Data, Data> {

  MarketWebSocketDirectChannel(MarketWebSocketDirectChannelId<Data> id) {
    super(id);
  }

  @Override
  public Class<Data> getMessageType() {
    return getId().getType();
  }

  @Override
  protected Data getData(Data message) {
    return message;
  }

  @Override
  protected SubscriptionState subscribe(WebSocketSession session) {
    return SUBSCRIBED;
  }

  @Override
  protected SubscriptionState unsubscribe(WebSocketSession session) {
    return UNSUBSCRIBED;
  }

  @Nullable
  @Override
  protected SubscriptionState getState(AnyWebSocketMessage message) {
    return null;
  }

  @Override
  protected void reset() {}
}
