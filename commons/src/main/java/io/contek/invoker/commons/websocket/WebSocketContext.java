package io.contek.invoker.commons.websocket;

import io.contek.invoker.commons.actor.http.BaseHttpContext;

import java.time.Duration;

public final class WebSocketContext extends BaseHttpContext {

  private final Duration pingInterval;

  private WebSocketContext(String baseUrl, Duration pingInterval) {
    super(baseUrl);
    this.pingInterval = pingInterval;
  }

  public static WebSocketContext forBaseUrl(String baseUrl) {
    return forBaseUrl(baseUrl, null);
  }

  public static WebSocketContext forBaseUrl(String baseUrl, Duration pingInterval) {
    return WebSocketContext.newBuilder().setBaseUrl(baseUrl).setPingInterval(pingInterval).build();
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  @Override
  public Duration getPingInterval() {
    return pingInterval;
  }

  public static final class Builder {

    private String baseUrl;
    private Duration pingInterval;

    private Builder() {}

    public Builder setBaseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
      return this;
    }

    public Builder setPingInterval(Duration pingInterval) {
      this.pingInterval = pingInterval;
      return this;
    }

    public WebSocketContext build() {
      if (baseUrl == null) {
        throw new IllegalArgumentException("No base URL specified");
      }
      return new WebSocketContext(baseUrl, pingInterval);
    }
  }
}
