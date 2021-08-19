package io.contek.invoker.ftx.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._TriggerOrder;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostTriggerOrder extends UserRestRequest<PostTriggerOrder.Response> {

  private String market;
  private String side;
  private Double size;
  private String type;
  private boolean reduceOnly;
  private boolean retryUntilFilled;
  private Double triggerPrice;
  private Double orderPrice;

  PostTriggerOrder(IActor actor, RestContext context) {
    super(actor, context);
  }

  public String getMarket() {
    return market;
  }

  public PostTriggerOrder setMarket(String market) {
    this.market = market;
    return this;
  }

  public String getSide() {
    return side;
  }

  public PostTriggerOrder setSide(String side) {
    this.side = side;
    return this;
  }

  public Double getSize() {
    return size;
  }

  public PostTriggerOrder setSize(Double size) {
    this.size = size;
    return this;
  }

  public String getType() {
    return type;
  }

  public PostTriggerOrder setType(String type) {
    this.type = type;
    return this;
  }

  public boolean isReduceOnly() {
    return reduceOnly;
  }

  public PostTriggerOrder setReduceOnly(boolean reduceOnly) {
    this.reduceOnly = reduceOnly;
    return this;
  }

  public boolean isRetryUntilFilled() {
    return retryUntilFilled;
  }

  public PostTriggerOrder setRetryUntilFilled(boolean retryUntilFilled) {
    this.retryUntilFilled = retryUntilFilled;
    return this;
  }

  public Double getTriggerPrice() {
    return triggerPrice;
  }

  public PostTriggerOrder setTriggerPrice(Double triggerPrice) {
    this.triggerPrice = triggerPrice;
    return this;
  }

  public Double getOrderPrice() {
    return orderPrice;
  }

  public PostTriggerOrder setOrderPrice(Double orderPrice) {
    this.orderPrice = orderPrice;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return RestMethod.POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/conditional_orders";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(market);
    builder.add("market", market);

    requireNonNull(side);
    builder.add("side", side);

    requireNonNull(size);
    builder.add("size", size);

    requireNonNull(type);
    builder.add("type", type);

    builder.add("reduceOnly", reduceOnly);
    builder.add("retryUntilFilled", retryUntilFilled);

    if (triggerPrice != null) {
      builder.add("triggerPrice", triggerPrice);
    }

    if (orderPrice != null) {
      builder.add("orderPrice", orderPrice);
    }

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<_TriggerOrder> {}
}
