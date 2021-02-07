package io.contek.invoker.commons.websocket;

import io.contek.invoker.commons.actor.http.BaseHttpContext;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import java.time.Duration;

@Immutable
public final class WebSocketContext extends BaseHttpContext {

  private final Duration pingInterval;

  private WebSocketContext(String baseUrl, @Nullable Duration pingInterval) {
    super(baseUrl);
    this.pingInterval = pingInterval;
  }

  public static WebSocketContext forBaseUrl(String baseUrl) {
    return WebSocketContext.newBuilder().setBaseUrl(baseUrl).build();
  }

  public static WebSocketContext forBaseUrlAndPingInterval(String baseUrl, Duration pingInterval) {
    return WebSocketContext.newBuilder().setBaseUrl(baseUrl).setPingInterval(pingInterval).build();
  }
  public static Builder newBuilder() {
    return new Builder();
  }

  @Nullable
  @Override
  public Duration getPingInterval() {
    return pingInterval;
  }

  @NotThreadSafe
  public static final class Builder {

    private String baseUrl;
    private Duration pingInterval;

    public Builder setBaseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
      return this;
    }

    public Builder setPingInterval(@Nullable Duration pingInterval) {
      this.pingInterval = pingInterval;
      return this;
    }

    public WebSocketContext build() {
      if (baseUrl == null) {
        throw new IllegalArgumentException("No base URL specified");
      }
      return new WebSocketContext(baseUrl, pingInterval);
    }

    private Builder() {}
  }
}
