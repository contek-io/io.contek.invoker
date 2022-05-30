package io.contek.invoker.commons.websocket;

import io.contek.invoker.commons.actor.http.IHttpContext;
import io.vertx.core.http.HttpClientOptions;

import java.time.Duration;
import java.util.Objects;

public record WebSocketContext(String baseUrl, HttpClientOptions options,
                               Duration pingInterval) implements IHttpContext {

  public WebSocketContext {
    Objects.requireNonNull(baseUrl);
    Objects.requireNonNull(options);
  }

  public static WebSocketContext of(String baseUrl, HttpClientOptions options, Duration pingInterval) {
    return new WebSocketContext(baseUrl, options, pingInterval);
  }

  public static WebSocketContext of(String baseUrl) {
    return of(baseUrl, new HttpClientOptions(), null);
  }

  public static WebSocketContext of(String baseUrl, Duration pingInterval) {
    return of(baseUrl, new HttpClientOptions(), pingInterval);
  }

}
