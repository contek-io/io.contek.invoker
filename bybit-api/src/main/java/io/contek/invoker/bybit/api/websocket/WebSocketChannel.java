package io.contek.invoker.bybit.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.websocket.common.WebSocketOperationRequest;
import io.contek.invoker.bybit.api.websocket.common.WebSocketOperationResponse;
import io.contek.invoker.bybit.api.websocket.common.WebSocketTopicMessage;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.bybit.api.websocket.common.constants.WebSocketOperationKeys._subscribe;
import static io.contek.invoker.bybit.api.websocket.common.constants.WebSocketOperationKeys._unsubscribe;
import static io.contek.invoker.commons.websocket.SubscriptionState.*;

@ThreadSafe
public abstract class WebSocketChannel<Message extends WebSocketTopicMessage>
    extends BaseWebSocketChannel<Message> {

  protected abstract String getTopic();

  @Override
  protected final String getDisplayName() {
    return getTopic();
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    WebSocketOperationRequest request = new WebSocketOperationRequest();
    request.op = _subscribe;
    request.args = ImmutableList.of(getTopic());
    session.send(request);
    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    WebSocketOperationRequest request = new WebSocketOperationRequest();
    request.op = _unsubscribe;
    request.args = ImmutableList.of(getTopic());
    session.send(request);
    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (message instanceof WebSocketOperationResponse) {
      WebSocketOperationResponse confirmation = (WebSocketOperationResponse) message;
      WebSocketOperationRequest request = confirmation.request;
      if (!request.args.contains(getTopic())) {
        return null;
      }
      if (!confirmation.success) {
        throw new IllegalStateException();
      }
      switch (request.op) {
        case _subscribe:
          return SUBSCRIBED;
        case _unsubscribe:
          return UNSUBSCRIBED;
        default:
          throw new IllegalStateException();
      }
    }
    return null;
  }

  @Override
  protected final void reset() {}

  @Override
  public void sendPing(WebSocketSession session) {
    super.sendPing(session);
  }
}
