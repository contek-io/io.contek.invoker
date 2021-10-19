package io.contek.invoker.coinbasepro.api;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.coinbasepro.api.rest.market.MarketRestApi;
import io.contek.invoker.coinbasepro.api.rest.user.UserRestApi;
import io.contek.invoker.coinbasepro.api.websocket.market.MarketWebSocketApi;
import io.contek.invoker.coinbasepro.api.websocket.user.UserWebSocketApi;
import io.contek.invoker.commons.ApiContext;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.IActorFactory;
import io.contek.invoker.commons.actor.SimpleActorFactory;
import io.contek.invoker.commons.actor.http.SimpleHttpClientFactory;
import io.contek.invoker.commons.actor.ratelimit.*;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.security.ApiKey;
import io.contek.invoker.security.SimpleCredentialFactory;
import io.contek.ursa.cache.LimiterManager;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.time.Duration;
import java.util.List;

import static com.google.common.io.BaseEncoding.base16;
import static io.contek.invoker.coinbasepro.api.ApiFactory.RateLimits.*;
import static io.contek.invoker.commons.actor.ratelimit.LimitType.IP;
import static io.contek.invoker.security.SecretKeyAlgorithm.HMAC_SHA256;

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

  public static ApiFactory getMainNet() {
    return fromContext(MAIN_NET_CONTEXT);
  }

  public static ApiFactory fromContext(ApiContext context) {
    return new ApiFactory(
        context, createActorFactory(context.getCushion(), context.getInterceptors()));
  }

  public SelectingRestApi rest() {
    return new SelectingRestApi();
  }

  public SelectingWebSocketApi ws() {
    return new SelectingWebSocketApi();
  }

  private static SimpleActorFactory createActorFactory(
      RateLimitCushion cushion, List<IRateLimitQuotaInterceptor> interceptors) {
    return SimpleActorFactory.newBuilder()
        .setCredentialFactory(createCredentialFactory())
        .setHttpClientFactory(SimpleHttpClientFactory.getInstance())
        .setRateLimitThrottleFactory(
            SimpleRateLimitThrottleFactory.create(createLimiterManager(cushion), interceptors))
        .build();
  }

  private static SimpleCredentialFactory createCredentialFactory() {
    return SimpleCredentialFactory.newBuilder()
        .setAlgorithm(HMAC_SHA256)
        .setEncoding(base16().upperCase())
        .build();
  }

  private static LimiterManager createLimiterManager(RateLimitCushion cushion) {
    return LimiterManagers.forRules(
        IP_REST_PUBLIC_REQUEST_RULE, IP_REST_PRIVATE_REQUEST_RULE, IP_WEB_SOCKET_CONNECTION_RULE);
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

    public static final ImmutableList<TypedPermitRequest> ONE_REST_PUBLIC_REQUEST =
        ImmutableList.of(IP_REST_PUBLIC_REQUEST_RULE.forPermits(1));

    public static final ImmutableList<TypedPermitRequest> ONE_REST_PRIVATE_REQUEST =
        ImmutableList.of(IP_REST_PRIVATE_REQUEST_RULE.forPermits(1));

    public static final ImmutableList<TypedPermitRequest> ONE_WEB_SOCKET_CONNECTION =
        ImmutableList.of(IP_WEB_SOCKET_CONNECTION_RULE.forPermits(1));

    private RateLimits() {}
  }
}
