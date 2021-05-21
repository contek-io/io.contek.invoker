package io.contek.invoker.hbdmlinear.api.websocket.market;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.websocket.SubscriptionState.*;
import static io.contek.invoker.hbdmlinear.api.websocket.common.constants.WebSocketStatusKeys._ok;

@ThreadSafe
abstract class MarketWebSocketChannel<
        Id extends MarketWebSocketChannelId<Message>, Message extends MarketWebSocketChannelMessage>
    extends BaseWebSocketChannel<Id, Message> {

  private final Class<Message> type;
  private final MarketWebSocketRequestIdGenerator requestIdGenerator;

  protected MarketWebSocketChannel(
      Id id, Class<Message> type, MarketWebSocketRequestIdGenerator requestIdGenerator) {
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
    MarketWebSocketUnsubscribeRequest request = new MarketWebSocketUnsubscribeRequest();
    request.unsub = id.getChannel();
    request.id = generateNexRequestId();
    session.send(request);
    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (message instanceof MarketWebSocketSubscribeConfirmation) {
      Id id = getId();
      MarketWebSocketSubscribeConfirmation confirmation =
          (MarketWebSocketSubscribeConfirmation) message;
      if (confirmation.subbed.equals(id.getChannel())) {
        if (!_ok.equals(confirmation.status)) {
          throw new IllegalStateException(confirmation.status);
        }
        return SUBSCRIBED;
      }
    }
    if (message instanceof MarketWebSocketUnsubscribeConfirmation) {
      Id id = getId();
      MarketWebSocketUnsubscribeConfirmation confirmation =
          (MarketWebSocketUnsubscribeConfirmation) message;
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

  String generateNexRequestId() {
    return requestIdGenerator.generateNext();
  }
}
