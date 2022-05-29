package io.contek.invoker.deribit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.deribit.api.common._PlaceOrderResponse;
import io.contek.invoker.deribit.api.rest.common.RestResponse;

import static io.contek.invoker.commons.rest.RestMethod.GET;
import static io.contek.invoker.deribit.api.ApiFactory.RateLimits.ONE_API_KEY_MATCHING_ENGINE_REQUEST;
import static java.util.Objects.requireNonNull;

public final class GetClosePosition extends UserRestRequest<GetClosePosition.Response> {

  private String instrument_name;
  private String type;
  private Double price;

  GetClosePosition(IActor actor, RestContext context) {
    super(actor, context);
  }

  public final GetClosePosition setInstrumentName(String instrument_name) {
    this.instrument_name = instrument_name;
    return this;
  }

  public final GetClosePosition setType(String type) {
    this.type = type;
    return this;
  }

  public final GetClosePosition setPrice(double price) {
    this.price = price;
    return this;
  }

  @Override
  protected final RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v2/private/close_position";
  }

  @Override
  protected final RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(instrument_name);
    builder.add("instrument_name", instrument_name);

    requireNonNull(type);
    builder.add("type", type);

    if (price != null) {
      builder.add("price", price);
    }

    return builder.build();
  }

  @Override
  protected final Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected final ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_API_KEY_MATCHING_ENGINE_REQUEST;
  }

  public static final class Response extends RestResponse<_PlaceOrderResponse> {}
}
