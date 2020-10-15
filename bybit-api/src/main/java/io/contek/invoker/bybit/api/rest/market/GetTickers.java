package io.contek.invoker.bybit.api.rest.market;

import static io.contek.invoker.bybit.api.rest.market.GetTickers.Response;

import io.contek.invoker.bybit.api.common._Ticker;
import io.contek.invoker.bybit.api.rest.common.RestResponse;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.rest.RestParams;
import java.util.List;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class GetTickers extends MarketRestRequest<Response> {

  private String symbol;

  GetTickers(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetTickers setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    return "/v2/public/tickers";
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
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<List<_Ticker>> {}
}
