package io.contek.invoker.coinbasepro.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.coinbasepro.api.websocket.common.WebSocketChannelInfo;
import io.contek.invoker.coinbasepro.api.websocket.common.WebSocketChannelMessage;
import io.contek.invoker.coinbasepro.api.websocket.common.WebSocketSubscriptionMessage;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;

import java.util.concurrent.atomic.AtomicReference;

import static io.contek.invoker.coinbasepro.api.websocket.common.constants.WebSocketMessageKeys.*;
import static io.contek.invoker.commons.websocket.SubscriptionState.*;

public abstract class WebSocketChannel<
        Id extends WebSocketChannelId<Message>, Message extends WebSocketChannelMessage>
    extends BaseWebSocketChannel<Id, Message> {

  private WebSocketSubscriptionMessage pendingRequestHolder = null;

  protected WebSocketChannel(Id id) {
    super(id);
  }

  private static <Id extends WebSocketChannelId<?>> WebSocketChannelInfo getChannelInfo(Id id) {
    WebSocketChannelInfo channel = new WebSocketChannelInfo();
    channel.name = id.getChannel();
    String productId = id.getProductId();
    if (productId != null) {
      channel.product_ids = ImmutableList.of(productId);
    }
    return channel;
  }

  private static <Id extends WebSocketChannelId<?>> boolean hasActiveChannel(
      WebSocketSubscriptionMessage message, Id id) {
    return message.channels.stream().anyMatch(channel -> hasActiveChannel(channel, id));
  }

  private static <Id extends WebSocketChannelId<?>> boolean hasActiveChannel(
      WebSocketChannelInfo channel, Id id) {
    if (!channel.name.equals(id.getChannel())) {
      return false;
    }

    String productId = id.getProductId();
    if (productId == null) {
      return true;
    }

    return channel.product_ids != null && channel.product_ids.contains(productId);
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
      Id id = getId();
      WebSocketSubscriptionMessage request = new WebSocketSubscriptionMessage();
      request.type = _subscribe;
      request.channels = ImmutableList.of(getChannelInfo(id));
      session.send(request);
      pendingRequestHolder = request;
      return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
      Id id = getId();
      WebSocketSubscriptionMessage request = new WebSocketSubscriptionMessage();
      request.type = _unsubscribe;
      request.channels = ImmutableList.of(getChannelInfo(id));
      session.send(request);
      pendingRequestHolder = request;
      return UNSUBSCRIBING;
  }

  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
      WebSocketSubscriptionMessage pendingRequest = pendingRequestHolder;
      if (pendingRequest == null) {
        return null;
      }
      if (!(message instanceof WebSocketSubscriptionMessage)) {
        return null;
      }
      WebSocketSubscriptionMessage casted = (WebSocketSubscriptionMessage) message;
      if (!_subscriptions.equals(casted.type)) {
        return null;
      }

      Id id = getId();
      if (_subscribe.equals(pendingRequest.type)) {
        if (hasActiveChannel(casted, id)) {
          pendingRequestHolder = null;
          return SUBSCRIBED;
        }
        return null;
      }
      if (_unsubscribe.equals(pendingRequest.type)) {
        if (!hasActiveChannel(casted, id)) {
          pendingRequestHolder = null;
          return UNSUBSCRIBED;
        }
        return null;
      }
      throw new IllegalStateException(pendingRequest.type);
  }

  @Override
  protected final void reset() {
      pendingRequestHolder = null;
  }
}
