package io.contek.invoker.binancefutures.api;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancefutures.api.rest.market.MarketRestApi;
import io.contek.invoker.binancefutures.api.rest.user.UserRestApi;
import io.contek.invoker.binancefutures.api.websocket.market.MarketWebSocketApi;
import io.contek.invoker.binancefutures.api.websocket.user.UserWebSocketApi;
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

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.time.Duration;

import static com.google.common.io.BaseEncoding.base16;
import static io.contek.invoker.binancefutures.api.ApiFactory.RateLimits.API_KEY_REST_ORDER_RULE;
import static io.contek.invoker.binancefutures.api.ApiFactory.RateLimits.IP_REST_REQUEST_RULE;
import static io.contek.invoker.commons.actor.ratelimit.RateLimitType.API_KEY;
import static io.contek.invoker.commons.actor.ratelimit.RateLimitType.IP;
import static io.contek.invoker.security.SecretKeyAlgorithm.HMAC_SHA256;

@ThreadSafe
public final class ApiFactory {

  public static final ApiContext MAIN_NET_CONTEXT =
      ApiContext.newBuilder()
          .setRestContext(RestContext.forBaseUrl("https://fapi.binance.com"))
          .setWebSocketContext(WebSocketContext.forBaseUrl("wss://fstream.binance.com"))
          .build();

  public static final ApiContext TEST_NET_CONTEXT =
      ApiContext.newBuilder()
          .setRestContext(RestContext.forBaseUrl("https://testnet.binancefuture.com"))
          .setWebSocketContext(WebSocketContext.forBaseUrl("wss://stream.binancefuture.com"))
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

  public static ApiFactory getTestNetDefault() {
    return fromContext(TEST_NET_CONTEXT);
  }

  public static ApiFactory fromContext(ApiContext context) {
    return new ApiFactory(
        context, createActorFactory(context.getRateLimitCushion(), context.getInterceptor()));
  }

  private static SimpleActorFactory createActorFactory(
      double rateLimitCushion, @Nullable IRateLimitQuotaInterceptor interceptor) {
    return SimpleActorFactory.newBuilder()
        .setCredentialFactory(createCredentialFactory())
        .setHttpClientFactory(SimpleHttpClientFactory.getInstance())
        .setRateLimitThrottleFactory(
            SimpleRateLimitThrottleFactory.create(
                createRateLimitCache(rateLimitCushion), interceptor))
        .build();
  }

  private static SimpleCredentialFactory createCredentialFactory() {
    return SimpleCredentialFactory.newBuilder()
        .setAlgorithm(HMAC_SHA256)
        .setEncoding(base16().lowerCase())
        .build();
  }

  private static RateLimitCache createRateLimitCache(double cushion) {
    return RateLimitCache.newBuilder()
        .setCushion(cushion)
        .addRule(IP_REST_REQUEST_RULE)
        .addRule(API_KEY_REST_ORDER_RULE)
        .build();
  }

  public SelectingRestApi rest() {
    return new SelectingRestApi();
  }

  public SelectingWebSocketApi ws() {
    return new SelectingWebSocketApi();
  }

  @Immutable
  public static final class RateLimits {

    public static final RateLimitRule IP_REST_REQUEST_RULE =
        RateLimitRule.newBuilder()
            .setName("ip_rest_request_rule")
            .setType(IP)
            .setMaxPermits(2400)
            .setResetPeriod(Duration.ofMinutes(1))
            .build();

    public static final RateLimitRule API_KEY_REST_ORDER_RULE =
        RateLimitRule.newBuilder()
            .setName("api_key_rest_order_rule")
            .setType(API_KEY)
            .setMaxPermits(1200)
            .setResetPeriod(Duration.ofMinutes(1))
            .build();

    public static final ImmutableList<RateLimitQuota> ONE_REST_REQUEST =
        ImmutableList.of(IP_REST_REQUEST_RULE.createRateLimitQuota(1));

    public static final ImmutableList<RateLimitQuota> ONE_REST_ORDER_REQUEST =
        ImmutableList.of(
            IP_REST_REQUEST_RULE.createRateLimitQuota(1),
            API_KEY_REST_ORDER_RULE.createRateLimitQuota(1));

    private RateLimits() {}
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
}
