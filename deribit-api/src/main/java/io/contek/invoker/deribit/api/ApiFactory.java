package io.contek.invoker.deribit.api;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.ApiContext;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.IActorFactory;
import io.contek.invoker.commons.actor.SimpleActorFactory;
import io.contek.invoker.commons.actor.http.SimpleHttpClientFactory;
import io.contek.invoker.commons.actor.ratelimit.*;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.deribit.api.rest.market.MarketRestApi;
import io.contek.invoker.deribit.api.rest.user.UserRestApi;
import io.contek.invoker.deribit.api.websocket.market.MarketWebSocketApi;
import io.contek.invoker.deribit.api.websocket.user.UserWebSocketApi;
import io.contek.invoker.security.ApiKey;
import io.contek.invoker.security.SimpleCredentialFactory;
import io.contek.ursa.cache.LimiterManager;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.time.Duration;
import java.util.List;

import static com.google.common.io.BaseEncoding.base16;
import static io.contek.invoker.commons.actor.ratelimit.LimitType.API_KEY;
import static io.contek.invoker.commons.actor.ratelimit.LimitType.IP;
import static io.contek.invoker.deribit.api.ApiFactory.RateLimits.*;
import static io.contek.invoker.security.SecretKeyAlgorithm.HMAC_SHA256;

@ThreadSafe
public final class ApiFactory {

  public static final ApiContext MAIN_NET_CONTEXT =
      ApiContext.newBuilder()
          .setRestContext(RestContext.forBaseUrl("https://www.deribit.com"))
          .setWebSocketContext(WebSocketContext.forBaseUrl("wss://www.deribit.com"))
          .build();

  public static final ApiContext TEST_NET_CONTEXT =
      ApiContext.newBuilder()
          .setRestContext(RestContext.forBaseUrl("https://test.deribit.com"))
          .setWebSocketContext(WebSocketContext.forBaseUrl("wss://test.deribit.com"))
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

  public SelectingRestApi rest() {
    return new SelectingRestApi();
  }

  public SelectingWebSocketApi ws() {
    return new SelectingWebSocketApi();
  }

  private static SimpleActorFactory createActorFactory(
      List<IRateLimitQuotaInterceptor> interceptors) {
    return SimpleActorFactory.newBuilder()
        .setCredentialFactory(createCredentialFactory())
        .setHttpClientFactory(SimpleHttpClientFactory.getInstance())
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
        API_KEY_MATCHING_ENGINE_REQUEST_RULE,
        API_KEY_NON_MATCHING_ENGINE_REQUEST_RULE,
        IP_NON_MATCHING_ENGINE_REQUEST_RULE,
        IP_WEB_SOCKET_CONNECTION_RULE);
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

    public static final RateLimitRule API_KEY_MATCHING_ENGINE_REQUEST_RULE =
        RateLimitRule.newBuilder()
            .setName("api_key_matching_engine_request_rule")
            .setType(API_KEY) // per sub-account
            .setMaxPermits(5)
            .setResetPeriod(Duration.ofSeconds(1))
            .build();

    public static final RateLimitRule API_KEY_NON_MATCHING_ENGINE_REQUEST_RULE =
        RateLimitRule.newBuilder()
            .setName("api_key_non_matching_engine_request_rule")
            .setType(API_KEY)
            .setMaxPermits(20)
            .setResetPeriod(Duration.ofSeconds(1))
            .build();

    public static final RateLimitRule IP_NON_MATCHING_ENGINE_REQUEST_RULE =
        RateLimitRule.newBuilder()
            .setName("ip_non_matching_engine_request_rule")
            .setType(IP)
            .setMaxPermits(20)
            .setResetPeriod(Duration.ofSeconds(1))
            .build();

    public static final RateLimitRule IP_WEB_SOCKET_CONNECTION_RULE =
        RateLimitRule.newBuilder()
            .setName("ip_web_socket_connection_rule")
            .setType(IP)
            .setMaxPermits(1)
            .setResetPeriod(Duration.ofSeconds(1))
            .build();

    public static final ImmutableList<TypedPermitRequest> ONE_API_KEY_MATCHING_ENGINE_REQUEST =
        ImmutableList.of(API_KEY_MATCHING_ENGINE_REQUEST_RULE.forPermits(1));

    public static final ImmutableList<TypedPermitRequest> ONE_API_KEY_NON_MATCHING_ENGINE_REQUEST =
        ImmutableList.of(API_KEY_NON_MATCHING_ENGINE_REQUEST_RULE.forPermits(1));

    public static final ImmutableList<TypedPermitRequest> ONE_IP_NON_MATCHING_ENGINE_REQUEST =
        ImmutableList.of(IP_NON_MATCHING_ENGINE_REQUEST_RULE.forPermits(1));

    public static final ImmutableList<TypedPermitRequest> ONE_WEB_SOCKET_CONNECTION =
        ImmutableList.of(IP_WEB_SOCKET_CONNECTION_RULE.forPermits(1));

    private RateLimits() {}
  }
}
