package io.contek.invoker.hbdminverse.api.websocket.common.notification;

import io.contek.invoker.commons.websocket.*;

import java.util.concurrent.atomic.AtomicReference;

import static io.contek.invoker.commons.websocket.SubscriptionState.*;
import static io.contek.invoker.hbdminverse.api.websocket.user.constants.OpKeys._sub;
import static io.contek.invoker.hbdminverse.api.websocket.user.constants.OpKeys._unsub;

public abstract class NotificationWebSocketChannel<
        Id extends NotificationWebSocketChannelId<Message>,
        Message extends NotificationWebSocketChannelMessage>
    extends BaseWebSocketChannel<Id, Message> {

  private final NotificationWebSocketRequestIdGenerator requestIdGenerator;

  private NotificationWebSocketRequest pendingRequestHolder = null;

  protected NotificationWebSocketChannel(
      Id id, NotificationWebSocketRequestIdGenerator requestIdGenerator) {
    super(id);
    this.requestIdGenerator = requestIdGenerator;
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
      if (pendingRequestHolder != null) {
        throw new IllegalStateException();
      }

      Id id = getId();
      NotificationWebSocketSubscriptionRequest request =
          new NotificationWebSocketSubscriptionRequest();
      request.op = _sub;
      request.topic = id.getTopic();
      request.cid = requestIdGenerator.generateNext();
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
      NotificationWebSocketSubscriptionRequest request =
          new NotificationWebSocketSubscriptionRequest();
      request.op = _unsub;
      request.topic = id.getTopic();
      request.cid = requestIdGenerator.generateNext();
      session.send(request);
      pendingRequestHolder = request;

    return UNSUBSCRIBING;
  }

  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (!(message instanceof NotificationWebSocketConfirmation)) {
      return null;
    }
    NotificationWebSocketConfirmation confirmation = (NotificationWebSocketConfirmation) message;

      NotificationWebSocketRequest request = pendingRequestHolder;
      if (request == null) {
        return null;
      }

      if (!request.cid.equals(confirmation.cid)) {
        return null;
      }

      if (confirmation.err_code != 0) {
        throw new WebSocketIllegalMessageException(
            confirmation.err_code + ": " + confirmation.err_msg);
      }

      reset();
    return switch (request.op) {
      case _sub -> SUBSCRIBED;
      case _unsub -> UNSUBSCRIBED;
      default -> throw new IllegalStateException();
    };
  }

  @Override
  protected final void reset() {
      pendingRequestHolder = null;
  }
}
