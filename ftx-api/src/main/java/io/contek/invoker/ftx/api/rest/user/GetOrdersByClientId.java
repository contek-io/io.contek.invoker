package io.contek.invoker.ftx.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._Order;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import static io.contek.invoker.commons.rest.RestMethod.GET;
import static io.contek.invoker.ftx.api.ApiFactory.RateLimits.ONE_REST_REQUEST;
import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

public final class GetOrdersByClientId extends UserRestRequest<GetOrdersByClientId.Response> {

  private String client_order_id;

  GetOrdersByClientId(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetOrdersByClientId setClientOrderId(String client_order_id) {
    this.client_order_id = client_order_id;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    requireNonNull(client_order_id);
    return format("/api/orders/by_client_id/{0}", client_order_id);
  }

  @Override
  protected RestParams getParams() {
    return RestParams.empty();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_REQUEST;
  }

  public static final class Response extends RestResponse<_Order> {}
}
