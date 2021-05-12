package io.contek.invoker.hbdmlinear.api.websocket.market;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketSubscribeConfirmation;
import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketUnsubscribeConfirmation;
import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketUnsubscribeRequest;

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
  protected final Class<Message> getMessageType() {
    return type;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    Id id = getId();
    WebSocketUnsubscribeRequest request = new WebSocketUnsubscribeRequest();
    request.unsub = id.getChannel();
    request.id = generateNexRequestId();
    session.send(request);
    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (message instanceof WebSocketSubscribeConfirmation) {
      Id id = getId();
      WebSocketSubscribeConfirmation confirmation = (WebSocketSubscribeConfirmation) message;
      if (confirmation.subbed.equals(id.getChannel())) {
        if (!_ok.equals(confirmation.status)) {
          throw new IllegalStateException(confirmation.status);
        }
        return SUBSCRIBED;
      }
    }
    if (message instanceof WebSocketUnsubscribeConfirmation) {
      Id id = getId();
      WebSocketUnsubscribeConfirmation confirmation = (WebSocketUnsubscribeConfirmation) message;
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
    return Integer.toString(requestIdGenerator.generateNext());
  }
}
