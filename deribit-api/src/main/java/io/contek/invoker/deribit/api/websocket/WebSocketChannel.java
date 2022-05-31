package io.contek.invoker.deribit.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.websocket.*;
import io.contek.invoker.deribit.api.websocket.common.SubscriptionParams;
import io.contek.invoker.deribit.api.websocket.common.WebSocketRequest;
import io.contek.invoker.deribit.api.websocket.common.WebSocketSingleChannelMessage;
import io.contek.invoker.deribit.api.websocket.common.WebSocketSubscriptionConfirmation;

import java.util.concurrent.atomic.AtomicReference;

import static io.contek.invoker.commons.websocket.SubscriptionState.*;
import static io.contek.invoker.deribit.api.websocket.common.constants.WebSocketOutboundKeys._subscribe;
import static io.contek.invoker.deribit.api.websocket.common.constants.WebSocketOutboundKeys._unsubscribe;

public abstract class WebSocketChannel<
        Id extends WebSocketChannelId<Message>, Message extends WebSocketSingleChannelMessage<?>>
    extends BaseWebSocketChannel<Id, Message> {

  private final String scope;
  private final WebSocketRequestIdGenerator requestIdGenerator;

  private WebSocketRequest<SubscriptionParams> pendingRequestHolder = null;

  protected WebSocketChannel(Id id, String scope, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id);
    this.scope = scope;
    this.requestIdGenerator = requestIdGenerator;
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
      if (pendingRequestHolder != null) {
        throw new IllegalStateException();
      }
      Id id = getId();
      SubscriptionParams params = new SubscriptionParams();
      params.channels = ImmutableList.of(id.getChannel());

      WebSocketRequest<SubscriptionParams> request = new WebSocketRequest<>();
      request.id = requestIdGenerator.getNextRequestId(WebSocketSubscriptionConfirmation.class);
      request.method = getSubscribeMethod();
      request.params = params;
      session.send(request);
      pendingRequestHolder = request;
    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
      if (pendingRequestHolder != null) {
        throw new IllegalStateException();
      }
      Id id = getId();
      SubscriptionParams params = new SubscriptionParams();
      params.channels = ImmutableList.of(id.getChannel());

      WebSocketRequest<SubscriptionParams> request = new WebSocketRequest<>();
      request.id = requestIdGenerator.getNextRequestId(WebSocketSubscriptionConfirmation.class);
      request.method = getUnsubscribeMethod();
      request.params = params;
      session.send(request);
      pendingRequestHolder = request;

    return UNSUBSCRIBING;
  }

  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (!(message instanceof WebSocketSubscriptionConfirmation)) {
      return null;
    }

      WebSocketRequest<SubscriptionParams> command = pendingRequestHolder;
      if (command == null) {
        return null;
      }

      WebSocketSubscriptionConfirmation confirmation = (WebSocketSubscriptionConfirmation) message;
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

  @Override
  protected final void reset() {
      pendingRequestHolder = null;
  }

  private String getSubscribeMethod() {
    return scope + '/' + _subscribe;
  }

  private String getUnsubscribeMethod() {
    return scope + '/' + _unsubscribe;
  }
}
