package io.contek.invoker.bitstamp.api.websocket;

import com.google.common.collect.ImmutableMap;
import io.contek.invoker.bitstamp.api.websocket.common.WebSocketChannelMessage;
import io.contek.invoker.bitstamp.api.websocket.common.WebSocketRequestConfirmationMessage;
import io.contek.invoker.bitstamp.api.websocket.common.WebSocketRequestMessage;
import io.contek.invoker.commons.api.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.api.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.api.websocket.SubscriptionState;
import io.contek.invoker.commons.api.websocket.WebSocketSession;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.bitstamp.api.websocket.common.constants.WebSocketEvents.*;
import static io.contek.invoker.bitstamp.api.websocket.common.constants.WebSocketFields.channel;
import static io.contek.invoker.commons.api.websocket.SubscriptionState.*;

@ThreadSafe
public abstract class WebSocketChannel<Message extends WebSocketChannelMessage<?>>
    extends BaseWebSocketChannel<Message> {

  private final String channelName;

  protected WebSocketChannel(String channelName) {
    this.channelName = channelName;
  }

  @Override
  protected final String getDisplayName() {
    return channelName;
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    WebSocketRequestMessage request = new WebSocketRequestMessage();
    request.event = bts_subscribe;
    request.data = ImmutableMap.of(channel, channelName);
    session.send(request);
    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    WebSocketRequestMessage request = new WebSocketRequestMessage();
    request.event = bts_unsubscribe;
    request.data = ImmutableMap.of(channel, channelName);
    session.send(request);
    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (!(message instanceof WebSocketRequestConfirmationMessage)) {
      return null;
    }
    WebSocketRequestConfirmationMessage casted = (WebSocketRequestConfirmationMessage) message;
    if (!channelName.equals(casted.channel)) {
      return null;
    }
    if (bts_subscription_succeeded.equals(casted.event)) {
      return SUBSCRIBED;
    }
    if (bts_unsubscription_succeeded.equals(casted.event)) {
      return UNSUBSCRIBED;
    }
    throw new IllegalStateException(casted.event);
  }

  @Override
  protected final boolean accepts(Message message) {
    return channelName.equals(message.channel);
  }

  @Override
  protected final void reset() {}
}
