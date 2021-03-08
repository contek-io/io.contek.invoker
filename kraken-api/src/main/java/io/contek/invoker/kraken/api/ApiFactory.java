package io.contek.invoker.kraken.api;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.ApiContext;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.IActorFactory;
import io.contek.invoker.commons.actor.SimpleActorFactory;
import io.contek.invoker.commons.actor.http.SimpleHttpClientFactory;
import io.contek.invoker.commons.actor.ratelimit.IRateLimitQuotaInterceptor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitCache;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.actor.ratelimit.RateLimitRule;
import io.contek.invoker.commons.actor.ratelimit.SimpleRateLimitThrottleFactory;
import io.contek.invoker.commons.websocket.ConsumerState;
import io.contek.invoker.commons.websocket.ISubscribingConsumer;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.kraken.api.common._OrderBook;
import io.contek.invoker.kraken.api.common._Trade;
import io.contek.invoker.kraken.api.websocket.market.MarketWebSocketApi;
import io.contek.invoker.kraken.api.websocket.market.OrderBookChannel;
import io.contek.invoker.kraken.api.websocket.market.TradesChannel;
import io.contek.invoker.kraken.api.websocket.user.UserWebSocketApi;
import io.contek.invoker.security.ApiKey;
import io.contek.invoker.security.SimpleCredentialFactory;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.time.Duration;

import static com.google.common.io.BaseEncoding.base16;
import static io.contek.invoker.commons.actor.ratelimit.RateLimitType.API_KEY;
import static io.contek.invoker.commons.actor.ratelimit.RateLimitType.IP;
import static io.contek.invoker.security.SecretKeyAlgorithm.HMAC_SHA256;

@ThreadSafe
public final class ApiFactory {

  public static final ApiContext MAIN_NET_CONTEXT =
    ApiContext.newBuilder()
      .setWebSocketContext(WebSocketContext.forBaseUrl("wss://ws.kraken.com"))
      .build();

  public static final ApiContext TEST_NET_CONTEXT =
    ApiContext.newBuilder()
      .setWebSocketContext(WebSocketContext.forBaseUrl("wss://beta-ws.kraken.com"))
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
    return new ApiFactory(context, createActorFactory(context.getInterceptor()));
  }


  public SelectingWebSocketApi ws() {
    return new SelectingWebSocketApi();
  }

  private static SimpleActorFactory createActorFactory(
    @Nullable IRateLimitQuotaInterceptor interceptor) {
    return SimpleActorFactory.newBuilder()
      .setCredentialFactory(createCredentialFactory())
      .setHttpClientFactory(SimpleHttpClientFactory.getInstance())
      .setRateLimitThrottleFactory(
        SimpleRateLimitThrottleFactory.create(createRateLimitCache(), interceptor))
      .build();
  }

  private static SimpleCredentialFactory createCredentialFactory() {
    return SimpleCredentialFactory.newBuilder()
      .setAlgorithm(HMAC_SHA256)
      .setEncoding(base16().lowerCase())
      .build();
  }

  private static RateLimitCache createRateLimitCache() {
    return RateLimitCache.newBuilder()
      .addRule(RateLimits.MATCHING_ENGINE_REQUEST_RULE)
      .addRule(RateLimits.NON_MATCHING_ENGINE_REQUEST_RULE)
      .build();
  }

  @ThreadSafe
  public final class SelectingWebSocketApi {

    private SelectingWebSocketApi() {
    }

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

    public static final RateLimitRule MATCHING_ENGINE_REQUEST_RULE =
      RateLimitRule.newBuilder()
        .setName("matching_engine_request_rule")
        .setType(API_KEY) // per sub-account
        .setMaxPermits(5)
        .setResetPeriod(Duration.ofSeconds(1))
        .build();

    public static final RateLimitRule NON_MATCHING_ENGINE_REQUEST_RULE =
      RateLimitRule.newBuilder()
        .setName("non_matching_engine_request_rule")
        .setType(IP)
        .setMaxPermits(20)
        .setResetPeriod(Duration.ofSeconds(1))
        .build();

    public static final ImmutableList<RateLimitQuota> ONE_MATCHING_ENGINE_REQUEST =
      ImmutableList.of(MATCHING_ENGINE_REQUEST_RULE.createRateLimitQuota(1));


    public static final ImmutableList<RateLimitQuota> ONE_NON_MATCHING_ENGINE_REQUEST =
      ImmutableList.of(NON_MATCHING_ENGINE_REQUEST_RULE.createRateLimitQuota(1));

    private RateLimits() {
    }
  }
}
