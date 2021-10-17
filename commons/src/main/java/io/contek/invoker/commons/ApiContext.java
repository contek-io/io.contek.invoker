package io.contek.invoker.commons;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.ratelimit.IRateLimitQuotaInterceptor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitCushion;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Immutable
public final class ApiContext {

  private final RestContext restContext;
  private final WebSocketContext webSocketContext;
  private final RateLimitCushion cushion;
  private final ImmutableList<IRateLimitQuotaInterceptor> interceptors;

  private ApiContext(
      @Nullable RestContext restContext,
      @Nullable WebSocketContext webSocketContext,
      RateLimitCushion cushion,
      ImmutableList<IRateLimitQuotaInterceptor> interceptors) {
    this.restContext = restContext;
    this.webSocketContext = webSocketContext;
    this.cushion = cushion;
    this.interceptors = interceptors;
  }

  public static Builder newBuilder() {
    return new Builder();
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

  public RateLimitCushion getCushion() {
    return cushion;
  }

  public ImmutableList<IRateLimitQuotaInterceptor> getInterceptors() {
    return interceptors;
  }

  @NotThreadSafe
  public static final class Builder {

    private RestContext restContext;
    private WebSocketContext webSocketContext;
    private RateLimitCushion cushion =
        RateLimitCushion.newBuilder()
            .setAdditionalResetPercentage(0.05)
            .setAdditionalResetDuration(Duration.ofMillis(100))
            .build();
    private List<IRateLimitQuotaInterceptor> interceptors = new ArrayList<>();

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

    public Builder setCushion(RateLimitCushion cushion) {
      this.cushion = cushion;
      return this;
    }

    public Builder setInterceptors(List<IRateLimitQuotaInterceptor> interceptors) {
      this.interceptors = interceptors;
      return this;
    }

    public Builder addInterceptor(IRateLimitQuotaInterceptor interceptor) {
      interceptors.add(interceptor);
      return this;
    }

    public ApiContext build() {
      return new ApiContext(
          restContext, webSocketContext, cushion, ImmutableList.copyOf(interceptors));
    }

    private Builder() {}
  }
}
