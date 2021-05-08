package io.contek.invoker.bitmex.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketOperationRequest;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketSubscribeResponse;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketTableDataMessage;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketUnsubscribeResponse;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.bitmex.api.websocket.common.constants.WebSocketRequestOperationKeys._subscribe;
import static io.contek.invoker.bitmex.api.websocket.common.constants.WebSocketRequestOperationKeys._unsubscribe;
import static io.contek.invoker.commons.websocket.SubscriptionState.*;

@ThreadSafe
public abstract class WebSocketChannel<
        Id extends WebSocketChannelId<Message>, Message extends WebSocketTableDataMessage<?>>
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
    if (message instanceof WebSocketSubscribeResponse) {
      Id id = getId();
      WebSocketSubscribeResponse confirmation = (WebSocketSubscribeResponse) message;
      if (confirmation.subscribe.equals(id.getTopic())) {
        return SUBSCRIBED;
      }
    }

    if (message instanceof WebSocketUnsubscribeResponse) {
      Id id = getId();
      WebSocketUnsubscribeResponse confirmation = (WebSocketUnsubscribeResponse) message;
      if (confirmation.unsubscribe.equals(id.getTopic())) {
        reset();
        return UNSUBSCRIBED;
      }
    }
    return null;
  }

  @Override
  protected final void reset() {}
}
