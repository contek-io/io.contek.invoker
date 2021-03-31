package io.contek.invoker.ftx.api.websocket.user;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.ftx.api.websocket.WebSocketChannel;
import io.contek.invoker.ftx.api.websocket.common.WebSocketInboundMessage;
import io.contek.invoker.ftx.api.websocket.common.WebSocketSubscriptionRequest;
import io.contek.invoker.ftx.api.websocket.common.WebSocketSubscriptionResponse;

import javax.annotation.Nullable;

import static io.contek.invoker.commons.websocket.SubscriptionState.SUBSCRIBED;
import static io.contek.invoker.commons.websocket.SubscriptionState.SUBSCRIBING;
import static io.contek.invoker.commons.websocket.SubscriptionState.UNSUBSCRIBED;
import static io.contek.invoker.commons.websocket.SubscriptionState.UNSUBSCRIBING;
import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketInboundKeys._subscribed;
import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketInboundKeys._unsubscribed;
import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketOutboundKeys._subscribe;
import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketOutboundKeys._unsubscribe;

public abstract class UserWebSocketChannel<Message extends WebSocketInboundMessage>
    extends WebSocketChannel<Message> {
  protected abstract String getChannel();

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    WebSocketSubscriptionRequest request = new WebSocketSubscriptionRequest();
    request.op = _subscribe;
    request.channel = getChannel();
    session.send(request);
    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    WebSocketSubscriptionRequest request = new WebSocketSubscriptionRequest();
    request.op = _unsubscribe;
    request.channel = getChannel();
    session.send(request);
    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (message instanceof WebSocketSubscriptionResponse) {
      WebSocketSubscriptionResponse confirmation = (WebSocketSubscriptionResponse) message;
      if (!getChannel().equals(confirmation.channel)) {
        return null;
      }
      switch (confirmation.type) {
        case _subscribed:
          return SUBSCRIBED;
        case _unsubscribed:
          return UNSUBSCRIBED;
        default:
          throw new IllegalArgumentException(confirmation.type);
      }
    }
    return null;
  }
}
