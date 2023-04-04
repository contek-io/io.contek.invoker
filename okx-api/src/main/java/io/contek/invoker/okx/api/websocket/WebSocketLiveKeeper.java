package io.contek.invoker.okx.api.websocket;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketLiveKeeper;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.commons.websocket.WebSocketSessionInactiveException;
import io.contek.invoker.okx.api.websocket.common.WebSocketPing;
import io.contek.invoker.okx.api.websocket.common.WebSocketPong;
import org.slf4j.Logger;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.slf4j.LoggerFactory.getLogger;

@ThreadSafe
public final class WebSocketLiveKeeper implements IWebSocketLiveKeeper {

  private static final Logger log = getLogger(WebSocketLiveKeeper.class);

  private static final Duration PING_INTERVAL = Duration.ofSeconds(20);

  private final String name;
  private final Clock clock;

  private final AtomicReference<PendingPing> state = new AtomicReference<>(null);

  WebSocketLiveKeeper(String name, Clock clock) {
    this.name = name;
    this.clock = clock;
  }

  @Override
  public void onHeartbeat(WebSocketSession session) throws WebSocketSessionInactiveException {
    Instant now = clock.instant();
    state.updateAndGet(
        s -> {
          if (s != null) {
            boolean overdue = s.timestamp.plus(PING_INTERVAL).isBefore(now);
            if (!overdue) {
              return s;
            }

            if (!s.isCompleted()) {
              throw new WebSocketSessionInactiveException();
            }
          }

          log.info("Sending PING message on WebSocket connection \"{}\".", name);
          session.send(new WebSocketPing());
          return new PendingPing(now);
        });
  }

  @Override
  public void onMessage(AnyWebSocketMessage message, WebSocketSession session) {
    if (message instanceof WebSocketPong) {
      log.info("Received PONG message on WebSocket connection \"{}\".", name);
      state.updateAndGet(
          s -> {
            if (s != null) {
              s.complete();
            }

            return s;
          });
    }
  }

  @Override
  public void afterDisconnect() {
    state.set(null);
  }

  @ThreadSafe
  private static final class PendingPing {

    private final Instant timestamp;
    private final AtomicBoolean completed = new AtomicBoolean(false);

    private PendingPing(Instant timestamp) {
      this.timestamp = timestamp;
    }

    private void complete() {
      completed.set(true);
    }

    private boolean isCompleted() {
      return completed.get();
    }
  }
}
