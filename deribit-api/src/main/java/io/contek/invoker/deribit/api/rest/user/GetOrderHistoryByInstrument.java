package io.contek.invoker.deribit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.deribit.api.common._Order;
import io.contek.invoker.deribit.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static io.contek.invoker.commons.rest.RestMethod.GET;
import static io.contek.invoker.deribit.api.ApiFactory.RateLimits.ONE_API_KEY_NON_MATCHING_ENGINE_REQUEST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetOrderHistoryByInstrument
    extends UserRestRequest<GetOrderHistoryByInstrument.Response> {

  private String instrument_name;
  private Integer count;
  private Integer offset;
  private Boolean include_old;
  private Boolean include_unfilled;

  GetOrderHistoryByInstrument(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetOrderHistoryByInstrument setInstrumentName(String instrument_name) {
    this.instrument_name = instrument_name;
    return this;
  }

  public GetOrderHistoryByInstrument setCount(Integer count) {
    this.count = count;
    return this;
  }

  public GetOrderHistoryByInstrument setOffset(Integer offset) {
    this.offset = offset;
    return this;
  }

  public GetOrderHistoryByInstrument setIncludeOld(Boolean include_old) {
    this.include_old = include_old;
    return this;
  }

  public GetOrderHistoryByInstrument setIncludeUnfilled(Boolean include_unfilled) {
    this.include_unfilled = include_unfilled;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v2/private/get_open_orders_by_instrument";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(instrument_name);
    builder.add("instrument_name", instrument_name);

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
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_API_KEY_NON_MATCHING_ENGINE_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<List<_Order>> {}
}
