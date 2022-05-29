package io.contek.invoker.ftx.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._Order;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import static io.contek.invoker.ftx.api.ApiFactory.RateLimits.ONE_REST_REQUEST;
import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

public final class ModifyOrders extends UserRestRequest<ModifyOrders.Response> {

  private String order_id;
  private Double price;
  private Double size;

  ModifyOrders(IActor actor, RestContext context) {
    super(actor, context);
  }

  public String getOrderId() {
    return order_id;
  }

  public ModifyOrders setOrderId(String order_id) {
    this.order_id = order_id;
    return this;
  }

  public Double getPrice() {
    return price;
  }

  public ModifyOrders setPrice(Double price) {
    this.price = price;
    return this;
  }

  public Double getSize() {
    return size;
  }

  public ModifyOrders setSize(Double size) {
    this.size = size;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return RestMethod.POST;
  }

  @Override
  protected String getEndpointPath() {
    requireNonNull(order_id);
    return format("/api/orders/{0}/modify", order_id);
  }

  @Override
  protected RestParams getParams() {
    requireNonNull(price);
    requireNonNull(size);
    return RestParams.newBuilder().add("price", price).add("size", size).build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  public static final class Response extends RestResponse<_Order> {}
}
