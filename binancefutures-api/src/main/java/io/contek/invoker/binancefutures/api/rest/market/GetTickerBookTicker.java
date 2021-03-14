package io.contek.invoker.binancefutures.api.rest.market;

import static io.contek.invoker.binancefutures.api.ApiFactory.RateLimits.IP_REST_REQUEST_RULE;
import static io.contek.invoker.binancefutures.api.ApiFactory.RateLimits.ONE_REST_REQUEST;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancefutures.api.common._BookTicker;
import io.contek.invoker.binancefutures.api.rest.market.GetTickerBookTicker.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class GetTickerBookTicker extends MarketRestRequest<Response> {

  private static final ImmutableList<RateLimitQuota> ALL_SYMBOLS_REQUIRED_QUOTA =
      ImmutableList.of(IP_REST_REQUEST_RULE.createRateLimitQuota(2));

  private String symbol;

  GetTickerBookTicker(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetTickerBookTicker setSymbol(@Nullable String symbol) {
    this.symbol = symbol;
    return this;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/fapi/v1/ticker/bookTicker";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (symbol != null) {
      builder.add("symbol", symbol);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    if (symbol != null) {
      return ONE_REST_REQUEST;
    }
    return ALL_SYMBOLS_REQUIRED_QUOTA;
  }

  @NotThreadSafe
  public static final class Response extends ArrayList<_BookTicker> {}
}
