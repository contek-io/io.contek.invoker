package io.contek.invoker.binanceinverse.api.websocket.user;

import io.contek.invoker.binanceinverse.api.websocket.common.WebSocketEventData;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.websocket.SubscriptionState.SUBSCRIBED;
import static io.contek.invoker.commons.websocket.SubscriptionState.UNSUBSCRIBED;

@ThreadSafe
public abstract class UserWebSocketChannel<Message extends WebSocketEventData>
    extends BaseWebSocketChannel<UserWebSocketChannelId<Message>, Message, Message> {

  public UserWebSocketChannel(UserWebSocketChannelId<Message> id) {
    super(id);
  }

  @Override
  protected final Message getData(Message message) {
    return message;
  }

  // We do no action during the subscription phase since the data will be pushed to our end when
  // opening the web socket connection.
  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    return SUBSCRIBED;
  }

  // We do no action in unsubscription phase since all the user-related channel shared the same
  // underlying web socket connection, and there is no way to unsubscribe to a given topic. Either
  // all user related events will be pushed to us, or the connection is closed altogether.
  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    return UNSUBSCRIBED;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (message instanceof UserDataStreamExpiredEvent) {
      return UNSUBSCRIBED;
    }
    return null;
  }

  @Override
  protected final void reset() {}
}
