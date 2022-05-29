package io.contek.invoker.ftx.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._TriggerOrder;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import static io.contek.invoker.ftx.api.ApiFactory.RateLimits.ONE_REST_REQUEST;
import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

public final class ModifyTriggerOrders extends UserRestRequest<ModifyTriggerOrders.Response> {

  private String order_id;
  private Double triggerPrice;
  private Double orderPrice;
  private Double size;
  private Double trailValue;

  ModifyTriggerOrders(IActor actor, RestContext context) {
    super(actor, context);
  }

  public Double getTrailValue() {
    return trailValue;
  }

  public ModifyTriggerOrders setTrailValue(Double trailValue) {
    this.trailValue = trailValue;
    return this;
  }

  public Double getOrderPrice() {
    return orderPrice;
  }

  public void setOrderPrice(Double orderPrice) {
    this.orderPrice = orderPrice;
  }

  public String getOrderId() {
    return order_id;
  }

  public ModifyTriggerOrders setOrderId(String orderId) {
    this.order_id = orderId;
    return this;
  }

  public Double getTriggerPrice() {
    return triggerPrice;
  }

  public ModifyTriggerOrders setTriggerPrice(Double triggerPrice) {
    this.triggerPrice = triggerPrice;
    return this;
  }

  public Double getSize() {
    return size;
  }

  public ModifyTriggerOrders setSize(Double size) {
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
    return format("/api/conditional_orders/{0}/modify", order_id);
  }

  @Override
  protected RestParams getParams() {
    requireNonNull(size);
    final RestParams.Builder builder = RestParams.newBuilder();
    if (triggerPrice != null) {
      builder.add("triggerPrice", triggerPrice);
    }
    if (trailValue != null) {
      builder.add("trailValue", trailValue);
    }
    builder.add("size", size);

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  public static final class Response extends RestResponse<_TriggerOrder> {}
}
