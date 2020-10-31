package io.contek.invoker.ftx.api.rest.user;

import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.rest.RestMethod;
import io.contek.invoker.commons.api.rest.RestParams;
import io.contek.invoker.ftx.api.common._Order;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.commons.api.rest.RestMethod.GET;
import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
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
    return format("/api/orders/client_order_id/{0}", client_order_id);
  }

  @Override
  protected RestParams getParams() {
    return RestParams.empty();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<_Order> {}
}
