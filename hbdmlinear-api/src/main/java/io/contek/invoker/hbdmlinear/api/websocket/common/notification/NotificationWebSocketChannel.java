package io.contek.invoker.hbdmlinear.api.websocket.common.notification;

import io.contek.invoker.commons.websocket.*;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

import static io.contek.invoker.commons.websocket.SubscriptionState.*;
import static io.contek.invoker.hbdmlinear.api.websocket.user.constants.OpKeys._sub;
import static io.contek.invoker.hbdmlinear.api.websocket.user.constants.OpKeys._unsub;

@ThreadSafe
public abstract class NotificationWebSocketChannel<
        Id extends NotificationWebSocketChannelId<Message>,
        Message extends NotificationWebSocketChannelMessage>
    extends BaseWebSocketChannel<Id, Message> {

  private final NotificationWebSocketRequestIdGenerator requestIdGenerator;

  private final AtomicReference<NotificationWebSocketRequest> pendingCommandHolder =
      new AtomicReference<>(null);

  protected NotificationWebSocketChannel(
      Id id, NotificationWebSocketRequestIdGenerator requestIdGenerator) {
    super(id);
    this.requestIdGenerator = requestIdGenerator;
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    synchronized (pendingCommandHolder) {
      if (pendingCommandHolder.get() != null) {
        throw new IllegalStateException();
      }
    }

    Id id = getId();
    NotificationWebSocketSubscriptionRequest request =
        new NotificationWebSocketSubscriptionRequest();
    request.op = _sub;
    request.topic = id.getTopic();
    request.cid = requestIdGenerator.generateNext();
    session.send(request);
    return UNSUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    synchronized (pendingCommandHolder) {
      if (pendingCommandHolder.get() != null) {
        throw new IllegalStateException();
      }
    }

    Id id = getId();
    NotificationWebSocketSubscriptionRequest request =
        new NotificationWebSocketSubscriptionRequest();
    request.op = _unsub;
    request.topic = id.getTopic();
    request.cid = requestIdGenerator.generateNext();
    session.send(request);
    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (!(message instanceof NotificationWebSocketResponse)) {
      return null;
    }
    NotificationWebSocketResponse<?> response = (NotificationWebSocketResponse<?>) message;

    synchronized (pendingCommandHolder) {
      NotificationWebSocketRequest request = pendingCommandHolder.get();
      if (request == null) {
        return null;
      }

      if (!request.cid.equals(response.cid)) {
        return null;
      }

      if (response.err_code != 0) {
        throw new WebSocketIllegalMessageException(response.err_code + ": " + response.err_msg);
      }

      reset();
      switch (request.op) {
        case _sub:
          return SUBSCRIBED;
        case _unsub:
          return UNSUBSCRIBED;
        default:
          throw new IllegalStateException();
      }
    }
  }

  @Override
  protected final void reset() {
    synchronized (pendingCommandHolder) {
      pendingCommandHolder.set(null);
    }
  }
}
