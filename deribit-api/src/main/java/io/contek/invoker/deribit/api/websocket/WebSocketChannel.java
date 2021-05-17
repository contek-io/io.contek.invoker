package io.contek.invoker.deribit.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.deribit.api.websocket.common.SubscriptionParams;
import io.contek.invoker.deribit.api.websocket.common.WebSocketChannelMessage;
import io.contek.invoker.deribit.api.websocket.common.WebSocketRequest;
import io.contek.invoker.deribit.api.websocket.common.WebSocketResponse;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

import static io.contek.invoker.commons.websocket.SubscriptionState.*;
import static io.contek.invoker.deribit.api.websocket.common.constants.WebSocketOutboundKeys._subscribe;
import static io.contek.invoker.deribit.api.websocket.common.constants.WebSocketOutboundKeys._unsubscribe;

@ThreadSafe
public abstract class WebSocketChannel<
        Id extends WebSocketChannelId<Message>, Message extends WebSocketChannelMessage<?>>
    extends BaseWebSocketChannel<Id, Message> {

  private final String scope;
  private final WebSocketRequestIdGenerator requestIdGenerator;

  private final AtomicReference<WebSocketRequest<SubscriptionParams>> pendingRequestHolder =
      new AtomicReference<>();

  protected WebSocketChannel(Id id, String scope, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id);
    this.scope = scope;
    this.requestIdGenerator = requestIdGenerator;
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    synchronized (pendingRequestHolder) {
      if (pendingRequestHolder.get() != null) {
        throw new IllegalStateException();
      }
      Id id = getId();
      SubscriptionParams params = new SubscriptionParams();
      params.channels = ImmutableList.of(id.getChannel());

      WebSocketRequest<SubscriptionParams> request = new WebSocketRequest<>();
      request.id = requestIdGenerator.getNextRequestId();
      request.method = getSubscribeMethod();
      request.params = params;
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
      Id id = getId();
      SubscriptionParams params = new SubscriptionParams();
      params.channels = ImmutableList.of(id.getChannel());

      WebSocketRequest<SubscriptionParams> request = new WebSocketRequest<>();
      request.id = requestIdGenerator.getNextRequestId();
      request.method = getUnsubscribeMethod();
      request.params = params;
      session.send(request);
      pendingRequestHolder.set(request);
    }

    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (!(message instanceof WebSocketResponse)) {
      return null;
    }

    synchronized (pendingRequestHolder) {
      WebSocketRequest<SubscriptionParams> command = pendingRequestHolder.get();
      if (command == null) {
        return null;
      }

      WebSocketResponse confirmation = (WebSocketResponse) message;
      if (confirmation.id == null
          || !confirmation.id.equals(command.id)
          || confirmation.result.isEmpty()) {
        return null;
      }

      reset();
      if (command.method.equals(getSubscribeMethod())) {
        return SUBSCRIBED;
      }
      if (command.method.equals(getUnsubscribeMethod())) {
        return UNSUBSCRIBED;
      }
      throw new IllegalStateException();
    }
  }

  @Override
  protected final void reset() {
    synchronized (pendingRequestHolder) {
      pendingRequestHolder.set(null);
    }
  }

  private String getSubscribeMethod() {
    return scope + '/' + _subscribe;
  }

  private String getUnsubscribeMethod() {
    return scope + '/' + _unsubscribe;
  }
}
