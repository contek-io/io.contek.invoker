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
        Message extends NotificationWebSocketDataMessage<Data>, Data>
    extends BaseWebSocketChannel<NotificationWebSocketChannelId<Message>, Message, Data> {

  private final NotificationWebSocketRequestIdGenerator requestIdGenerator;

  private final AtomicReference<NotificationWebSocketRequest> pendingRequestHolder =
      new AtomicReference<>(null);

  protected NotificationWebSocketChannel(
      NotificationWebSocketChannelId<Message> id,
      NotificationWebSocketRequestIdGenerator requestIdGenerator) {
    super(id);
    this.requestIdGenerator = requestIdGenerator;
  }

  @Override
  protected final Data getData(Message message) {
    return message.data;
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    synchronized (pendingRequestHolder) {
      if (pendingRequestHolder.get() != null) {
        throw new IllegalStateException();
      }

      NotificationWebSocketChannelId<Message> id = getId();
      NotificationWebSocketSubscriptionRequest request =
          new NotificationWebSocketSubscriptionRequest();
      request.op = _sub;
      request.topic = id.getTopic();
      request.cid = requestIdGenerator.generateNext();
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

      NotificationWebSocketChannelId<Message> id = getId();
      NotificationWebSocketSubscriptionRequest request =
          new NotificationWebSocketSubscriptionRequest();
      request.op = _unsub;
      request.topic = id.getTopic();
      request.cid = requestIdGenerator.generateNext();
      session.send(request);
      pendingRequestHolder.set(request);
    }

    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (!(message instanceof NotificationWebSocketConfirmation confirmation)) {
      return null;
    }

    synchronized (pendingRequestHolder) {
      NotificationWebSocketRequest request = pendingRequestHolder.get();
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
  }

  @Override
  protected final void reset() {
    synchronized (pendingRequestHolder) {
      pendingRequestHolder.set(null);
    }
  }
}
