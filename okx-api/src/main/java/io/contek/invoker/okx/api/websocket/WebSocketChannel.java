package io.contek.invoker.okx.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelPushData;
import io.contek.invoker.okx.api.websocket.common.WebSocketSubscriptionRequest;
import io.contek.invoker.okx.api.websocket.common.WebSocketSubscriptionResponse;
import io.contek.invoker.okx.api.websocket.common.constants.WebSocketOutboundKeys;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.websocket.SubscriptionState.*;
import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketInboundKeys._subscribe;
import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketInboundKeys._unsubscribe;

@ThreadSafe
public abstract class WebSocketChannel<
        Id extends WebSocketChannelId<Message>, Message extends WebSocketChannelPushData<?>>
    extends BaseWebSocketChannel<Id, Message> {

  protected WebSocketChannel(Id id) {
    super(id);
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    WebSocketSubscriptionRequest request = new WebSocketSubscriptionRequest();
    request.op = WebSocketOutboundKeys._subscribe;
    request.args = ImmutableList.of(getId().toChannelArg());
    session.send(request);
    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    WebSocketSubscriptionRequest request = new WebSocketSubscriptionRequest();
    request.op = WebSocketOutboundKeys._unsubscribe;
    request.args = ImmutableList.of(getId().toChannelArg());
    session.send(request);
    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (message instanceof WebSocketSubscriptionResponse) {
      WebSocketSubscriptionResponse confirmation = (WebSocketSubscriptionResponse) message;
      if (!getId().getChannel().equals(confirmation.arg.channel)) {
        return null;
      }
      if (!getId().getChannel().equals(confirmation.arg.channel)) {
        return null;
      }

      switch (confirmation.event) {
        case _subscribe:
          return SUBSCRIBED;
        case _unsubscribe:
          return UNSUBSCRIBED;
        default:
          throw new IllegalArgumentException(confirmation.event);
      }
    }
    return null;
  }

  @Override
  protected final void reset() {}
}
