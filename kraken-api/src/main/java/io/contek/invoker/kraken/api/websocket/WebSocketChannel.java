package io.contek.invoker.kraken.api.websocket;

import com.google.common.collect.ImmutableList;
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
import java.util.concurrent.atomic.AtomicReference;

import static io.contek.invoker.commons.websocket.SubscriptionState.*;

@ThreadSafe
public abstract class WebSocketChannel<Message extends WebSocketInboundMessage>
    extends BaseWebSocketChannel<Message> {

  private final AtomicReference<WebSocketRequest> pendingRequestHolder =
      new AtomicReference<>(null);

  protected abstract Subscription getSubscription();

  protected abstract String getPair();

  protected abstract boolean matches(Subscription response, Subscription request);

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    synchronized (pendingRequestHolder) {
      if (pendingRequestHolder.get() != null) {
        throw new IllegalStateException();
      }

      WebSocketRequest request = new WebSocketRequest();
      request.event = "subscribe";
      request.pair = ImmutableList.of(getPair());
      request.subscription = getSubscription();
      session.send(request);
      pendingRequestHolder.set(request);
    }
    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    synchronized (pendingRequestHolder) {
      if (pendingRequestHolder.get() != null) {
        throw new IllegalStateException();
      }

      WebSocketRequest request = new WebSocketRequest();
      request.event = "unsubscribe";
      request.pair = ImmutableList.of(getPair());
      request.subscription = getSubscription();
      session.send(request);
      pendingRequestHolder.set(request);
    }
    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (!(message instanceof WebSocketResponse)) {
      return null;
    }

    WebSocketResponse response = (WebSocketResponse) message;
    synchronized (pendingRequestHolder) {
      WebSocketRequest request = pendingRequestHolder.get();
      if (request == null) {
        return null;
      }
      if (!response.event.equals("subscriptionStatus")) {
        return null;
      }
      if (response.subscription == null || !matches(response.subscription, request.subscription)) {
        return null;
      }
      reset();
      switch (response.status) {
        case "subscribed":
          return SUBSCRIBED;
        case "unsubscribed":
          return UNSUBSCRIBED;
        default:
          throw new IllegalStateException(response.status);
      }
    }
  }

  @Override
  protected final void reset() {
    synchronized (pendingRequestHolder) {
      pendingRequestHolder.set(null);
    }
  }
}
