package io.contek.invoker.binancedelivery.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancedelivery.api.websocket.common.WebSocketCommand;
import io.contek.invoker.binancedelivery.api.websocket.common.WebSocketCommandConfirmation;
import io.contek.invoker.commons.api.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.api.websocket.BaseWebSocketChannel;
import io.contek.invoker.commons.api.websocket.SubscriptionState;
import io.contek.invoker.commons.api.websocket.WebSocketSession;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

import static io.contek.invoker.binancedelivery.api.websocket.common.constants.WebSocketMethods.SUBSCRIBE;
import static io.contek.invoker.binancedelivery.api.websocket.common.constants.WebSocketMethods.UNSUBSCRIBE;
import static io.contek.invoker.commons.api.websocket.SubscriptionState.*;

@ThreadSafe
public abstract class WebSocketChannel<Message> extends BaseWebSocketChannel<Message> {

  private final WebSocketRequestIdGenerator requestIdGenerator;

  private final AtomicReference<WebSocketCommand> pendingCommandHolder = new AtomicReference<>();

  protected WebSocketChannel(WebSocketRequestIdGenerator requestIdGenerator) {
    this.requestIdGenerator = requestIdGenerator;
  }

  protected abstract String getTopic();

  @Override
  protected final String getDisplayName() {
    return getTopic();
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    synchronized (pendingCommandHolder) {
      if (pendingCommandHolder.get() != null) {
        throw new IllegalStateException();
      }
      WebSocketCommand command = new WebSocketCommand();
      command.method = SUBSCRIBE;
      command.params = ImmutableList.of(getTopic());
      command.id = requestIdGenerator.getNextRequestId();
      session.send(command);
      pendingCommandHolder.set(command);
    }
    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    synchronized (pendingCommandHolder) {
      if (pendingCommandHolder.get() != null) {
        throw new IllegalStateException();
      }
      WebSocketCommand command = new WebSocketCommand();
      command.method = UNSUBSCRIBE;
      command.params = ImmutableList.of(getTopic());
      command.id = requestIdGenerator.getNextRequestId();
      session.send(command);
      pendingCommandHolder.set(command);
    }
    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    synchronized (pendingCommandHolder) {
      WebSocketCommand command = pendingCommandHolder.get();
      if (command == null) {
        return null;
      }
      if (!(message instanceof WebSocketCommandConfirmation)) {
        return null;
      }
      WebSocketCommandConfirmation confirmation = (WebSocketCommandConfirmation) message;
      if (confirmation.id == null || !confirmation.id.equals(command.id)) {
        return null;
      }
      reset();
      switch (command.method) {
        case SUBSCRIBE:
          return SUBSCRIBED;
        case UNSUBSCRIBE:
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
