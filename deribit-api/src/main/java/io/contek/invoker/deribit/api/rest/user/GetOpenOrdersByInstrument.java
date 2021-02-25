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
import static io.contek.invoker.deribit.api.ApiFactory.RateLimits.ONE_NON_MATCHING_ENGINE_REQUEST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetOpenOrdersByInstrument
    extends UserRestRequest<GetOpenOrdersByInstrument.Response> {

  private String instrument_name;
  private String type;

  GetOpenOrdersByInstrument(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetOpenOrdersByInstrument setInstrumentName(String instrument_name) {
    this.instrument_name = instrument_name;
    return this;
  }

  public GetOpenOrdersByInstrument setType(String type) {
    this.type = type;
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

    if (type != null) {
      builder.add("type", type);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_NON_MATCHING_ENGINE_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<List<_Order>> {}
}
