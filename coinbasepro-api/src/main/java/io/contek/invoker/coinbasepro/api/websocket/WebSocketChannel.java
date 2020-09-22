package io.contek.invoker.coinbasepro.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.coinbasepro.api.websocket.common.WebSocketChannelInfo;
import io.contek.invoker.coinbasepro.api.websocket.common.WebSocketMessage;
import io.contek.invoker.coinbasepro.api.websocket.common.WebSocketSubscriptionMessage;
import io.contek.invoker.coinbasepro.api.websocket.common.constants.WebSocketMessageTypes;
import io.contek.invoker.commons.api.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.api.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.api.websocket.SubscriptionState;
import io.contek.invoker.commons.api.websocket.WebSocketSession;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

import static io.contek.invoker.commons.api.websocket.SubscriptionState.*;

@ThreadSafe
public abstract class WebSocketChannel<Message extends WebSocketMessage>
    extends BaseWebSocketChannel<Message> {

  private final String name;
  private final String productId;

  private final AtomicReference<WebSocketSubscriptionMessage> pendingRequestHolder =
      new AtomicReference<>();

  protected WebSocketChannel(String name, String productId) {
    this.name = name;
    this.productId = productId;
  }

  @Override
  protected final String getDisplayName() {
    return name + ':' + productId;
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    synchronized (pendingRequestHolder) {
      WebSocketSubscriptionMessage request = new WebSocketSubscriptionMessage();
      request.type = WebSocketMessageTypes.subscribe;
      request.channels = ImmutableList.of(getChannelInfo());
      session.send(request);
      pendingRequestHolder.set(request);
      return SUBSCRIBING;
    }
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    synchronized (pendingRequestHolder) {
      WebSocketSubscriptionMessage request = new WebSocketSubscriptionMessage();
      request.type = WebSocketMessageTypes.unsubscribe;
      request.channels = ImmutableList.of(getChannelInfo());
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
      if (!WebSocketMessageTypes.subscriptions.equals(casted.type)) {
        return null;
      }

      if (WebSocketMessageTypes.subscribe.equals(pendingRequest.type)) {
        if (casted.channels.stream()
            .anyMatch(
                channel -> channel.name.equals(name) && channel.product_ids.contains(productId))) {
          pendingRequestHolder.set(null);
          return SUBSCRIBED;
        }
        return null;
      }
      if (WebSocketMessageTypes.unsubscribe.equals(pendingRequest.type)) {
        if (casted.channels.stream()
            .anyMatch(
                channel -> channel.name.equals(name) && channel.product_ids.contains(productId))) {
          return null;
        }
        pendingRequestHolder.set(null);
        return UNSUBSCRIBED;
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

  private WebSocketChannelInfo getChannelInfo() {
    WebSocketChannelInfo channel = new WebSocketChannelInfo();
    channel.name = name;
    channel.product_ids = ImmutableList.of(productId);
    return channel;
  }
}
