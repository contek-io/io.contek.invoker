package io.contek.invoker.bybit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.ApiFactory;
import io.contek.invoker.bybit.api.common._Position;
import io.contek.invoker.bybit.api.rest.common.PageListResult;
import io.contek.invoker.bybit.api.rest.common.ResponseWrapper;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.Objects;

import static io.contek.invoker.bybit.api.rest.user.GetPositionList.Response;
import static io.contek.invoker.commons.rest.RestMethod.GET;

@NotThreadSafe
public final class GetPositionList extends UserRestRequest<Response> {

  private String category;
  private String symbol;
  private String baseCoin;
  private String settleCoin;
  private Integer limit;
  private String cursor;

  GetPositionList(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetPositionList setCategory(String category) {
    this.category = category;
    return this;
  }

  public GetPositionList setSymbol(@Nullable String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetPositionList setBaseCoin(@Nullable String baseCoin) {
    this.baseCoin = baseCoin;
    return this;
  }

  public GetPositionList setSettleCoin(@Nullable String settleCoin) {
    this.settleCoin = settleCoin;
    return this;
  }

  public GetPositionList setLimit(@Nullable Integer limit) {
    this.limit = limit;
    return this;
  }

  public GetPositionList setCursor(@Nullable String cursor) {
    this.cursor = cursor;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/v5/position/list";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    Objects.requireNonNull(category);
    builder.add("category", category);

    if (category != null) {
      builder.add("category", category);
    }
    if (symbol != null) {
      builder.add("symbol", symbol);
    }
    if (baseCoin != null) {
      builder.add("baseCoin", baseCoin);
    }
    if (settleCoin != null) {
      builder.add("settleCoin", settleCoin);
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
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ApiFactory.RateLimits.ONE_REST_PRIVATE_POSITION_LIST_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends ResponseWrapper<Result> {}

  @NotThreadSafe
  public static final class Result extends PageListResult<_Position> {

    public String category;
  }
}
