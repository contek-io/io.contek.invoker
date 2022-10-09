package io.contek.invoker.bitstamp.api.websocket;

import com.google.common.collect.ImmutableMap;
import io.contek.invoker.bitstamp.api.websocket.common.WebSocketChannelMessage;
import io.contek.invoker.bitstamp.api.websocket.common.WebSocketRequestConfirmationMessage;
import io.contek.invoker.bitstamp.api.websocket.common.WebSocketRequestMessage;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.bitstamp.api.websocket.common.constants.WebSocketEventKeys.*;
import static io.contek.invoker.bitstamp.api.websocket.common.constants.WebSocketFieldKeys._channel;
import static io.contek.invoker.commons.websocket.SubscriptionState.*;

@ThreadSafe
public abstract class WebSocketChannel<Message extends WebSocketChannelMessage<Data>, Data>
    extends BaseWebSocketChannel<WebSocketChannelId<Message>, Message, Data> {

  protected WebSocketChannel(WebSocketChannelId<Message> id) {
    super(id);
  }

  @Override
  protected final Data getData(Message message) {
    return message.data;
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    WebSocketChannelId<Message> id = getId();
    WebSocketRequestMessage request = new WebSocketRequestMessage();
    request.event = _bts_subscribe;
    request.data = ImmutableMap.of(_channel, id.getChannel());
    session.send(request);
    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    WebSocketChannelId<Message> id = getId();
    WebSocketRequestMessage request = new WebSocketRequestMessage();
    request.event = _bts_unsubscribe;
    request.data = ImmutableMap.of(_channel, id.getChannel());
    session.send(request);
    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (!(message instanceof WebSocketRequestConfirmationMessage casted)) {
      return null;
    }

    WebSocketChannelId<Message> id = getId();
    if (!id.getChannel().equals(casted.channel)) {
      return null;
    }
    if (_bts_subscription_succeeded.equals(casted.event)) {
      return SUBSCRIBED;
    }
    if (_bts_unsubscription_succeeded.equals(casted.event)) {
      return UNSUBSCRIBED;
    }
    throw new IllegalStateException(casted.event);
  }

  @Override
  protected final void reset() {}
}
