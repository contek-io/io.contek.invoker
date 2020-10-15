package io.contek.invoker.bybit.api.rest.market;

import static io.contek.invoker.bybit.api.rest.market.GetAccountRatio.Response;
import static java.util.Objects.requireNonNull;

import io.contek.invoker.bybit.api.common._AccountRatio;
import io.contek.invoker.bybit.api.rest.common.RestResponse;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.rest.RestParams;
import java.util.List;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class GetAccountRatio extends MarketRestRequest<Response> {

  public static final int MAX_LIMIT = 500;

  private String symbol;
  private String period;
  private Integer limit;

  GetAccountRatio(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetAccountRatio setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetAccountRatio setPeriod(String period) {
    this.period = period;
    return this;
  }

  public GetAccountRatio setLimit(Integer limit) {
    this.limit = limit;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    return "/v2/public/account-ratio";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    requireNonNull(period);
    builder.add("period", period);

    if (limit != null) {
      if (limit > MAX_LIMIT) {
        throw new IllegalArgumentException(Integer.toString(limit));
      }
      builder.add("limit", limit);
    }

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<List<_AccountRatio>> {}
}
