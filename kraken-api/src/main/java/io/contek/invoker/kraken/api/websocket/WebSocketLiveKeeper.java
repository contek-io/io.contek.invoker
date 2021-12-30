package io.contek.invoker.kraken.api.websocket;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketLiveKeeper;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.commons.websocket.WebSocketSessionIdleException;
import io.contek.invoker.kraken.api.websocket.common.WebSocketPingRequest;
import io.contek.invoker.kraken.api.websocket.common.WebSocketPongResponse;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static io.contek.invoker.kraken.api.websocket.common.constants.WebSocketEventKeys._ping;

@ThreadSafe
public final class WebSocketLiveKeeper implements IWebSocketLiveKeeper {

  private static final Duration PING_INTERVAL = Duration.ofSeconds(2);

  private final WebSocketRequestIdGenerator requestIdGenerator;
  private final Clock clock;

  private final AtomicReference<PendingPing> state = new AtomicReference<>(null);

  WebSocketLiveKeeper(WebSocketRequestIdGenerator requestIdGenerator, Clock clock) {
    this.requestIdGenerator = requestIdGenerator;
    this.clock = clock;
  }

  @Override
  public void onHeartbeat(WebSocketSession session) {
    Instant now = clock.instant();
    state.updateAndGet(
        s -> {
          if (s != null) {
            boolean overdue = s.timestamp.plus(PING_INTERVAL).isBefore(now);
            if (!overdue) {
              return s;
            }

            if (!s.isCompleted()) {
              throw new WebSocketSessionIdleException();
            }
          }

          int reqid = requestIdGenerator.generateNext();
          WebSocketPingRequest request = new WebSocketPingRequest();
          request.event = _ping;
          request.reqid = reqid;
          session.send(request);
          return new PendingPing(reqid, now);
        });
  }

  @Override
  public void onMessage(AnyWebSocketMessage message, WebSocketSession session) {
    if (message instanceof WebSocketPongResponse) {
      WebSocketPongResponse response = (WebSocketPongResponse) message;
      state.updateAndGet(
          s -> {
            if (s != null) {
              if (s.reqid != response.reqid) {
                throw new IllegalStateException();
              }

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

  @Immutable
  private static final class PendingPing {

    private final int reqid;
    private final Instant timestamp;
    private final AtomicBoolean completed = new AtomicBoolean(false);

    private PendingPing(int reqid, Instant timestamp) {
      this.reqid = reqid;
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
