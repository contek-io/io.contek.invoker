package io.contek.invoker.okx.api;

import io.contek.invoker.commons.ApiContext;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.IActorFactory;
import io.contek.invoker.commons.actor.SimpleActorFactory;
import io.contek.invoker.commons.actor.ratelimit.IRateLimitQuotaInterceptor;
import io.contek.invoker.commons.actor.ratelimit.LimiterManagers;
import io.contek.invoker.commons.actor.ratelimit.SimpleRateLimitThrottleFactory;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.okx.api.rest.market.*;
import io.contek.invoker.okx.api.rest.user.*;
import io.contek.invoker.okx.api.websocket.WebSocketApi;
import io.contek.invoker.okx.api.websocket.market.MarketWebSocketApi;
import io.contek.invoker.okx.api.websocket.user.UserWebSocketApi;
import io.contek.invoker.security.ApiKey;
import io.contek.invoker.security.SimpleCredentialFactory;
import io.contek.ursa.cache.LimiterManager;
import io.vertx.core.Vertx;

import java.time.Duration;
import java.util.List;

import static com.google.common.io.BaseEncoding.base64;
import static io.contek.invoker.security.SecretKeyAlgorithm.HMAC_SHA256;

public final class ApiFactory {

  public static final ApiContext MAIN_NET_CONTEXT =
      ApiContext.newBuilder()
          .setRestContext(RestContext.of("https://www.okx.com"))
          .setWebSocketContext(WebSocketContext.of("wss://ws.okx.com:8443", Duration.ofSeconds(15)))
          .build();

  public static final ApiContext AWS_NET_CONTEXT =
      ApiContext.newBuilder()
          .setRestContext(RestContext.of("https://aws.okx.com"))
          .setWebSocketContext(
              WebSocketContext.of("wss://wsaws.okx.com:8443", Duration.ofSeconds(15)))
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

  public static ApiFactory getAwsNet() {
    return fromContext(MAIN_NET_CONTEXT);
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
        .setEncoding(base64())
        .build();
  }

  private static LimiterManager createLimiterManager() {
    return LimiterManagers.forRules(
        GetMarketBooks.RATE_LIMIT_RULE,
        GetMarketCandles.RATE_LIMIT_RULE,
        GetMarketHistoryCandles.RATE_LIMIT_RULE,
        GetMarketIndexCandles.RATE_LIMIT_RULE,
        GetMarketMarkPriceCandles.RATE_LIMIT_RULE,
        GetMarketTickers.RATE_LIMIT_RULE,
        GetMarketTrades.RATE_LIMIT_RULE,
        GetPublicInstruments.RATE_LIMIT_RULE,
        GetAccountAccountPositionRisk.RATE_LIMIT_RULE,
        GetAccountBalance.RATE_LIMIT_RULE,
        GetAccountConfig.RATE_LIMIT_RULE,
        GetAccountPositions.RATE_LIMIT_RULE,
        GetAssetBalances.RATE_LIMIT_RULE,
        GetTradeFills.RATE_LIMIT_RULE,
        GetTradeFillsHistory.RATE_LIMIT_RULE,
        GetTradeOrder.RATE_LIMIT_RULE,
        GetTradeOrderHistory.RATE_LIMIT_RULE,
        GetTradeOrderHistoryArchive.RATE_LIMIT_RULE,
        GetTradeOrdersPending.RATE_LIMIT_RULE,
        PostAccountSetLeverage.RATE_LIMIT_RULE,
        PostAccountSetPositionMode.RATE_LIMIT_RULE,
        PostTradeCancelOrder.RATE_LIMIT_RULE,
        PostTradeCancelOrder.RATE_LIMIT_RULE,
        WebSocketApi.RATE_LIMIT_RULE);
  }

  public SelectingRestApi rest() {
    return new SelectingRestApi();
  }

  public SelectingWebSocketApi ws() {
    return new SelectingWebSocketApi();
  }

  public final class SelectingRestApi {

    private SelectingRestApi() {}

    public MarketRestApi market(Vertx vertx) {
      RestContext restContext = context.getRestContext();
      IActor actor = actorFactory.create(null, vertx, restContext);
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
