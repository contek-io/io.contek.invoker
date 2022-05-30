package io.contek.invoker.commons;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.ratelimit.IRateLimitQuotaInterceptor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.websocket.WebSocketContext;

import java.util.ArrayList;
import java.util.List;

public final class ApiContext {

  private final RestContext restContext;
  private final WebSocketContext webSocketContext;
  private final ImmutableList<IRateLimitQuotaInterceptor> interceptors;

  private ApiContext(
      RestContext restContext,
      WebSocketContext webSocketContext,
      ImmutableList<IRateLimitQuotaInterceptor> interceptors) {
    this.restContext = restContext;
    this.webSocketContext = webSocketContext;
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

  public ImmutableList<IRateLimitQuotaInterceptor> getInterceptors() {
    return interceptors;
  }

  public static final class Builder {

    private RestContext restContext;
    private WebSocketContext webSocketContext;
    private List<IRateLimitQuotaInterceptor> interceptors = new ArrayList<>();

    private Builder() {}

    public Builder setRestContext(RestContext restContext) {
      this.restContext = restContext;
      return this;
    }

    public Builder setWebSocketContext(WebSocketContext webSocketContext) {
      this.webSocketContext = webSocketContext;
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
      return new ApiContext(restContext, webSocketContext, ImmutableList.copyOf(interceptors));
    }
  }
}
