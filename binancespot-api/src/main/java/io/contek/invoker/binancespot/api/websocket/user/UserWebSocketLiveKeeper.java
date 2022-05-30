package io.contek.invoker.binancespot.api.websocket.user;

import io.contek.invoker.binancespot.api.rest.user.UserRestApi;
import io.contek.invoker.commons.actor.http.HttpConnectionException;
import io.contek.invoker.commons.actor.http.HttpInterruptedException;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;
import io.contek.invoker.commons.websocket.IWebSocketLiveKeeper;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.commons.websocket.WebSocketSessionInactiveException;
import io.vertx.core.Future;
import org.slf4j.Logger;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

import static org.slf4j.LoggerFactory.getLogger;

final class UserWebSocketLiveKeeper implements IWebSocketLiveKeeper {

  private static final Logger log = getLogger(UserWebSocketLiveKeeper.class);

  private static final Duration REFRESH_PERIOD = Duration.ofMinutes(10);

  private final UserRestApi userRestApi;
  private final Clock clock;

  private State stateHolder = null;

  UserWebSocketLiveKeeper(UserRestApi userRestApi, Clock clock) {
    this.userRestApi = userRestApi;
    this.clock = clock;
  }

  @Override
  public void onHeartbeat(WebSocketSession session) throws WebSocketSessionInactiveException {
    State state = stateHolder;
    if (state == null) {
      return;
    }

    Instant timestamp = clock.instant();
    Instant expire = state.lastRefreshTimestamp().plus(REFRESH_PERIOD);
    if (timestamp.isBefore(expire)) {
      return;
    }

    try {
      userRestApi.putListenKey().setListenKey(state.listenKey()).submit();
      stateHolder = new State(state.listenKey(), timestamp);
    } catch (HttpConnectionException | HttpInterruptedException e) {
      log.warn("Failed to refresh listen key.", e);
    }
  }

  @Override
  public void onMessage(AnyWebSocketMessage message, WebSocketSession session) {}

  @Override
  public void afterDisconnect() {
    stateHolder = null;
  }

  Future<String> init() {
    Instant timestamp = clock.instant();
    return userRestApi
        .postListenKey()
        .submit()
        .onSuccess(
            response -> {
              stateHolder = new State(response.listenKey, timestamp);
            })
        .map(response -> response.listenKey);
  }

  private record State(String listenKey, Instant lastRefreshTimestamp) {}
}
