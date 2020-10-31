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
public final class GetOrders extends UserRestRequest<GetOrders.Response> {

  private String order_id;

  GetOrders(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetOrders setOrderId(String order_id) {
    this.order_id = order_id;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    requireNonNull(order_id);
    return format("/api/orders/{0}", order_id);
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
