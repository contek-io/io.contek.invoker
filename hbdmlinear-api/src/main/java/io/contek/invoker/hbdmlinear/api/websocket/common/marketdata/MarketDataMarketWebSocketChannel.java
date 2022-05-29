package io.contek.invoker.hbdmlinear.api.websocket.common.marketdata;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;

import static io.contek.invoker.commons.websocket.SubscriptionState.*;
import static io.contek.invoker.hbdmlinear.api.websocket.common.constants.WebSocketStatusKeys._ok;

public abstract class MarketDataMarketWebSocketChannel<
        Id extends MarketDataWebSocketChannelId<Message>,
        Message extends MarketDataWebSocketChannelMessage>
    extends BaseWebSocketChannel<Id, Message> {

  private final Class<Message> type;
  private final MarketDataWebSocketRequestIdGenerator requestIdGenerator;

  protected MarketDataMarketWebSocketChannel(
      Id id, Class<Message> type, MarketDataWebSocketRequestIdGenerator requestIdGenerator) {
    super(id);
    this.type = type;
    this.requestIdGenerator = requestIdGenerator;
  }

  @Override
  public final Class<Message> getMessageType() {
    return type;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    Id id = getId();
    MarketDataWebSocketUnsubscribeRequest request = new MarketDataWebSocketUnsubscribeRequest();
    request.unsub = id.getChannel();
    request.id = generateNexRequestId();
    session.send(request);
    return UNSUBSCRIBING;
  }

  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (message instanceof MarketDataWebSocketSubscribeConfirmation) {
      Id id = getId();
      MarketDataWebSocketSubscribeConfirmation confirmation =
          (MarketDataWebSocketSubscribeConfirmation) message;
      if (confirmation.subbed.equals(id.getChannel())) {
        if (!_ok.equals(confirmation.status)) {
          throw new IllegalStateException(confirmation.status);
        }
        return SUBSCRIBED;
      }
    }
    if (message instanceof MarketDataWebSocketUnsubscribeConfirmation) {
      Id id = getId();
      MarketDataWebSocketUnsubscribeConfirmation confirmation =
          (MarketDataWebSocketUnsubscribeConfirmation) message;
      if (confirmation.unsubbed.equals(id.getChannel())) {
        if (!_ok.equals(confirmation.status)) {
          throw new IllegalStateException(confirmation.status);
        }
        return UNSUBSCRIBED;
      }
    }
    return null;
  }

  @Override
  protected final void reset() {}

  protected final String generateNexRequestId() {
    return requestIdGenerator.generateNext();
  }
}
