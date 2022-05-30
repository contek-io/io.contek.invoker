package io.contek.invoker.commons.websocket;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import static io.contek.invoker.commons.websocket.ConsumerState.*;
import static io.contek.invoker.commons.websocket.SubscriptionState.*;
import static org.slf4j.LoggerFactory.getLogger;

public abstract class BaseWebSocketChannel<
        Id extends BaseWebSocketChannelId<Message>, Message extends AnyWebSocketMessage>
    implements IWebSocketComponent {

  private static final Logger log = getLogger(BaseWebSocketChannel.class);

  private final Id id;
  private final List<ISubscribingConsumer<Message>> consumers = new ArrayList<>();
  private final ReentrantLock lock;
  private volatile SubscriptionState stateHolder = UNSUBSCRIBED;

  protected BaseWebSocketChannel(Id id) {
    this.id = id;
    this.lock = new ReentrantLock();
  }

  public final Id getId() {
    return id;
  }

  public final void addConsumer(ISubscribingConsumer<Message> consumer) {
    lock.lock();
    try {
      SubscriptionState state = stateHolder;
      consumer.onStateChange(state);
      consumers.add(consumer);
    } finally {
      lock.unlock();
    }
  }

  @Override
  public final void heartbeat(WebSocketSession session) {
    lock.lock();
    try {
      ConsumerState childConsumerState = getChildConsumerState();

      SubscriptionState currentState = stateHolder;
      SubscriptionState newState = null;
      if (currentState == SUBSCRIBED && childConsumerState == IDLE) {
        log.info("Unsubscribing channel {}.", id);
        newState = unsubscribe(session);
        if (newState == SUBSCRIBED || newState == SUBSCRIBING) {
          log.error("Channel {} has invalid state after unsubscribe: {}.", id, newState);
        }
      } else if (currentState == UNSUBSCRIBED && childConsumerState == ACTIVE) {
        log.info("Subscribing channel {}.", id);
        newState = subscribe(session);
        if (newState == UNSUBSCRIBED || newState == UNSUBSCRIBING) {
          log.error("Channel {} has invalid state after subscribe: {}.", id, newState);
        }
      }

      if (newState != null) {
        setState(newState);
      }
    } finally {
      lock.unlock();
    }
  }

  @Override
  public final ConsumerState getState() {
    lock.lock();
    try {
      if (getChildConsumerState() == ACTIVE) {
        return ACTIVE;
      }

      return stateHolder != UNSUBSCRIBED ? ACTIVE : IDLE;
    } finally {
      lock.unlock();
    }
  }

  private void setState(SubscriptionState state) {
    lock.lock();
    try {
      consumers.forEach(consumer -> consumer.onStateChange(state));
      stateHolder = state;
    } finally {
      lock.unlock();
    }
  }

  @Override
  public final void onMessage(AnyWebSocketMessage message, WebSocketSession session) {
    lock.lock();
    try {
      Message casted = tryCast(message);
      if (casted != null) {
        consumers.forEach(consumer -> consumer.onNext(casted));
      }

      SubscriptionState newState = getState(message);
      if (newState != null) {
        log.info("Channel {} is now {}.", id, newState);
        setState(newState);
      }
    } finally {
      lock.unlock();
    }
  }

  @Override
  public final void afterDisconnect() {
    reset();
    setState(UNSUBSCRIBED);
  }

  public abstract Class<Message> getMessageType();

  protected abstract SubscriptionState subscribe(WebSocketSession session);

  protected abstract SubscriptionState unsubscribe(WebSocketSession session);

  protected abstract SubscriptionState getState(AnyWebSocketMessage message);

  protected abstract void reset();

  private ConsumerState getChildConsumerState() {
    lock.lock();
    try {
      consumers.removeIf(consumer -> consumer.getState() == TERMINATED);
      return consumers.stream().anyMatch(consumer -> consumer.getState() == ACTIVE) ? ACTIVE : IDLE;
    } finally {
      lock.unlock();
    }
  }

  private Message tryCast(AnyWebSocketMessage message) {
    if (!getMessageType().isAssignableFrom(message.getClass())) {
      return null;
    }
    Message casted = getMessageType().cast(message);
    return id.accepts(casted) ? casted : null;
  }
}
