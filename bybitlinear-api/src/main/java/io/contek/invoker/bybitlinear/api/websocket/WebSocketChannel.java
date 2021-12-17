package io.contek.invoker.bybitlinear.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybitlinear.api.websocket.common.WebSocketOperationRequest;
import io.contek.invoker.bybitlinear.api.websocket.common.WebSocketOperationResponse;
import io.contek.invoker.bybitlinear.api.websocket.common.WebSocketTopicMessage;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.bybitlinear.api.websocket.common.constants.WebSocketOperationKeys._subscribe;
import static io.contek.invoker.bybitlinear.api.websocket.common.constants.WebSocketOperationKeys._unsubscribe;
import static io.contek.invoker.commons.websocket.SubscriptionState.*;

@ThreadSafe
public abstract class WebSocketChannel<
        Id extends WebSocketChannelId<Message>, Message extends WebSocketTopicMessage>
    extends BaseWebSocketChannel<Id, Message> {

  protected WebSocketChannel(Id id) {
    super(id);
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    Id id = getId();
    WebSocketOperationRequest request = new WebSocketOperationRequest();
    request.op = _subscribe;
    request.args = ImmutableList.of(id.getTopic());
    session.send(request);
    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    Id id = getId();
    WebSocketOperationRequest request = new WebSocketOperationRequest();
    request.op = _unsubscribe;
    request.args = ImmutableList.of(id.getTopic());
    session.send(request);
    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (message instanceof WebSocketOperationResponse) {
      WebSocketOperationResponse confirmation = (WebSocketOperationResponse) message;
      WebSocketOperationRequest request = confirmation.request;
      if (!request.args.contains(getId().getTopic())) {
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
}
