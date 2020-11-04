package io.contek.invoker.bitmex.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketRequest;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketSubscribeConfirmation;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketTableDataMessage;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketUnsubscribeConfirmation;
import io.contek.invoker.commons.api.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.api.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.api.websocket.SubscriptionState;
import io.contek.invoker.commons.api.websocket.WebSocketSession;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.bitmex.api.websocket.common.constants.WebSocketRequestOperationKeys._subscribe;
import static io.contek.invoker.bitmex.api.websocket.common.constants.WebSocketRequestOperationKeys._unsubscribe;
import static io.contek.invoker.commons.api.websocket.SubscriptionState.*;

@ThreadSafe
public abstract class WebSocketChannel<Message extends WebSocketTableDataMessage<?>>
    extends BaseWebSocketChannel<Message> {

  protected abstract String getTopic();

  @Override
  protected final String getDisplayName() {
    return getTopic();
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    WebSocketRequest request = new WebSocketRequest();
    request.op = _subscribe;
    request.args = ImmutableList.of(getTopic());
    session.send(request);
    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    WebSocketRequest request = new WebSocketRequest();
    request.op = _unsubscribe;
    request.args = ImmutableList.of(getTopic());
    session.send(request);
    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (message instanceof WebSocketSubscribeConfirmation) {
      WebSocketSubscribeConfirmation confirmation = (WebSocketSubscribeConfirmation) message;
      if (confirmation.subscribe.equals(getTopic())) {
        return SUBSCRIBED;
      }
    }

    if (message instanceof WebSocketUnsubscribeConfirmation) {
      WebSocketUnsubscribeConfirmation confirmation = (WebSocketUnsubscribeConfirmation) message;
      if (confirmation.unsubscribe.equals(getTopic())) {
        reset();
        return UNSUBSCRIBED;
      }
    }
    return null;
  }

  @Override
  protected final void reset() {}
}
