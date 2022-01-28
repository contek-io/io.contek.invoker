package io.contek.invoker.okx.api.websocket;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelMessage;
import io.contek.invoker.okx.api.websocket.common.WebSocketSubscriptionResponse;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.websocket.SubscriptionState.SUBSCRIBED;
import static io.contek.invoker.commons.websocket.SubscriptionState.UNSUBSCRIBED;
import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketInboundKeys._subscribed;
import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketInboundKeys._unsubscribed;

@ThreadSafe
public abstract class WebSocketChannel<
        Id extends WebSocketChannelId<Message>, Message extends WebSocketChannelMessage<?>>
    extends BaseWebSocketChannel<Id, Message> {

  protected WebSocketChannel(Id id) {
    super(id);
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (message instanceof WebSocketSubscriptionResponse) {
      WebSocketSubscriptionResponse confirmation = (WebSocketSubscriptionResponse) message;
      if (!getId().getChannel().equals(confirmation.channel)) {
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

  @Override
  protected final void reset() {}
}
