package io.contek.invoker.bybit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.ApiFactory;
import io.contek.invoker.bybit.api.common._OrderRef;
import io.contek.invoker.bybit.api.rest.common.ListResult;
import io.contek.invoker.bybit.api.rest.common.ResponseWrapper;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybit.api.rest.user.PostOrderCancelAll.Response;
import static io.contek.invoker.commons.rest.RestMethod.POST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostOrderCancelAll extends UserRestRequest<Response> {

  private String category;
  private String symbol;
  private String baseCoin;
  private String settleCoin;
  private String orderFilter;

  PostOrderCancelAll(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostOrderCancelAll setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/v5/order/cancel-all";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(category);
    builder.add("category", category);

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    if (baseCoin != null) {
      builder.add("baseCoin", baseCoin);
    }
    if (settleCoin != null) {
      builder.add("settleCoin", settleCoin);
    }
    if (orderFilter != null) {
      builder.add("orderFilter", orderFilter);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ApiFactory.RateLimits.ONE_REST_PRIVATE_ORDER_CREATE_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends ResponseWrapper<Result> {}

  @NotThreadSafe
  public static final class Result extends ListResult<_OrderRef> {}
}
