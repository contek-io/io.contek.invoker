package io.contek.invoker.hbdmlinear.api.websocket;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketMarketDataMessage;
import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketSubscribeRequest;
import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketUnsubscribeRequest;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.websocket.SubscriptionState.SUBSCRIBING;
import static io.contek.invoker.commons.websocket.SubscriptionState.UNSUBSCRIBING;

@ThreadSafe
public abstract class WebSocketChannel<Message extends WebSocketMarketDataMessage<?>>
    extends BaseWebSocketChannel<Message> {

  protected abstract String getTopic();

  @Override
  protected final String getDisplayName() {
    return getTopic();
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    WebSocketSubscribeRequest request = new WebSocketSubscribeRequest();
    request.sub = getTopic();
    session.send(request);
    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    WebSocketUnsubscribeRequest request = new WebSocketUnsubscribeRequest();
    request.unsub = getTopic();
    session.send(request);
    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    return null;
  }

  @Override
  protected final void reset() {}
}
