package io.contek.invoker.bybitinverse.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybitinverse.api.websocket.common.WebSocketOperationRequest;
import io.contek.invoker.bybitinverse.api.websocket.common.WebSocketOperationResponse;
import io.contek.invoker.bybitinverse.api.websocket.common.WebSocketTopicMessage;
import io.contek.invoker.bybitinverse.api.websocket.common.constants.WebSocketOperationKeys;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.websocket.SubscriptionState.*;

@ThreadSafe
public abstract class WebSocketChannel<Message extends WebSocketTopicMessage>
    extends BaseWebSocketChannel<WebSocketChannelId<Message>, Message, Message> {

  protected WebSocketChannel(WebSocketChannelId<Message> id) {
    super(id);
  }

  @Override
  protected Message getData(Message message) {
    return message;
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    WebSocketChannelId<Message> id = getId();
    WebSocketOperationRequest request = new WebSocketOperationRequest();
    request.op = WebSocketOperationKeys._subscribe;
    request.args = ImmutableList.of(id.getTopic());
    session.send(request);
    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    WebSocketChannelId<Message> id = getId();
    WebSocketOperationRequest request = new WebSocketOperationRequest();
    request.op = WebSocketOperationKeys._unsubscribe;
    request.args = ImmutableList.of(id.getTopic());
    session.send(request);
    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (message instanceof WebSocketOperationResponse confirmation) {
      WebSocketOperationRequest request = confirmation.request;
      if (!request.args.contains(getId().getTopic())) {
        return null;
      }
      if (!confirmation.success) {
        throw new IllegalStateException();
      }
      return switch (request.op) {
        case WebSocketOperationKeys._subscribe -> SUBSCRIBED;
        case WebSocketOperationKeys._unsubscribe -> UNSUBSCRIBED;
        default -> throw new IllegalStateException();
      };
    }
    return null;
  }

  @Override
  protected final void reset() {}
}
