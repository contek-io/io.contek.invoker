package io.contek.invoker.bybit.api.rest.market;

import io.contek.invoker.bybit.api.common._OrderBookLevel;
import io.contek.invoker.bybit.api.rest.common.RestResponse;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static io.contek.invoker.bybit.api.rest.market.GetOrderBookL2.Response;

@NotThreadSafe
public final class GetOrderBookL2 extends MarketRestRequest<Response> {

  public String symbol;

  GetOrderBookL2(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetOrderBookL2 setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    return "/v2/public/orderBook/L2";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    builder.add("symbol", symbol);

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<List<_OrderBookLevel>> {}
}
