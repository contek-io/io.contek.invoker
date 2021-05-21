package io.contek.invoker.commons;

import io.contek.invoker.commons.actor.ratelimit.IRateLimitQuotaInterceptor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;

@Immutable
public final class ApiContext {

  private final double rateLimitCushion;
  private final RestContext restContext;
  private final WebSocketContext webSocketContext;
  private final IRateLimitQuotaInterceptor interceptor;

  private ApiContext(
      double rateLimitCushion,
      @Nullable RestContext restContext,
      @Nullable WebSocketContext webSocketContext,
      @Nullable IRateLimitQuotaInterceptor interceptor) {
    this.rateLimitCushion = rateLimitCushion;
    this.restContext = restContext;
    this.webSocketContext = webSocketContext;
    this.interceptor = interceptor;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public double getRateLimitCushion() {
    return rateLimitCushion;
  }

  public RestContext getRestContext() {
    if (restContext == null) {
      throw new NoApiContextException();
    }
    return restContext;
  }

  public WebSocketContext getWebSocketContext() {
    if (webSocketContext == null) {
      throw new NoApiContextException();
    }
    return webSocketContext;
  }

  @Nullable
  public IRateLimitQuotaInterceptor getInterceptor() {
    return interceptor;
  }

  @NotThreadSafe
  public static final class Builder {

    private double rateLimitCushion = 0.0d;
    private RestContext restContext;
    private WebSocketContext webSocketContext;
    private IRateLimitQuotaInterceptor interceptor;

    public Builder setRateLimitCushion(double rateLimitCushion) {
      this.rateLimitCushion = rateLimitCushion;
      return this;
    }

    public Builder setRestContext(@Nullable RestContext.Builder builder) {
      return setRestContext(builder == null ? null : builder.build());
    }

    public Builder setRestContext(@Nullable RestContext restContext) {
      this.restContext = restContext;
      return this;
    }

    public Builder setWebSocketContext(@Nullable WebSocketContext.Builder builder) {
      return setWebSocketContext(builder == null ? null : builder.build());
    }

    public Builder setWebSocketContext(@Nullable WebSocketContext webSocketContext) {
      this.webSocketContext = webSocketContext;
      return this;
    }

    public Builder setInterceptor(@Nullable IRateLimitQuotaInterceptor interceptor) {
      this.interceptor = interceptor;
      return this;
    }

    public ApiContext build() {
      return new ApiContext(rateLimitCushion, restContext, webSocketContext, interceptor);
    }

    private Builder() {}
  }
}
