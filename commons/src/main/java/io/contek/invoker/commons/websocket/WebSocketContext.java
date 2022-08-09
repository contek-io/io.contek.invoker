package io.contek.invoker.commons.websocket;

import io.contek.invoker.commons.actor.http.BaseHttpContext;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import java.time.Duration;

@Immutable
public final class WebSocketContext extends BaseHttpContext {

  private final Duration pingInterval;
  private final boolean isDemo;

  private WebSocketContext(String baseUrl, @Nullable Duration pingInterval) {
    this(baseUrl, pingInterval, false);
  }

  private WebSocketContext(String baseUrl, @Nullable Duration pingInterval, boolean isDemo) {
    super(baseUrl);
    this.pingInterval = pingInterval;
    this.isDemo = isDemo;
  }


  public static WebSocketContext forBaseUrl(String baseUrl) {
    return forBaseUrl(baseUrl, null);
  }

  public static WebSocketContext forBaseUrl(String baseUrl, @Nullable Duration pingInterval) {
    return forBaseUrl(baseUrl, pingInterval, false);
  }

  public static WebSocketContext forBaseUrl(String baseUrl, @Nullable Duration pingInterval, boolean isDemo) {
    return WebSocketContext.newBuilder().setBaseUrl(baseUrl).setPingInterval(pingInterval).setIsDemo(isDemo).build();
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  @Nullable
  @Override
  public Duration getPingInterval() {
    return pingInterval;
  }

  public boolean isDemo() {
    return isDemo;
  }

  @NotThreadSafe
  public static final class Builder {

    private String baseUrl;
    private Duration pingInterval;
    private boolean isDemo;

    public Builder setBaseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
      return this;
    }

    public Builder setPingInterval(@Nullable Duration pingInterval) {
      this.pingInterval = pingInterval;
      return this;
    }

    public Builder setIsDemo(boolean isDemo) {
      this.isDemo = isDemo;
      return this;
    }

    public WebSocketContext build() {
      if (baseUrl == null) {
        throw new IllegalArgumentException("No base URL specified");
      }
      return new WebSocketContext(baseUrl, pingInterval, isDemo);
    }

    private Builder() {}
  }
}
