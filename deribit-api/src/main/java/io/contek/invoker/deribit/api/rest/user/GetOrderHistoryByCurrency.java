package io.contek.invoker.deribit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.deribit.api.common._Order;
import io.contek.invoker.deribit.api.rest.common.RestResponse;

import java.util.List;

import static io.contek.invoker.commons.rest.RestMethod.GET;
import static io.contek.invoker.deribit.api.ApiFactory.RateLimits.ONE_API_KEY_NON_MATCHING_ENGINE_REQUEST;
import static java.util.Objects.requireNonNull;

public final class GetOrderHistoryByCurrency
    extends UserRestRequest<GetOrderHistoryByCurrency.Response> {

  private String currency;
  private String kind;
  private Integer count;
  private Integer offset;
  private Boolean include_old;
  private Boolean include_unfilled;

  GetOrderHistoryByCurrency(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetOrderHistoryByCurrency setCurrency(String currency) {
    this.currency = currency;
    return this;
  }

  public GetOrderHistoryByCurrency setKind(String kind) {
    this.kind = kind;
    return this;
  }

  public GetOrderHistoryByCurrency setCount(Integer count) {
    this.count = count;
    return this;
  }

  public GetOrderHistoryByCurrency setOffset(Integer offset) {
    this.offset = offset;
    return this;
  }

  public GetOrderHistoryByCurrency setIncludeOld(Boolean include_old) {
    this.include_old = include_old;
    return this;
  }

  public GetOrderHistoryByCurrency setIncludeUnfilled(Boolean include_unfilled) {
    this.include_unfilled = include_unfilled;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v2/private/get_order_history_by_currency";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(currency);
    builder.add("currency", currency);

    if (kind != null) {
      builder.add("kind", kind);
    }

    if (count != null) {
      builder.add("count", count);
    }

    if (offset != null) {
      builder.add("offset", offset);
    }

    if (include_old != null) {
      builder.add("include_old", include_old);
    }

    if (include_unfilled != null) {
      builder.add("include_unfilled", include_unfilled);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_API_KEY_NON_MATCHING_ENGINE_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  public static final class Response extends RestResponse<List<_Order>> {}
}
