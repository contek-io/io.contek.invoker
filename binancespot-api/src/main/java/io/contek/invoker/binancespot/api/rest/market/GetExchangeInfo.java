package io.contek.invoker.binancespot.api.rest.market;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancespot.api.rest.market.GetExchangeInfo.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;
import java.util.Map;

import static io.contek.invoker.binancespot.api.ApiFactory.RateLimits.IP_REST_REQUEST_RULE;

@NotThreadSafe
public final class GetExchangeInfo extends MarketRestRequest<Response> {

  private static final ImmutableList<RateLimitQuota> REQUIRED_QUOTA =
      ImmutableList.of(IP_REST_REQUEST_RULE.createRateLimitQuota(10));

  GetExchangeInfo(IActor actor, RestContext context) {
    super(actor, context);
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v3/exchangeInfo";
  }

  @Override
  protected RestParams getParams() {
    return RestParams.empty();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return REQUIRED_QUOTA;
  }

  @NotThreadSafe
  public static final class Response {

    public List<MarketDetails> symbols;

    @Override
    public String toString() {
      return "Response{" +
              "symbols=" + symbols +
              '}';
    }
  }

  @NotThreadSafe
  public static final class MarketDetails {

    public String symbol;
    public String status;
    public String baseAsset;
    public Integer baseAssetPrecision;
    public String quoteAsset;
    public Integer quotePrecision;
    public Integer quoteAssetPrecision;
    public List<String> orderTypes;
    public Boolean icebergAllowed;
    public Boolean ocoAllowed;
    public Boolean isSpotTradingAllowed;
    public Boolean isMarginTradingAllowed;
    public List<String> permissions;
    public List<Map<String, Object>> filters;

    @Override
    public String toString() {
      return "MarketDetails{" +
              "symbol='" + symbol + '\'' +
              ", status='" + status + '\'' +
              ", baseAsset='" + baseAsset + '\'' +
              ", baseAssetPrecision=" + baseAssetPrecision +
              ", quoteAsset='" + quoteAsset + '\'' +
              ", quotePrecision=" + quotePrecision +
              ", quoteAssetPrecision=" + quoteAssetPrecision +
              ", orderTypes=" + orderTypes +
              ", icebergAllowed=" + icebergAllowed +
              ", ocoAllowed=" + ocoAllowed +
              ", isSpotTradingAllowed=" + isSpotTradingAllowed +
              ", isMarginTradingAllowed=" + isMarginTradingAllowed +
              ", permissions=" + permissions +
              ", filters=" + filters +
              '}';
    }
  }
}
