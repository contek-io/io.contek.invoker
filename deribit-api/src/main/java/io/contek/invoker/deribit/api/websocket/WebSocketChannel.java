package io.contek.invoker.deribit.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.websocket.*;
import io.contek.invoker.deribit.api.websocket.common.SubscriptionParams;
import io.contek.invoker.deribit.api.websocket.common.WebSocketRequest;
import io.contek.invoker.deribit.api.websocket.common.WebSocketSingleChannelMessage;
import io.contek.invoker.deribit.api.websocket.common.WebSocketSubscriptionConfirmation;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

import static io.contek.invoker.commons.websocket.SubscriptionState.*;
import static io.contek.invoker.deribit.api.websocket.common.constants.WebSocketOutboundKeys._subscribe;
import static io.contek.invoker.deribit.api.websocket.common.constants.WebSocketOutboundKeys._unsubscribe;

@ThreadSafe
public abstract class WebSocketChannel<Message extends WebSocketSingleChannelMessage<Data>, Data>
    extends BaseWebSocketChannel<WebSocketChannelId<Message>, Message, Data> {

  private final String scope;
  private final WebSocketRequestIdGenerator requestIdGenerator;

  private final AtomicReference<WebSocketRequest<SubscriptionParams>> pendingRequestHolder =
      new AtomicReference<>();

  protected WebSocketChannel(
      WebSocketChannelId<Message> id,
      String scope,
      WebSocketRequestIdGenerator requestIdGenerator) {
    super(id);
    this.scope = scope;
    this.requestIdGenerator = requestIdGenerator;
  }

  @Override
  protected final Data getData(Message message) {
    return message.params.data;
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    synchronized (pendingRequestHolder) {
      if (pendingRequestHolder.get() != null) {
        throw new IllegalStateException();
      }

      WebSocketChannelId<Message> id = getId();
      SubscriptionParams params = new SubscriptionParams();
      params.channels = ImmutableList.of(id.getChannel());

      WebSocketRequest<SubscriptionParams> request = new WebSocketRequest<>();
      request.id = requestIdGenerator.getNextRequestId(WebSocketSubscriptionConfirmation.class);
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

      WebSocketChannelId<Message> id = getId();
      SubscriptionParams params = new SubscriptionParams();
      params.channels = ImmutableList.of(id.getChannel());

      WebSocketRequest<SubscriptionParams> request = new WebSocketRequest<>();
      request.id = requestIdGenerator.getNextRequestId(WebSocketSubscriptionConfirmation.class);
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
    if (!(message instanceof WebSocketSubscriptionConfirmation confirmation)) {
      return null;
    }

    synchronized (pendingRequestHolder) {
      WebSocketRequest<SubscriptionParams> command = pendingRequestHolder.get();
      if (command == null) {
        return null;
      }

      if (confirmation.id == null || !confirmation.id.equals(command.id)) {
        return null;
      }

      if (confirmation.error != null) {
        throw new WebSocketIllegalMessageException(
            confirmation.error.code + ": " + confirmation.error.message);
      }

      if (confirmation.result == null || confirmation.result.isEmpty()) {
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
