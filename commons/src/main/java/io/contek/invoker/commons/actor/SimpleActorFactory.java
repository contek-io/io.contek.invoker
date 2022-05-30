package io.contek.invoker.commons.actor;

import io.contek.invoker.commons.actor.http.IHttpClient;
import io.contek.invoker.commons.actor.http.IHttpContext;
import io.contek.invoker.commons.actor.ratelimit.IRateLimitThrottle;
import io.contek.invoker.commons.actor.ratelimit.IRateLimitThrottleFactory;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.security.ApiKey;
import io.contek.invoker.security.ICredential;
import io.contek.invoker.security.ICredentialFactory;
import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;

import java.net.InetAddress;

public record SimpleActorFactory(ICredentialFactory credentialFactory,
                                 IRateLimitThrottleFactory rateLimitThrottleFactory) implements IActorFactory {

  public SimpleActorFactory {
    if (credentialFactory == null) {
      throw new IllegalArgumentException("No credential factory specified");
    }
    if (rateLimitThrottleFactory == null) {
      throw new IllegalArgumentException("No rate limit throttle factory specified");
    }
  }

  private SimpleActorFactory(Builder builder) {
    this(builder.credentialFactory, builder.rateLimitThrottleFactory);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public IActor create(ApiKey apiKey, Vertx vertx, IHttpContext context) {
    ICredential credential =
            apiKey == null ? ICredential.anonymous() : credentialFactory.create(apiKey);

    final IHttpClient httpClient;
    if (context instanceof RestContext restContext) {
      httpClient = new IHttpClient.RestClient(WebClient.create(vertx, restContext.options()));
    }
    else if (context instanceof WebSocketContext webSocketContext){
      httpClient = new IHttpClient.WebSocketClient(vertx.createHttpClient(webSocketContext.options()));
    }
    else {
      throw new RuntimeException();
    }

    IRateLimitThrottle rateLimitThrottle =
      rateLimitThrottleFactory.create(
        InetAddress.getLoopbackAddress(), apiKey == null ? null : apiKey.getId());
    return new SimpleActor(credential, httpClient, rateLimitThrottle, vertx);


  }

  public static final class Builder {
    private ICredentialFactory credentialFactory;
    private IRateLimitThrottleFactory rateLimitThrottleFactory;

    private Builder() {
    }

    public Builder setCredentialFactory(ICredentialFactory credentialFactory) {
      this.credentialFactory = credentialFactory;
      return this;
    }

    public Builder setRateLimitThrottleFactory(IRateLimitThrottleFactory rateLimitThrottleFactory) {
      this.rateLimitThrottleFactory = rateLimitThrottleFactory;
      return this;
    }

    public SimpleActorFactory build() {
      return new SimpleActorFactory(this);
    }
  }
}
