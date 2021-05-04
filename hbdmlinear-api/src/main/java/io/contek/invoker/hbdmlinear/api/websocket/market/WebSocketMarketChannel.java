package io.contek.invoker.hbdmlinear.api.websocket.market;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketSubscribeConfirmation;
import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketSubscribeRequest;
import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketUnsubscribeConfirmation;
import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketUnsubscribeRequest;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.websocket.SubscriptionState.*;
import static io.contek.invoker.hbdmlinear.api.websocket.common.constants.WebSocketStatusKeys._ok;

@ThreadSafe
public abstract class WebSocketMarketChannel<Message extends WebSocketMarketDataMessage>
    extends BaseWebSocketChannel<Message> {

  private final WebSocketMarketRequestIdGenerator requestIdGenerator;

  protected WebSocketMarketChannel(WebSocketMarketRequestIdGenerator requestIdGenerator) {
    this.requestIdGenerator = requestIdGenerator;
  }

  protected abstract String getTopic();

  @Override
  protected final String getDisplayName() {
    return getTopic();
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    WebSocketSubscribeRequest request = new WebSocketSubscribeRequest();
    request.sub = getTopic();
    request.id = Long.toString(requestIdGenerator.generateNext());
    session.send(request);
    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    WebSocketUnsubscribeRequest request = new WebSocketUnsubscribeRequest();
    request.unsub = getTopic();
    request.id = Long.toString(requestIdGenerator.generateNext());
    session.send(request);
    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (message instanceof WebSocketSubscribeConfirmation) {
      WebSocketSubscribeConfirmation confirmation = (WebSocketSubscribeConfirmation) message;
      if (confirmation.subbed.equals(getTopic())) {
        if (!_ok.equals(confirmation.status)) {
          throw new IllegalStateException(confirmation.status);
        }
        return SUBSCRIBED;
      }
    }
    if (message instanceof WebSocketUnsubscribeConfirmation) {
      WebSocketUnsubscribeConfirmation confirmation = (WebSocketUnsubscribeConfirmation) message;
      if (confirmation.unsubbed.equals(getTopic())) {
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
