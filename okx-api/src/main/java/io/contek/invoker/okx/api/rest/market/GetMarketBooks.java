package io.contek.invoker.okx.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.okx.api.common._OrderBook;
import io.contek.invoker.okx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetMarketBooks extends MarketRestRequest<GetMarketBooks.Response> {

  private String instId;
  private Integer sz;

  GetMarketBooks(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetMarketBooks setInstId(String instId) {
    this.instId = instId;
    return this;
  }

  public GetMarketBooks setSz(Integer sz) {
    this.sz = sz;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v5/market/books";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(instId);
    builder.add("instId", instId);

    if (sz != null) {
      builder.add("sz", sz);
    }

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<_OrderBook> {}
}
