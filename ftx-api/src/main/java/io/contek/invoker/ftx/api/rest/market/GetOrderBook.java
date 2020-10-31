package io.contek.invoker.ftx.api.rest.market;

import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.rest.RestParams;
import io.contek.invoker.ftx.api.common._OrderBook;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetOrderBook extends MarketRestRequest<GetOrderBook.Response> {

  private String market_name;
  private Integer depth;

  GetOrderBook(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetOrderBook setMarketName(String market_name) {
    this.market_name = market_name;
    return this;
  }

  public GetOrderBook setDepth(Integer depth) {
    this.depth = depth;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    requireNonNull(market_name);
    return format("/api/markets/{0}/orderbook", market_name);
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(depth);
    builder.add("depth", depth);

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<_OrderBook> {}
}
