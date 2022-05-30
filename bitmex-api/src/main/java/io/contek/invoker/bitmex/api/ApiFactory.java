package io.contek.invoker.bitmex.api;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bitmex.api.rest.market.MarketRestApi;
import io.contek.invoker.bitmex.api.rest.user.UserRestApi;
import io.contek.invoker.bitmex.api.websocket.market.MarketWebSocketApi;
import io.contek.invoker.bitmex.api.websocket.user.UserWebSocketApi;
import io.contek.invoker.commons.ApiContext;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.IActorFactory;
import io.contek.invoker.commons.actor.SimpleActorFactory;
import io.contek.invoker.commons.actor.ratelimit.*;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.security.ApiKey;
import io.contek.invoker.security.SimpleCredentialFactory;
import io.contek.ursa.cache.LimiterManager;
import io.vertx.core.Vertx;

import java.time.Duration;
import java.util.List;

import static com.google.common.io.BaseEncoding.base16;
import static io.contek.invoker.bitmex.api.ApiFactory.RateLimits.*;
import static io.contek.invoker.commons.actor.ratelimit.LimitType.API_KEY;
import static io.contek.invoker.commons.actor.ratelimit.LimitType.IP;
import static io.contek.invoker.security.SecretKeyAlgorithm.HMAC_SHA256;

public final class ApiFactory {

  public static final ApiContext MAIN_NET_CONTEXT =
      ApiContext.newBuilder()
          .setRestContext(RestContext.of("https://www.bitmex.com"))
          .setWebSocketContext(WebSocketContext.of("wss://www.bitmex.com", Duration.ofSeconds(5)))
          .build();

  public static final ApiContext TEST_NET_CONTEXT =
      ApiContext.newBuilder()
          .setRestContext(RestContext.of("https://testnet.bitmex.com"))
          .setWebSocketContext(WebSocketContext.of("wss://testnet.bitmex.com"))
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

  public static ApiFactory getTestNet() {
    return fromContext(TEST_NET_CONTEXT);
  }

  public static ApiFactory fromContext(ApiContext context) {
    return new ApiFactory(context, createActorFactory(context.getInterceptors()));
  }

  private static SimpleActorFactory createActorFactory(
      List<IRateLimitQuotaInterceptor> interceptors) {
    return SimpleActorFactory.newBuilder()
        .setCredentialFactory(createCredentialFactory())
        .setRateLimitThrottleFactory(
            SimpleRateLimitThrottleFactory.create(createLimiterManager(), interceptors))
        .build();
  }

  private static SimpleCredentialFactory createCredentialFactory() {
    return SimpleCredentialFactory.newBuilder()
        .setAlgorithm(HMAC_SHA256)
        .setEncoding(base16().lowerCase())
        .build();
  }

  private static LimiterManager createLimiterManager() {
    return LimiterManagers.forRules(
        API_KEY_REST_REQUEST_RULE, IP_REST_REQUEST_RULE, IP_WEB_SOCKET_CONNECTION_RULE);
  }

  public SelectingRestApi rest() {
    return new SelectingRestApi();
  }

  public SelectingWebSocketApi ws() {
    return new SelectingWebSocketApi();
  }

  public static final class RateLimits {

    public static final RateLimitRule API_KEY_REST_REQUEST_RULE =
        RateLimitRule.newBuilder()
            .setName("api_key_rest_request_rule")
            .setType(API_KEY)
            .setMaxPermits(30)
            .setResetPeriod(Duration.ofMinutes(1))
            .build();

    public static final RateLimitRule IP_REST_REQUEST_RULE =
        RateLimitRule.newBuilder()
            .setName("ip_rest_request_rule")
            .setType(IP)
            .setMaxPermits(30)
            .setResetPeriod(Duration.ofMinutes(1))
            .build();

    public static final RateLimitRule IP_WEB_SOCKET_CONNECTION_RULE =
        RateLimitRule.newBuilder()
            .setName("ip_web_socket_connection_rule")
            .setType(IP)
            .setMaxPermits(60)
            .setResetPeriod(Duration.ofHours(1))
            .build();

    public static final ImmutableList<TypedPermitRequest> ONE_ANONYMOUS_REST_REQUEST =
        ImmutableList.of(IP_REST_REQUEST_RULE.forPermits(1));

    public static final ImmutableList<TypedPermitRequest> ONE_AUTHENTICATED_REST_REQUEST =
        ImmutableList.of(API_KEY_REST_REQUEST_RULE.forPermits(1));

    public static final ImmutableList<TypedPermitRequest> ONE_WEB_SOCKET_CONNECTION =
        ImmutableList.of(IP_WEB_SOCKET_CONNECTION_RULE.forPermits(1));

    private RateLimits() {}
  }

  public final class SelectingRestApi {

    private SelectingRestApi() {}

    public MarketRestApi market(Vertx vertx, ApiKey apiKey) {
      RestContext restContext = context.getRestContext();
      IActor actor = actorFactory.create(apiKey, vertx, restContext);
      return new MarketRestApi(actor, restContext);
    }

    public UserRestApi user(Vertx vertx, ApiKey apiKey) {
      RestContext restContext = context.getRestContext();
      IActor actor = actorFactory.create(apiKey, vertx, restContext);
      return new UserRestApi(actor, restContext);
    }
  }

  public final class SelectingWebSocketApi {

    private SelectingWebSocketApi() {}

    public MarketWebSocketApi market(Vertx vertx) {
      WebSocketContext wsContext = context.getWebSocketContext();
      IActor actor = actorFactory.create(null, vertx, wsContext);
      return new MarketWebSocketApi(actor, wsContext);
    }

    public UserWebSocketApi user(Vertx vertx, ApiKey apiKey) {
      WebSocketContext wsContext = context.getWebSocketContext();
      IActor actor = actorFactory.create(apiKey, vertx, wsContext);
      return new UserWebSocketApi(actor, wsContext);
    }
  }
}
