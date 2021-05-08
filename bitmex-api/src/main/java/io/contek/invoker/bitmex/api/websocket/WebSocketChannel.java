package io.contek.invoker.bitmex.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketOperationRequest;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketSubscribeResponse;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketTableDataMessage;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketUnsubscribeResponse;
import io.contek.invoker.commons.websocket.*;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.bitmex.api.websocket.common.constants.WebSocketRequestOperationKeys._subscribe;
import static io.contek.invoker.bitmex.api.websocket.common.constants.WebSocketRequestOperationKeys._unsubscribe;
import static io.contek.invoker.commons.websocket.SubscriptionState.*;

@ThreadSafe
public abstract class WebSocketChannel<Message extends WebSocketTableDataMessage<?>>
    extends BaseWebSocketChannel<Message> {

  public WebSocketChannel() {
    super(id);
  }

  protected abstract String getTopic();

  @Override
  protected final BaseWebSocketChannelId getId() {
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
    if (message instanceof WebSocketSubscribeResponse) {
      WebSocketSubscribeResponse confirmation = (WebSocketSubscribeResponse) message;
      if (confirmation.subscribe.equals(getTopic())) {
        return SUBSCRIBED;
      }
    }

    if (message instanceof WebSocketUnsubscribeResponse) {
      WebSocketUnsubscribeResponse confirmation = (WebSocketUnsubscribeResponse) message;
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
