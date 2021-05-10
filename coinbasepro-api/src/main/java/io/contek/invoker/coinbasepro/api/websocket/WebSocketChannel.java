package io.contek.invoker.coinbasepro.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.coinbasepro.api.websocket.common.WebSocketChannelInfo;
import io.contek.invoker.coinbasepro.api.websocket.common.WebSocketChannelMessage;
import io.contek.invoker.coinbasepro.api.websocket.common.WebSocketSubscriptionMessage;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

import static io.contek.invoker.coinbasepro.api.websocket.common.constants.WebSocketMessageKeys.*;
import static io.contek.invoker.commons.websocket.SubscriptionState.*;

@ThreadSafe
public abstract class WebSocketChannel<
        Id extends WebSocketChannelId<Message>, Message extends WebSocketChannelMessage>
    extends BaseWebSocketChannel<Id, Message> {

  private final AtomicReference<WebSocketSubscriptionMessage> pendingRequestHolder =
      new AtomicReference<>();

  protected WebSocketChannel(Id id) {
    super(id);
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    synchronized (pendingRequestHolder) {
      Id id = getId();
      WebSocketSubscriptionMessage request = new WebSocketSubscriptionMessage();
      request.type = _subscribe;
      request.channels = ImmutableList.of(getChannelInfo(id));
      session.send(request);
      pendingRequestHolder.set(request);
      return SUBSCRIBING;
    }
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    synchronized (pendingRequestHolder) {
      Id id = getId();
      WebSocketSubscriptionMessage request = new WebSocketSubscriptionMessage();
      request.type = _unsubscribe;
      request.channels = ImmutableList.of(getChannelInfo(id));
      session.send(request);
      pendingRequestHolder.set(request);
      return UNSUBSCRIBING;
    }
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    synchronized (pendingRequestHolder) {
      WebSocketSubscriptionMessage pendingRequest = pendingRequestHolder.get();
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
          pendingRequestHolder.set(null);
          return SUBSCRIBED;
        }
        return null;
      }
      if (_unsubscribe.equals(pendingRequest.type)) {
        if (!hasActiveChannel(casted, id)) {
          pendingRequestHolder.set(null);
          return UNSUBSCRIBED;
        }
        return null;
      }
      throw new IllegalStateException(pendingRequest.type);
    }
  }

  @Override
  protected final void reset() {
    synchronized (pendingRequestHolder) {
      pendingRequestHolder.set(null);
    }
  }

  private static <Id extends WebSocketChannelId<?>> WebSocketChannelInfo getChannelInfo(Id id) {
    WebSocketChannelInfo channel = new WebSocketChannelInfo();
    channel.name = id.getType();
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
    if (!channel.name.equals(id.getType())) {
      return false;
    }

    String productId = id.getProductId();
    if (productId == null) {
      return true;
    }

    return channel.product_ids != null && channel.product_ids.contains(productId);
  }
}
