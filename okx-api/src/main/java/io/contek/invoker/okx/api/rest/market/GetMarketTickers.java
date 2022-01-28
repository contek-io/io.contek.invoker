package io.contek.invoker.okx.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.okx.api.common._Ticker;
import io.contek.invoker.okx.api.rest.common.RestResponse;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetMarketTickers extends MarketRestRequest<GetMarketTickers.Response> {

  private String instType;
  private String uly;

  GetMarketTickers(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetMarketTickers setInstType(String instType) {
    this.instType = instType;
    return this;
  }

  public GetMarketTickers setUly(@Nullable String uly) {
    this.uly = uly;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v5/market/tickers";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(instType);
    builder.add("instType", instType);

    if (uly != null) {
      builder.add("uly", uly);
    }

    return RestParams.empty();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<List<_Ticker>> {}
}
