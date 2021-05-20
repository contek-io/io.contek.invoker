package io.contek.invoker.hbdmlinear.api.websocket.user;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

import static io.contek.invoker.commons.websocket.SubscriptionState.*;
import static io.contek.invoker.hbdmlinear.api.websocket.user.constants.OpKeys._sub;
import static io.contek.invoker.hbdmlinear.api.websocket.user.constants.OpKeys._unsub;

@ThreadSafe
abstract class UserWebSocketChannel<
        Id extends UserWebSocketChannelId<Message>, Message extends UserWebSocketChannelMessage>
    extends BaseWebSocketChannel<Id, Message> {

  private final Class<Message> type;
  private final UserWebSocketRequestIdGenerator requestIdGenerator;

  private final AtomicReference<UserWebSocketRequest> pendingCommandHolder =
      new AtomicReference<>(null);

  protected UserWebSocketChannel(
      Id id, Class<Message> type, UserWebSocketRequestIdGenerator requestIdGenerator) {
    super(id);
    this.type = type;
    this.requestIdGenerator = requestIdGenerator;
  }

  @Override
  public final Class<Message> getMessageType() {
    return type;
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    synchronized (pendingCommandHolder) {
      if (pendingCommandHolder.get() != null) {
        throw new IllegalStateException();
      }
    }

    Id id = getId();
    UserWebSocketRequest request = new UserWebSocketRequest();
    request.op = _sub;
    request.topic = id.getTopic();
    request.cid = generateNexRequestId();
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
    UserWebSocketRequest request = new UserWebSocketRequest();
    request.op = _unsub;
    request.topic = id.getTopic();
    request.cid = generateNexRequestId();
    session.send(request);
    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (!(message instanceof UserWebSocketResponse)) {
      return null;
    }
    UserWebSocketResponse<?> confirmation = (UserWebSocketResponse<?>) message;

    synchronized (pendingCommandHolder) {
      UserWebSocketRequest command = pendingCommandHolder.get();
      if (command == null) {
        return null;
      }
      if (confirmation.cid == null || !confirmation.cid.equals(command.cid)) {
        return null;
      }
      reset();
      switch (command.op) {
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
  protected final void reset() {}

  String generateNexRequestId() {
    return Integer.toString(requestIdGenerator.generateNext());
  }
}
