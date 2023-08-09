package io.contek.invoker.bybit.api.rest.market;

import io.contek.invoker.bybit.api.common._InstrumentInfo;
import io.contek.invoker.bybit.api.rest.common.PageListResult;
import io.contek.invoker.bybit.api.rest.common.ResponseWrapper;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.Objects;

import static io.contek.invoker.bybit.api.rest.market.GetInstrumentsInfo.Response;

@NotThreadSafe
public final class GetInstrumentsInfo extends MarketRestRequest<Response> {

  private String category;
  private String symbol;
  private String status;
  private String baseCoin;
  private Integer limit;
  private String cursor;

  GetInstrumentsInfo(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetInstrumentsInfo setCategory(String category) {
    this.category = category;
    return this;
  }

  public GetInstrumentsInfo setSymbol(@Nullable String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetInstrumentsInfo setStatus(@Nullable String status) {
    this.status = status;
    return this;
  }

  public GetInstrumentsInfo setBaseCoin(@Nullable String baseCoin) {
    this.baseCoin = baseCoin;
    return this;
  }

  public GetInstrumentsInfo setLimit(@Nullable Integer limit) {
    this.limit = limit;
    return this;
  }

  public GetInstrumentsInfo setCursor(@Nullable String cursor) {
    this.cursor = cursor;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    return "/v5/market/instruments-info";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    Objects.requireNonNull(category);
    builder.add("category", category);

    if (symbol != null) {
      builder.add("symbol", symbol);
    }
    if (status != null) {
      builder.add("status", status);
    }
    if (baseCoin != null) {
      builder.add("baseCoin", baseCoin);
    }
    if (limit != null) {
      builder.add("limit", limit);
    }
    if (cursor != null) {
      builder.add("cursor", cursor);
    }

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends ResponseWrapper<Result> {}

  @NotThreadSafe
  public static final class Result extends PageListResult<_InstrumentInfo> {

    public String category;
  }
}
