package io.contek.invoker.deribit.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.deribit.api.common._OrderBook;
import io.contek.invoker.deribit.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetOrderBook extends MarketRestRequest<GetOrderBook.Response> {

  private String instrument_name;
  private Integer depth;

  GetOrderBook(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetOrderBook setInstrumentName(String market_name) {
    this.instrument_name = market_name;
    return this;
  }

  public GetOrderBook setDepth(Integer depth) {
    this.depth = depth;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v2/public/get_order_book";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(depth);
    builder.add("depth", depth);
    requireNonNull(instrument_name);
    builder.add("instrument_name", instrument_name);

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<_OrderBook> {}
}
