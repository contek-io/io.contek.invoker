package io.contek.invoker.kraken.api.websocket;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.kraken.api.websocket.common.Subscription;
import io.contek.invoker.kraken.api.websocket.common.WebSocketInboundMessage;
import io.contek.invoker.kraken.api.websocket.common.WebSocketRequest;
import io.contek.invoker.kraken.api.websocket.common.WebSocketResponse;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static io.contek.invoker.commons.websocket.SubscriptionState.SUBSCRIBED;
import static io.contek.invoker.commons.websocket.SubscriptionState.SUBSCRIBING;
import static io.contek.invoker.commons.websocket.SubscriptionState.UNSUBSCRIBED;
import static io.contek.invoker.commons.websocket.SubscriptionState.UNSUBSCRIBING;

@ThreadSafe
public abstract class WebSocketChannel<Message extends WebSocketInboundMessage>
  extends BaseWebSocketChannel<Message> {

  private final AtomicReference<WebSocketRequest> pendingSubscriptionHolder = new AtomicReference<>();

  protected abstract Subscription getSubscription();

  protected abstract List<String> getPair();

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {

    WebSocketRequest request = new WebSocketRequest();
    request.event = "subscribe";
    request.pair = getPair();
    request.subscription = getSubscription();
    session.send(request);
    pendingSubscriptionHolder.set(request);

    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {

    WebSocketRequest request = new WebSocketRequest();
    request.event = "unsubscribe";
    request.pair = getPair();
    request.subscription = getSubscription();
    session.send(request);

    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {

    if (!(message instanceof WebSocketResponse)) {
      return null;
    }
    reset();
    WebSocketResponse res = (WebSocketResponse) message;
    if (!res.event.equals("subscriptionStatus")) {
      return null;
    }
    switch (res.status) {
      case "subscribed":
        return SUBSCRIBED;
      case "unsubscribed":
        return UNSUBSCRIBED;
      default:
        throw new IllegalStateException();
    }
  }

  @Override
  protected final void reset() {
  }
}
