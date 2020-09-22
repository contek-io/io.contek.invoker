package io.contek.invoker.commons.api;

import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.websocket.WebSocketContext;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;

@Immutable
public final class ApiContext {

  private final RestContext restContext;
  private final WebSocketContext webSocketContext;

  private ApiContext(
      @Nullable RestContext restContext, @Nullable WebSocketContext webSocketContext) {
    this.restContext = restContext;
    this.webSocketContext = webSocketContext;
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

  @NotThreadSafe
  public static final class Builder {

    private RestContext restContext;
    private WebSocketContext webSocketContext;

    public Builder setRestContext(@Nullable RestContext restContext) {
      this.restContext = restContext;
      return this;
    }

    public Builder setWebSocketContext(@Nullable WebSocketContext webSocketContext) {
      this.webSocketContext = webSocketContext;
      return this;
    }

    public ApiContext build() {
      return new ApiContext(restContext, webSocketContext);
    }

    private Builder() {}
  }
}
