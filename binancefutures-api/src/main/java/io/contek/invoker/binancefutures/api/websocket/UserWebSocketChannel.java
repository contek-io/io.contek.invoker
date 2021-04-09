package io.contek.invoker.binancefutures.api.websocket;

import io.contek.invoker.binancefutures.api.websocket.user.UserDataStreamExpiredEvent;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;

import javax.annotation.Nullable;

public abstract class UserWebSocketChannel<Message> extends BaseWebSocketChannel<Message> {

  // We do no action during the subscription phase since the data will be pushed to our end when
  // opening the web socket connection.
  @Override
  protected SubscriptionState subscribe(WebSocketSession session) {
    return SubscriptionState.SUBSCRIBED;
  }

  // We do no action in unsubscription phase since all the user-related channel shared the same
  // underlying web socket connection, and there is no way to unsubscribe to a given topic. Either
  // all user related events will be pushed to us, or the connection is closed altogether.
  @Override
  protected SubscriptionState unsubscribe(WebSocketSession session) {
    return SubscriptionState.UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected SubscriptionState getState(AnyWebSocketMessage message) {
    if (message instanceof UserDataStreamExpiredEvent) {
      return SubscriptionState.UNSUBSCRIBED;
    }
    return null;
  }

  @Override
  protected void reset() {}
}
