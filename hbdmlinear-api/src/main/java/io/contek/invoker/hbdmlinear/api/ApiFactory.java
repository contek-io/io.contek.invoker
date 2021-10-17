package io.contek.invoker.hbdmlinear.api;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.ApiContext;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.IActorFactory;
import io.contek.invoker.commons.actor.SimpleActorFactory;
import io.contek.invoker.commons.actor.http.SimpleHttpClientFactory;
import io.contek.invoker.commons.actor.ratelimit.*;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.hbdmlinear.api.rest.market.MarketRestApi;
import io.contek.invoker.hbdmlinear.api.rest.user.UserRestApi;
import io.contek.invoker.hbdmlinear.api.websocket.market.MarketNotificationWebSocketApi;
import io.contek.invoker.hbdmlinear.api.websocket.market.MarketWebSocketApi;
import io.contek.invoker.hbdmlinear.api.websocket.user.UserWebSocketApi;
import io.contek.invoker.security.ApiKey;
import io.contek.invoker.security.SimpleCredentialFactory;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.time.Duration;
import java.util.List;

import static com.google.common.io.BaseEncoding.base64;
import static io.contek.invoker.commons.actor.ratelimit.RateLimitType.API_KEY;
import static io.contek.invoker.commons.actor.ratelimit.RateLimitType.IP;
import static io.contek.invoker.security.SecretKeyAlgorithm.HMAC_SHA256;

@ThreadSafe
public final class ApiFactory {

  public static final ApiContext MAIN_NET_CONTEXT =
      ApiContext.newBuilder()
          .setRestContext(RestContext.forBaseUrl("https://api.hbdm.com"))
          .setWebSocketContext(WebSocketContext.forBaseUrl("wss://api.hbdm.com"))
          .build();

  public static final ApiContext INSECURE_MAIN_NET_CONTEXT =
      ApiContext.newBuilder()
          .setRestContext(RestContext.forBaseUrl("http://api.hbdm.com"))
          .setWebSocketContext(WebSocketContext.forBaseUrl("ws://api.hbdm.com"))
          .build();

  public static final ApiContext BTCGATEWAY_CONTEXT =
      ApiContext.newBuilder()
          .setRestContext(RestContext.forBaseUrl("https://api.btcgateway.pro"))
          .setWebSocketContext(WebSocketContext.forBaseUrl("wss://api.btcgateway.pro"))
          .build();

  public static final ApiContext INSECURE_BTCGATEWAY_CONTEXT =
      ApiContext.newBuilder()
          .setRestContext(RestContext.forBaseUrl("http://api.btcgateway.pro"))
          .setWebSocketContext(WebSocketContext.forBaseUrl("ws://api.btcgateway.pro"))
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

  public static ApiFactory getInsecureMainNet() {
    return fromContext(INSECURE_MAIN_NET_CONTEXT);
  }

  public static ApiFactory getBtcGateway() {
    return fromContext(BTCGATEWAY_CONTEXT);
  }

  public static ApiFactory getInsecureBtcGateway() {
    return fromContext(INSECURE_BTCGATEWAY_CONTEXT);
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
            SimpleRateLimitThrottleFactory.create(createRateLimitCache(cushion), interceptors))
        .build();
  }

  private static SimpleCredentialFactory createCredentialFactory() {
    return SimpleCredentialFactory.newBuilder()
        .setAlgorithm(HMAC_SHA256)
        .setEncoding(base64())
        .build();
  }

  private static RateLimitCache createRateLimitCache(RateLimitCushion cushion) {
    return RateLimitCache.newBuilder()
        .setCushion(cushion)
        .addRule(RateLimits.IP_REST_PUBLIC_MARKET_DATA_REQUEST_RULE)
        .addRule(RateLimits.IP_REST_PUBLIC_NON_MARKET_DATA_REQUEST_RULE)
        .addRule(RateLimits.API_KEY_REST_PRIVATE_READ_REQUEST_RULE)
        .addRule(RateLimits.API_KEY_REST_PRIVATE_WRITE_REQUEST_RULE)
        .addRule(RateLimits.IP_WEB_SOCKET_CONNECTION_RULE)
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

    public MarketNotificationWebSocketApi marketNotification() {
      WebSocketContext wsContext = context.getWebSocketContext();
      IActor actor = actorFactory.create(null, wsContext);
      return new MarketNotificationWebSocketApi(actor, wsContext);
    }

    public UserWebSocketApi user(ApiKey apiKey) {
      WebSocketContext wsContext = context.getWebSocketContext();
      IActor actor = actorFactory.create(apiKey, wsContext);
      return new UserWebSocketApi(actor, context.getWebSocketContext());
    }
  }

  @Immutable
  public static final class RateLimits {

    public static final RateLimitRule IP_REST_PUBLIC_MARKET_DATA_REQUEST_RULE =
        RateLimitRule.newBuilder()
            .setName("ip_rest_public_market_data_request_rule")
            .setType(IP)
            .setMaxPermits(800)
            .setResetPeriod(Duration.ofSeconds(1))
            .build();

    public static final RateLimitRule IP_REST_PUBLIC_NON_MARKET_DATA_REQUEST_RULE =
        RateLimitRule.newBuilder()
            .setName("ip_rest_public_non_market_data_request_rule")
            .setType(IP)
            .setMaxPermits(120)
            .setResetPeriod(Duration.ofSeconds(3))
            .build();

    public static final RateLimitRule API_KEY_REST_PRIVATE_READ_REQUEST_RULE =
        RateLimitRule.newBuilder()
            .setName("api_key_rest_private_read_request_rule")
            .setType(API_KEY)
            .setMaxPermits(36)
            .setResetPeriod(Duration.ofSeconds(3))
            .build();

    public static final RateLimitRule API_KEY_REST_PRIVATE_WRITE_REQUEST_RULE =
        RateLimitRule.newBuilder()
            .setName("api_key_rest_private_write_request_rule")
            .setType(API_KEY)
            .setMaxPermits(36)
            .setResetPeriod(Duration.ofSeconds(3))
            .build();

    public static final RateLimitRule IP_WEB_SOCKET_CONNECTION_RULE =
        RateLimitRule.newBuilder()
            .setName("ip_web_socket_connection_rule")
            .setType(IP)
            .setMaxPermits(50)
            .setResetPeriod(Duration.ofSeconds(1))
            .build();

    public static final ImmutableList<RateLimitQuota> ONE_IP_REST_PUBLIC_MARKET_DATA_REQUEST =
        ImmutableList.of(IP_REST_PUBLIC_MARKET_DATA_REQUEST_RULE.createRateLimitQuota(1));

    public static final ImmutableList<RateLimitQuota> ONE_IP_REST_PUBLIC_NON_MARKET_DATA_REQUEST =
        ImmutableList.of(IP_REST_PUBLIC_NON_MARKET_DATA_REQUEST_RULE.createRateLimitQuota(1));

    public static final ImmutableList<RateLimitQuota> ONE_API_KEY_REST_PRIVATE_READ_REQUEST =
        ImmutableList.of(API_KEY_REST_PRIVATE_READ_REQUEST_RULE.createRateLimitQuota(1));

    public static final ImmutableList<RateLimitQuota> ONE_API_KEY_REST_PRIVATE_WRITE_REQUEST =
        ImmutableList.of(API_KEY_REST_PRIVATE_WRITE_REQUEST_RULE.createRateLimitQuota(1));

    public static final ImmutableList<RateLimitQuota> ONE_IP_WEB_SOCKET_CONNECTION_REQUEST =
        ImmutableList.of(IP_WEB_SOCKET_CONNECTION_RULE.createRateLimitQuota(1));

    private RateLimits() {}
  }
}
