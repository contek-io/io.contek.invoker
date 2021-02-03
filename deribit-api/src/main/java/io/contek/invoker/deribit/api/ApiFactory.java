package io.contek.invoker.deribit.api;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.ApiContext;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.IActorFactory;
import io.contek.invoker.commons.actor.SimpleActorFactory;
import io.contek.invoker.commons.actor.http.SimpleHttpClientFactory;
import io.contek.invoker.commons.actor.ratelimit.*;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.websocket.ConsumerState;
import io.contek.invoker.commons.websocket.ISubscribingConsumer;
import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.deribit.api.common._OrderBookLevel;
import io.contek.invoker.deribit.api.rest.market.MarketRestApi;
import io.contek.invoker.deribit.api.rest.user.*;
import io.contek.invoker.deribit.api.websocket.WebSocketApi;
import io.contek.invoker.deribit.api.websocket.market.MarketWebSocketApi;
import io.contek.invoker.deribit.api.websocket.market.OrderBookChannel;
import io.contek.invoker.deribit.api.websocket.market.TradesChannel;
import io.contek.invoker.deribit.api.websocket.user.UserWebSocketApi;
import io.contek.invoker.security.ApiKey;
import io.contek.invoker.security.SimpleCredentialFactory;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.time.Duration;
import java.util.List;

import static com.google.common.io.BaseEncoding.base16;
import static io.contek.invoker.commons.actor.ratelimit.RateLimitType.IP;
import static io.contek.invoker.security.SecretKeyAlgorithm.HMAC_SHA256;

@ThreadSafe
public final class ApiFactory {

  public static void main(String[] args) {
    ApiKey key = ApiKey.newBuilder().setId("DKSwf9Wz").setSecret("3_Vt0AOGX3ZE8z9-4k3RwBhlzthziY7TxeE6dwIqvBM").build();
//    UserRestApi user = ApiFactory.getTestNetDefault().rest().user(key);
//    GetBuy.Response res =  user
//            .getBuy()
//            .setAmount(10d)
//            .setInstrument_name("BTC-PERPETUAL")
//            .setType("market")
//            .submit();
//    System.out.println(res.result);

//    GetCancelAll.Response resCancelAll = user.getCancelAll().submit();
//    System.out.println(resCancelAll.result);

    MarketWebSocketApi wsApi = ApiFactory.getTestNetDefault().ws().market();
//    wsApi.getOrderBookChannel("BTC-PERPETUAL").addConsumer(new ISubscribingConsumer<OrderBookChannel.Message>() {
//      @Override
//      public void onStateChange(SubscriptionState state) {
//        if (state == SubscriptionState.SUBSCRIBED) {
//          System.out.println("Starting to receive order book data.");
//        }
//      }
//
//      @Override
//      public void onNext(OrderBookChannel.Message message) {
//        for (_OrderBookLevel priceAndAmount : message.params.data.asks) {
//          System.out.println("price: " + priceAndAmount.get(0) + " amount: " + priceAndAmount.get(1));
//        }
//      }
//
//      @Override
//      public ConsumerState getState() {
//        return ConsumerState.ACTIVE;
//      }
//    });

    wsApi.getTradesChannel("BTC-PERPETUAL").addConsumer(
            new ISubscribingConsumer<TradesChannel.Message>() {
              @Override
              public void onStateChange(SubscriptionState state) {
                if (state == SubscriptionState.SUBSCRIBED) {
                  System.out.println("Starting to receive trade data.");
                }
              }

              @Override
              public void onNext(TradesChannel.Message message) {
                for (TradesChannel.Trade trade : message.params.data) {
                  System.out.println("price: " + trade.price + " amount: " + trade.amount);
                }
              }

              @Override
              public ConsumerState getState() {
                return ConsumerState.ACTIVE;
              }
            }
    );
  }

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

  public static ApiFactory getMainNetDefault() {
    return fromContext(MAIN_NET_CONTEXT);
  }

  public static ApiFactory getTestNetDefault() {
    return fromContext(TEST_NET_CONTEXT);
  }



  public static ApiFactory fromContext(ApiContext context) {
    return new ApiFactory(context, createActorFactory(context.getInterceptor()));
  }

  public SelectingRestApi rest() {
    return new SelectingRestApi();
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
    return RateLimitCache.newBuilder().addRule(RateLimits.API_KEY_REST_PUBLIC_REQUEST_RULE).build();
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

    public static final RateLimitRule API_KEY_REST_PUBLIC_REQUEST_RULE =
        RateLimitRule.newBuilder()
            .setName("api_key_rest_public_request_rule")
            .setType(IP) // should be sub-account actually
            .setMaxPermits(20)
            .setResetPeriod(Duration.ofSeconds(1))
            .build();

    public static final ImmutableList<RateLimitQuota> ONE_REST_PUBLIC_REQUEST =
        ImmutableList.of(API_KEY_REST_PUBLIC_REQUEST_RULE.createRateLimitQuota(1));

    private RateLimits() {}
  }
}
