package io.contek.invoker.bybitlinear.api.rest.market;

import io.contek.invoker.bybitlinear.api.common._OrderBook;
import io.contek.invoker.bybitlinear.api.rest.common.RestResponse;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybitlinear.api.rest.market.GetOrderBookL2.Response;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetOrderBookL2 extends MarketRestRequest<Response> {

  private String symbol;

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

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<_OrderBook> {}
}
