package io.contek.invoker.deribit.api.websocket;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.deribit.api.websocket.common.WebSocketInboundMessage;
import io.contek.invoker.deribit.api.websocket.common.WebSocketSubscriptionRequest;
import io.contek.invoker.deribit.api.websocket.common.WebSocketSubscriptionResponse;
import io.contek.invoker.deribit.api.websocket.common.constants.WebSocketOutboundKeys;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

import static io.contek.invoker.commons.websocket.SubscriptionState.*;

@ThreadSafe
public abstract class WebSocketChannel<Message extends WebSocketInboundMessage>
  extends BaseWebSocketChannel<Message> {

  private final AtomicReference<WebSocketSubscriptionRequest> pendingSubscriptionHolder = new AtomicReference<>();

  protected abstract String getChannel();

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {

    synchronized (pendingSubscriptionHolder) {
      if (pendingSubscriptionHolder.get() != null) {
        throw new IllegalStateException();
      }
      WebSocketSubscriptionRequest request = new WebSocketSubscriptionRequest();
      request.method = "public/" + WebSocketOutboundKeys._subscribe;
      request.params.put("channels", Collections.singletonList(getChannel()));
      session.send(request);
      pendingSubscriptionHolder.set(request);
    }

    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    synchronized (pendingSubscriptionHolder) {
      if (pendingSubscriptionHolder.get() != null) {
        throw new IllegalStateException();
      }
      WebSocketSubscriptionRequest request = new WebSocketSubscriptionRequest();
      request.method = "public/" + WebSocketOutboundKeys._unsubscribe;
      request.params.put("channels", Collections.singletonList(getChannel()));
      session.send(request);
      pendingSubscriptionHolder.set(request);
    }

    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    synchronized (pendingSubscriptionHolder) {
      WebSocketSubscriptionRequest command = pendingSubscriptionHolder.get();
      if (command == null) {
        return null;
      }
      if (!(message instanceof WebSocketSubscriptionResponse)) {
        return null;
      }
      WebSocketSubscriptionResponse confirmation = (WebSocketSubscriptionResponse) message;
      if (confirmation.id == null || !confirmation.id.equals(command.id) || confirmation.result.isEmpty()) {
        return null;
      }
      reset();
      switch (command.method) {
        case "public/" + WebSocketOutboundKeys._subscribe:
          return SUBSCRIBED;
        case "public/" + WebSocketOutboundKeys._unsubscribe:
          return UNSUBSCRIBED;
        default:
          throw new IllegalStateException();
      }
    }
  }

  @Override
  protected final void reset() {
  }
}
