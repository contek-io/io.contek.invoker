package io.contek.invoker.bybit.api;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.rest.market.MarketRestApi;
import io.contek.invoker.bybit.api.rest.user.UserRestApi;
import io.contek.invoker.bybit.api.websocket.market.MarketWebSocketApi;
import io.contek.invoker.bybit.api.websocket.user.UserWebSocketApi;
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
import static io.contek.invoker.bybit.api.ApiFactory.RateLimits.*;
import static io.contek.invoker.commons.actor.ratelimit.LimitType.API_KEY;
import static io.contek.invoker.commons.actor.ratelimit.LimitType.IP;
import static io.contek.invoker.security.SecretKeyAlgorithm.HMAC_SHA256;

@ThreadSafe
public final class ApiFactory {

  public static final ApiContext MAIN_NET_CONTEXT =
      ApiContext.newBuilder()
          .setRestContext(RestContext.forBaseUrl("https://api.bybit.com"))
          .setWebSocketContext(WebSocketContext.forBaseUrl("wss://stream.bybit.com"))
          .build();

  public static final ApiContext TEST_NET_CONTEXT =
      ApiContext.newBuilder()
          .setRestContext(RestContext.forBaseUrl("https://api-testnet.bybit.com"))
          .setWebSocketContext(WebSocketContext.forBaseUrl("wss://stream-testnet.bybit.com"))
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
        .setEncoding(base16().upperCase())
        .build();
  }

  private static LimiterManager createLimiterManager() {
    return LimiterManagers.forRules(
        IP_REST_REQUEST_RULE,
        API_KEY_REST_ORDER_CREATE_RULE,
        API_KEY_REST_ORDER_CANCEL_RULE,
        API_KEY_REST_ORDER_CANCEL_ALL_RULE,
        API_KEY_REST_ORDER_REALTIME_RULE,
        API_KEY_REST_ORDER_HISTORY_RULE,
        API_KEY_REST_POSITION_LIST_RULE,
        API_KEY_REST_EXECUTION_LIST_RULE,
        API_KEY_REST_ACCOUNT_WALLET_BALANCE_RULE);
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

    public static final RateLimitRule IP_REST_REQUEST_RULE =
        RateLimitRule.newBuilder()
            .setName("ip_rest_request_rule")
            .setType(IP)
            .setMaxPermits(120)
            .setResetPeriod(Duration.ofSeconds(5))
            .build();

    public static final RateLimitRule API_KEY_REST_ORDER_CREATE_RULE =
        RateLimitRule.newBuilder()
            .setName("api_key_rest_order_create_rule")
            .setType(API_KEY)
            .setMaxPermits(10)
            .setResetPeriod(Duration.ofSeconds(1))
            .build();

    public static final RateLimitRule API_KEY_REST_ORDER_CANCEL_RULE =
        RateLimitRule.newBuilder()
            .setName("api_key_rest_order_cancel_rule")
            .setType(API_KEY)
            .setMaxPermits(10)
            .setResetPeriod(Duration.ofSeconds(1))
            .build();

    public static final RateLimitRule API_KEY_REST_ORDER_CANCEL_ALL_RULE =
        RateLimitRule.newBuilder()
            .setName("api_key_rest_order_cancel_all_rule")
            .setType(API_KEY)
            .setMaxPermits(10)
            .setResetPeriod(Duration.ofSeconds(1))
            .build();

    public static final RateLimitRule API_KEY_REST_ORDER_REALTIME_RULE =
        RateLimitRule.newBuilder()
            .setName("api_key_rest_order_realtime_rule")
            .setType(API_KEY)
            .setMaxPermits(10)
            .setResetPeriod(Duration.ofSeconds(1))
            .build();

    public static final RateLimitRule API_KEY_REST_ORDER_HISTORY_RULE =
        RateLimitRule.newBuilder()
            .setName("api_key_rest_order_history_rule")
            .setType(API_KEY)
            .setMaxPermits(10)
            .setResetPeriod(Duration.ofSeconds(1))
            .build();

    public static final RateLimitRule API_KEY_REST_POSITION_LIST_RULE =
        RateLimitRule.newBuilder()
            .setName("api_key_rest_position_list_rule")
            .setType(API_KEY)
            .setMaxPermits(10)
            .setResetPeriod(Duration.ofSeconds(1))
            .build();

    public static final RateLimitRule API_KEY_REST_EXECUTION_LIST_RULE =
        RateLimitRule.newBuilder()
            .setName("api_key_rest_execution_list_rule")
            .setType(API_KEY)
            .setMaxPermits(10)
            .setResetPeriod(Duration.ofSeconds(1))
            .build();

    public static final RateLimitRule API_KEY_REST_ACCOUNT_WALLET_BALANCE_RULE =
        RateLimitRule.newBuilder()
            .setName("api_key_rest_account_wallet_balance_rule")
            .setType(API_KEY)
            .setMaxPermits(10)
            .setResetPeriod(Duration.ofSeconds(1))
            .build();

    public static final ImmutableList<TypedPermitRequest> ONE_REST_PUBLIC_GET_REQUEST =
        ImmutableList.of(IP_REST_REQUEST_RULE.forPermits(1));

    public static final ImmutableList<TypedPermitRequest> ONE_REST_PRIVATE_ORDER_CREATE_REQUEST =
        ImmutableList.of(
            IP_REST_REQUEST_RULE.forPermits(1), API_KEY_REST_ORDER_CREATE_RULE.forPermits(1));

    public static final ImmutableList<TypedPermitRequest> ONE_REST_PRIVATE_ORDER_CANCEL_REQUEST =
        ImmutableList.of(
            IP_REST_REQUEST_RULE.forPermits(1), API_KEY_REST_ORDER_CANCEL_RULE.forPermits(1));

    public static final ImmutableList<TypedPermitRequest>
        ONE_REST_PRIVATE_ORDER_CANCEL_ALL_REQUEST =
            ImmutableList.of(
                IP_REST_REQUEST_RULE.forPermits(1),
                API_KEY_REST_ORDER_CANCEL_ALL_RULE.forPermits(1));

    public static final ImmutableList<TypedPermitRequest> ONE_REST_PRIVATE_ORDER_REALTIME_REQUEST =
        ImmutableList.of(
            IP_REST_REQUEST_RULE.forPermits(1), API_KEY_REST_ORDER_REALTIME_RULE.forPermits(1));

    public static final ImmutableList<TypedPermitRequest> ONE_REST_PRIVATE_ORDER_HISTORY_REQUEST =
        ImmutableList.of(
            IP_REST_REQUEST_RULE.forPermits(1), API_KEY_REST_ORDER_HISTORY_RULE.forPermits(1));

    public static final ImmutableList<TypedPermitRequest> ONE_REST_PRIVATE_POSITION_LIST_REQUEST =
        ImmutableList.of(
            IP_REST_REQUEST_RULE.forPermits(1), API_KEY_REST_POSITION_LIST_RULE.forPermits(1));

    public static final ImmutableList<TypedPermitRequest> ONE_REST_PRIVATE_EXECUTION_LIST_REQUEST =
        ImmutableList.of(
            IP_REST_REQUEST_RULE.forPermits(1), API_KEY_REST_EXECUTION_LIST_RULE.forPermits(1));

    public static final ImmutableList<TypedPermitRequest>
        ONE_REST_PRIVATE_ACCOUNT_WALLET_BALANCE_REQUEST =
            ImmutableList.of(
                IP_REST_REQUEST_RULE.forPermits(1),
                API_KEY_REST_ACCOUNT_WALLET_BALANCE_RULE.forPermits(1));

    public static final ImmutableList<TypedPermitRequest> ONE_REST_REQUEST =
        ImmutableList.of(IP_REST_REQUEST_RULE.forPermits(1));

    private RateLimits() {}
  }
}
