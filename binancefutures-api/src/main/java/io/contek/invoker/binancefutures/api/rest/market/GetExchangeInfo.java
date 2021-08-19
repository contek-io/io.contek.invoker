package io.contek.invoker.binancefutures.api.rest.market;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancefutures.api.rest.market.GetExchangeInfo.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;
import java.util.Map;

import static io.contek.invoker.binancefutures.api.ApiFactory.RateLimits.ONE_REST_REQUEST;

@NotThreadSafe
public final class GetExchangeInfo extends MarketRestRequest<Response> {

  GetExchangeInfo(IActor actor, RestContext context) {
    super(actor, context);
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/fapi/v1/exchangeInfo";
  }

  @Override
  protected RestParams getParams() {
    return RestParams.empty();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_REST_REQUEST;
  }

  @NotThreadSafe
  public static final class Response {

    public List<MarketDetails> symbols;
  }

  @NotThreadSafe
  public static final class MarketDetails {

    public String symbol;
    public String status;
    public Double maintMarginPercent;
    public Double requiredMarginPercent;
    public String baseAsset;
    public String quoteAsset;
    public Integer pricePrecision;
    public Integer baseAssetPrecision;
    public Integer quotePrecision;
    public Integer quantityPrecision;
    
    public List<Map<String, Object>> filters;

    @Override
    public String toString() {
      return "MarketDetails{" +
              "symbol='" + symbol + '\'' +
              ", status='" + status + '\'' +
              ", maintMarginPercent=" + maintMarginPercent +
              ", requiredMarginPercent=" + requiredMarginPercent +
              ", baseAsset='" + baseAsset + '\'' +
              ", quoteAsset='" + quoteAsset + '\'' +
              ", pricePrecision=" + pricePrecision +
              ", baseAssetPrecision=" + baseAssetPrecision +
              ", quotePrecision=" + quotePrecision +
              ", quantityPrecision=" + quantityPrecision +
              ", filters=" + filters +
              '}';
    }
  }
}
