package io.contek.invoker.deribit.api.rest.market;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.deribit.api.common._OrderBook;
import io.contek.invoker.deribit.api.rest.common.RestResponse;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.deribit.api.ApiFactory.RateLimits.ONE_IP_NON_MATCHING_ENGINE_REQUEST;
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

  public GetOrderBook setDepth(@Nullable Integer depth) {
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

    requireNonNull(instrument_name);
    builder.add("instrument_name", instrument_name);

    if (depth != null) {
      builder.add("depth", depth);
    }

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_IP_NON_MATCHING_ENGINE_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<_OrderBook> {}
}
