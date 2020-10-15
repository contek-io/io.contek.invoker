package io.contek.invoker.coinbasepro.api;

import static com.google.common.io.BaseEncoding.base16;
import static io.contek.invoker.coinbasepro.api.ApiFactory.RateLimits.IP_REST_PRIVATE_REQUEST_RULE;
import static io.contek.invoker.coinbasepro.api.ApiFactory.RateLimits.IP_REST_PUBLIC_REQUEST_RULE;
import static io.contek.invoker.coinbasepro.api.ApiFactory.RateLimits.IP_WEB_SOCKET_CONNECTION_RULE;
import static io.contek.invoker.commons.api.actor.ratelimit.RateLimitType.IP;
import static io.contek.invoker.commons.api.actor.security.SecretKeyAlgorithm.HMAC_SHA256;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.coinbasepro.api.rest.market.MarketRestApi;
import io.contek.invoker.coinbasepro.api.rest.user.UserRestApi;
import io.contek.invoker.coinbasepro.api.websocket.market.MarketWebSocketApi;
import io.contek.invoker.coinbasepro.api.websocket.user.UserWebSocketApi;
import io.contek.invoker.commons.api.ApiContext;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.actor.IActorFactory;
import io.contek.invoker.commons.api.actor.SimpleActorFactory;
import io.contek.invoker.commons.api.actor.http.SimpleHttpClientFactory;
import io.contek.invoker.commons.api.actor.ratelimit.RateLimitCache;
import io.contek.invoker.commons.api.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.api.actor.ratelimit.RateLimitRule;
import io.contek.invoker.commons.api.actor.ratelimit.SimpleRateLimitThrottleFactory;
import io.contek.invoker.commons.api.actor.security.ApiKey;
import io.contek.invoker.commons.api.actor.security.SimpleCredentialFactory;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.websocket.WebSocketContext;
import java.time.Duration;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class ApiFactory {

  public static final ApiContext MAIN_NET_CONTEXT =
      ApiContext.newBuilder()
          .setRestContext(RestContext.forBaseUrl("https://api.pro.coinbase.com"))
          .setWebSocketContext(WebSocketContext.forBaseUrl("wss://ws-feed.pro.coinbase.com"))
          .build();

  private final ApiContext context;
  private final IActorFactory actorFactory;

  private ApiFactory(ApiContext context, IActorFactory actorFactory) {
    this.context = context;
    this.actorFactory = actorFactory;
  }

  public static ApiFactory getMainNetDefault() {
    return fromContext(MAIN_NET_CONTEXT);
  }

  public static ApiFactory fromContext(ApiContext context) {
    return new ApiFactory(context, createActorFactory());
  }

  public SelectingRestApi rest() {
    return new SelectingRestApi();
  }

  public SelectingWebSocketApi ws() {
    return new SelectingWebSocketApi();
  }

  private static SimpleActorFactory createActorFactory() {
    return SimpleActorFactory.newBuilder()
        .setCredentialFactory(createCredentialFactory())
        .setHttpClientFactory(SimpleHttpClientFactory.getInstance())
        .setRateLimitThrottleFactory(
            SimpleRateLimitThrottleFactory.fromCache(createRateLimitCache()))
        .build();
  }

  private static SimpleCredentialFactory createCredentialFactory() {
    return SimpleCredentialFactory.newBuilder()
        .setAlgorithm(HMAC_SHA256)
        .setEncoding(base16().upperCase())
        .build();
  }

  private static RateLimitCache createRateLimitCache() {
    return RateLimitCache.newBuilder()
        .addRule(IP_REST_PUBLIC_REQUEST_RULE)
        .addRule(IP_REST_PRIVATE_REQUEST_RULE)
        .addRule(IP_WEB_SOCKET_CONNECTION_RULE)
        .build();
  }

  @ThreadSafe
  public final class SelectingRestApi {

    private SelectingRestApi() {}

    public MarketRestApi market() {
      RestContext restContext = context.getRestContext();
      IActor actor = actorFactory.create(null, restContext);
      return new MarketRestApi(actor, restContext);
    }

    public UserRestApi user(ApiKey apiKey) {
      RestContext restContext = context.getRestContext();
      IActor actor = actorFactory.create(apiKey, restContext);
      return new UserRestApi(actor, restContext);
    }
  }

  @ThreadSafe
  public final class SelectingWebSocketApi {

    private SelectingWebSocketApi() {}

    public MarketWebSocketApi market() {
      WebSocketContext wsContext = context.getWebSocketContext();
      IActor actor = actorFactory.create(null, wsContext);
      return new MarketWebSocketApi(actor, wsContext);
    }

    public UserWebSocketApi user(ApiKey apiKey) {
      WebSocketContext wsContext = context.getWebSocketContext();
      IActor actor = actorFactory.create(apiKey, wsContext);
      return new UserWebSocketApi(actor, wsContext);
    }
  }

  @Immutable
  public static final class RateLimits {

    public static final RateLimitRule IP_REST_PUBLIC_REQUEST_RULE =
        RateLimitRule.newBuilder()
            .setName("ip_rest_public_request_rule")
            .setType(IP)
            .setMaxPermits(3)
            .setResetPeriod(Duration.ofSeconds(1))
            .build();

    public static final RateLimitRule IP_REST_PRIVATE_REQUEST_RULE =
        RateLimitRule.newBuilder()
            .setName("ip_rest_private_request_rule")
            .setType(IP)
            .setMaxPermits(5)
            .setResetPeriod(Duration.ofSeconds(1))
            .build();

    public static final RateLimitRule IP_WEB_SOCKET_CONNECTION_RULE =
        RateLimitRule.newBuilder()
            .setName("ip_web_socket_connection_rule")
            .setType(IP)
            .setMaxPermits(1)
            .setResetPeriod(Duration.ofSeconds(4))
            .build();

    public static final ImmutableList<RateLimitQuota> ONE_REST_PUBLIC_REQUEST =
        ImmutableList.of(IP_REST_PUBLIC_REQUEST_RULE.createRateLimitQuota(1));

    public static final ImmutableList<RateLimitQuota> ONE_REST_PRIVATE_REQUEST =
        ImmutableList.of(IP_REST_PRIVATE_REQUEST_RULE.createRateLimitQuota(1));

    public static final ImmutableList<RateLimitQuota> ONE_WEB_SOCKET_CONNECTION =
        ImmutableList.of(IP_WEB_SOCKET_CONNECTION_RULE.createRateLimitQuota(1));

    private RateLimits() {}
  }
}
