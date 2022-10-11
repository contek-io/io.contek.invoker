package io.contek.invoker.okx.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelArg;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelPushData;
import io.contek.invoker.okx.api.websocket.common.WebSocketSubscriptionRequest;
import io.contek.invoker.okx.api.websocket.common.WebSocketSubscriptionResponse;
import io.contek.invoker.okx.api.websocket.common.constants.WebSocketOutboundKeys;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static io.contek.invoker.commons.websocket.SubscriptionState.*;
import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketInboundKeys._subscribe;
import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketInboundKeys._unsubscribe;

@ThreadSafe
public abstract class WebSocketChannel<Message extends WebSocketChannelPushData<?>>
    extends BaseWebSocketChannel<WebSocketChannelId<Message>, Message, Message> {

  private final AtomicReference<WebSocketSubscriptionRequest> pendingRequestHolder =
      new AtomicReference<>(null);

  protected WebSocketChannel(WebSocketChannelId<Message> id) {
    super(id);
  }

  @Override
  protected Message getData(Message message) {
    return message;
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    synchronized (pendingRequestHolder) {
      if (pendingRequestHolder.get() != null) {
        throw new IllegalStateException();
      }

      WebSocketSubscriptionRequest request = new WebSocketSubscriptionRequest();
      request.op = WebSocketOutboundKeys._subscribe;
      request.args = ImmutableList.of(getId().toChannelArg());
      session.send(request);
      pendingRequestHolder.set(request);
    }
    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    synchronized (pendingRequestHolder) {
      if (pendingRequestHolder.get() != null) {
        throw new IllegalStateException();
      }

      WebSocketSubscriptionRequest request = new WebSocketSubscriptionRequest();
      request.op = WebSocketOutboundKeys._unsubscribe;
      request.args = ImmutableList.of(getId().toChannelArg());
      session.send(request);
      pendingRequestHolder.set(request);
    }
    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (!(message instanceof WebSocketSubscriptionResponse response)) {
      return null;
    }

    synchronized (pendingRequestHolder) {
      WebSocketSubscriptionRequest request = pendingRequestHolder.get();
      if (request == null) {
        return null;
      }

      WebSocketChannelArg requestArg = request.args.get(0);
      WebSocketChannelArg responseArg = response.arg;
      if (!Objects.equals(requestArg.channel, responseArg.channel)
          || !Objects.equals(requestArg.instType, responseArg.instType)
          || !Objects.equals(requestArg.uly, responseArg.uly)
          || !Objects.equals(requestArg.instId, responseArg.instId)) {
        return null;
      }

      reset();
      return switch (response.event) {
        case _subscribe -> SUBSCRIBED;
        case _unsubscribe -> UNSUBSCRIBED;
        default -> throw new IllegalArgumentException(response.event);
      };
    }
  }

  @Override
  protected final void reset() {
    synchronized (pendingRequestHolder) {
      pendingRequestHolder.set(null);
    }
  }
}
