package io.contek.invoker.deribit.api.websocket;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.deribit.api.websocket.common.WebSocketInboundMessage;
import io.contek.invoker.deribit.api.websocket.common.WebSocketRequest;
import io.contek.invoker.deribit.api.websocket.common.WebSocketResponse;
import io.contek.invoker.deribit.api.websocket.common.constants.WebSocketOutboundKeys;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

import static io.contek.invoker.commons.websocket.SubscriptionState.*;

@ThreadSafe
public abstract class WebSocketChannel<Message extends WebSocketInboundMessage>
  extends BaseWebSocketChannel<Message> {

  private final AtomicReference<WebSocketRequest> pendingSubscriptionHolder = new AtomicReference<>();

  protected abstract String getChannel();

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {

    synchronized (pendingSubscriptionHolder) {
      if (pendingSubscriptionHolder.get() != null) {
        throw new IllegalStateException();
      }
      WebSocketRequest request = new WebSocketRequest();
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
      WebSocketRequest request = new WebSocketRequest();
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
      WebSocketRequest command = pendingSubscriptionHolder.get();
      if (command == null) {
        return null;
      }
      if (!(message instanceof WebSocketResponse)) {
        return null;
      }
      WebSocketResponse confirmation = (WebSocketResponse) message;
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
