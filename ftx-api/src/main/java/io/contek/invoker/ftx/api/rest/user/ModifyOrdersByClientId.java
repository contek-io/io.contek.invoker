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

public final class ModifyOrdersByClientId extends UserRestRequest<ModifyOrdersByClientId.Response> {

  private String client_order_id;
  private Double price;
  private Double size;

  ModifyOrdersByClientId(IActor actor, RestContext context) {
    super(actor, context);
  }

  public String getClientOrderId() {
    return client_order_id;
  }

  public ModifyOrdersByClientId setClientOrderId(String client_order_id) {
    this.client_order_id = client_order_id;
    return this;
  }

  public Double getPrice() {
    return price;
  }

  public ModifyOrdersByClientId setPrice(Double price) {
    this.price = price;
    return this;
  }

  public Double getSize() {
    return size;
  }

  public ModifyOrdersByClientId setSize(Double size) {
    this.size = size;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return RestMethod.POST;
  }

  @Override
  protected String getEndpointPath() {
    requireNonNull(client_order_id);
    return format("/api/orders/by_client_id/{0}/modify", client_order_id);
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
