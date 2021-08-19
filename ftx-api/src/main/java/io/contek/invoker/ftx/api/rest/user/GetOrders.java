package io.contek.invoker.ftx.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._Order;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetOrders extends UserRestRequest<GetOrders.Response> {

  private String orderId;

  public GetOrders(IActor actor, RestContext context) {
    super(actor, context);
  }

  public String getOrderId() {
    return orderId;
  }

  public GetOrders setOrderId(String order_id) {
    this.orderId = order_id;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return RestMethod.GET;
  }

  @Override
  protected String getEndpointPath() {
    requireNonNull(orderId);
    return format("/api/orders/{0}", orderId);
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
