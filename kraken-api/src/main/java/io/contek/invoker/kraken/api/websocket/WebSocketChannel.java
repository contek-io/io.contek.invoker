package io.contek.invoker.kraken.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.kraken.api.websocket.common.Subscription;
import io.contek.invoker.kraken.api.websocket.common.WebSocketChannelDataMessage;
import io.contek.invoker.kraken.api.websocket.common.WebSocketSubscribeRequest;
import io.contek.invoker.kraken.api.websocket.common.WebSocketSubscriptionStatus;

import java.util.concurrent.atomic.AtomicReference;

import static io.contek.invoker.commons.websocket.SubscriptionState.*;
import static io.contek.invoker.kraken.api.websocket.common.constants.WebSocketEventKeys._subscribe;
import static io.contek.invoker.kraken.api.websocket.common.constants.WebSocketEventKeys._unsubscribe;

public abstract class WebSocketChannel<
        Id extends WebSocketChannelId<Message>, Message extends WebSocketChannelDataMessage<?>>
    extends BaseWebSocketChannel<Id, Message> {

  private final WebSocketRequestIdGenerator requestIdGenerator;

  private final AtomicReference<WebSocketSubscribeRequest> pendingRequestHolder =
      new AtomicReference<>(null);

  protected WebSocketChannel(Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id);
    this.requestIdGenerator = requestIdGenerator;
  }

  protected abstract Subscription getSubscription();

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    synchronized (pendingRequestHolder) {
      if (pendingRequestHolder.get() != null) {
        throw new IllegalStateException();
      }

      Id id = getId();
      WebSocketSubscribeRequest request = new WebSocketSubscribeRequest();
      request.event = _subscribe;
      request.reqid = requestIdGenerator.generateNext();
      request.pair = ImmutableList.of(id.getPair());
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

      Id id = getId();
      WebSocketSubscribeRequest request = new WebSocketSubscribeRequest();
      request.event = _unsubscribe;
      request.reqid = requestIdGenerator.generateNext();
      request.pair = ImmutableList.of(id.getPair());
      request.subscription = getSubscription();
      session.send(request);
      pendingRequestHolder.set(request);
    }
    return UNSUBSCRIBING;
  }

  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (!(message instanceof WebSocketSubscriptionStatus)) {
      return null;
    }

    WebSocketSubscriptionStatus response = (WebSocketSubscriptionStatus) message;
    synchronized (pendingRequestHolder) {
      WebSocketSubscribeRequest request = pendingRequestHolder.get();
      if (request == null) {
        return null;
      }
      if (!response.event.equals("subscriptionStatus")) {
        return null;
      }
      if (response.reqid == null || response.reqid.equals(request.reqid)) {
        return null;
      }
      reset();
      switch (response.status) {
        case "subscribed":
          return SUBSCRIBED;
        case "unsubscribed":
          return UNSUBSCRIBED;
        default:
          throw new IllegalStateException(response.status + ": " + response.errorMessage);
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
