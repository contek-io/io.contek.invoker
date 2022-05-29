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

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static io.contek.invoker.commons.websocket.SubscriptionState.*;
import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketInboundKeys._subscribe;
import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketInboundKeys._unsubscribe;

public abstract class WebSocketChannel<
        Id extends WebSocketChannelId<Message>, Message extends WebSocketChannelPushData<?>>
    extends BaseWebSocketChannel<Id, Message> {

  private final AtomicReference<WebSocketSubscriptionRequest> pendingRequestHolder =
      new AtomicReference<>(null);

  protected WebSocketChannel(Id id) {
    super(id);
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

  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (!(message instanceof WebSocketSubscriptionResponse)) {
      return null;
    }

    WebSocketSubscriptionResponse response = (WebSocketSubscriptionResponse) message;
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
      switch (response.event) {
        case _subscribe:
          return SUBSCRIBED;
        case _unsubscribe:
          return UNSUBSCRIBED;
        default:
          throw new IllegalArgumentException(response.event);
      }
    }
  }

  @Override
  protected final void reset() {
    synchronized (pendingRequestHolder) {
      pendingRequestHolder.set(null);
    }
  }
}
