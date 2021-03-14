package io.contek.invoker.binancedelivery.api.rest.market;

import static io.contek.invoker.binancedelivery.api.ApiFactory.RateLimits.IP_REST_REQUEST_RULE;
import static io.contek.invoker.binancedelivery.api.ApiFactory.RateLimits.ONE_REST_REQUEST;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancedelivery.api.common._BookTicker;
import io.contek.invoker.binancedelivery.api.rest.market.GetTickerBookTicker.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class GetTickerBookTicker extends MarketRestRequest<Response> {

  private static final ImmutableList<RateLimitQuota> MULTI_SYMBOLS_REQUIRED_QUOTA =
      ImmutableList.of(IP_REST_REQUEST_RULE.createRateLimitQuota(2));

  private String symbol;
  private String pair;

  GetTickerBookTicker(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetTickerBookTicker setSymbol(@Nullable String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetTickerBookTicker setPair(@Nullable String pair) {
    this.pair = pair;
    return this;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/dapi/v1/ticker/bookTicker";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (symbol != null) {
      builder.add("symbol", symbol);
    }

    if (pair != null) {
      builder.add("pair", pair);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    if (symbol != null) {
      return ONE_REST_REQUEST;
    }
    return MULTI_SYMBOLS_REQUIRED_QUOTA;
  }

  @NotThreadSafe
  public static final class Response extends ArrayList<_BookTicker> {}
}
