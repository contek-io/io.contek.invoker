package io.contek.invoker.hbdminverse.api.websocket.market;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.hbdminverse.api.websocket.common.WebSocketSubscribeConfirmation;
import io.contek.invoker.hbdminverse.api.websocket.common.WebSocketSubscribeRequest;
import io.contek.invoker.hbdminverse.api.websocket.common.WebSocketUnsubscribeConfirmation;
import io.contek.invoker.hbdminverse.api.websocket.common.WebSocketUnsubscribeRequest;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.websocket.SubscriptionState.*;
import static io.contek.invoker.hbdminverse.api.websocket.common.constants.WebSocketStatusKeys._ok;

@ThreadSafe
abstract class WebSocketMarketChannel<Message extends WebSocketMarketDataMessage>
    extends BaseWebSocketChannel<Message> {

  private final String topic;
  private final Class<Message> type;
  private final WebSocketMarketRequestIdGenerator requestIdGenerator;

  protected WebSocketMarketChannel(
      String topic, Class<Message> type, WebSocketMarketRequestIdGenerator requestIdGenerator) {
    this.topic = topic;
    this.type = type;
    this.requestIdGenerator = requestIdGenerator;
  }

  final void register(WebSocketMarketMessageParser parser) {
    parser.register(topic, type);
  }

  @Override
  protected final String getDisplayName() {
    return topic;
  }

  @Override
  protected final Class<Message> getMessageType() {
    return type;
  }

  @Override
  protected final boolean accepts(Message message) {
    return topic.equals(message.ch);
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    WebSocketSubscribeRequest request = new WebSocketSubscribeRequest();
    request.sub = topic;
    request.id = Long.toString(requestIdGenerator.generateNext());
    session.send(request);
    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    WebSocketUnsubscribeRequest request = new WebSocketUnsubscribeRequest();
    request.unsub = topic;
    request.id = Long.toString(requestIdGenerator.generateNext());
    session.send(request);
    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (message instanceof WebSocketSubscribeConfirmation) {
      WebSocketSubscribeConfirmation confirmation = (WebSocketSubscribeConfirmation) message;
      if (confirmation.subbed.equals(topic)) {
        if (!_ok.equals(confirmation.status)) {
          throw new IllegalStateException(confirmation.status);
        }
        return SUBSCRIBED;
      }
    }
    if (message instanceof WebSocketUnsubscribeConfirmation) {
      WebSocketUnsubscribeConfirmation confirmation = (WebSocketUnsubscribeConfirmation) message;
      if (confirmation.unsubbed.equals(topic)) {
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
}
