package io.contek.invoker.kraken.api;

import io.contek.invoker.commons.ApiContext;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.IActorFactory;
import io.contek.invoker.commons.actor.SimpleActorFactory;
import io.contek.invoker.commons.actor.http.SimpleHttpClientFactory;
import io.contek.invoker.commons.actor.ratelimit.IRateLimitQuotaInterceptor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitCache;
import io.contek.invoker.commons.actor.ratelimit.SimpleRateLimitThrottleFactory;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.kraken.api.websocket.market.MarketWebSocketApi;
import io.contek.invoker.security.SimpleCredentialFactory;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import static com.google.common.io.BaseEncoding.base16;
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

  }

}
