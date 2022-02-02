package io.contek.invoker.okx.api.websocket;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketLiveKeeper;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.commons.websocket.WebSocketSessionInactiveException;
import io.contek.invoker.okx.api.websocket.common.WebSocketPing;
import io.contek.invoker.okx.api.websocket.common.WebSocketPong;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public final class WebSocketLiveKeeper implements IWebSocketLiveKeeper {

  private static final Duration PING_INTERVAL = Duration.ofSeconds(20);

  private final Clock clock;

  private final AtomicReference<PendingPing> state = new AtomicReference<>(null);

  WebSocketLiveKeeper(Clock clock) {
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

          session.send(new WebSocketPing());
          return new PendingPing(now);
        });
  }

  @Override
  public void onMessage(AnyWebSocketMessage message, WebSocketSession session) {
    if (message instanceof WebSocketPong) {
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
