package io.contek.invoker.ftx.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._ConditionalOrder;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.commons.rest.RestMethod.POST;
import static io.contek.invoker.ftx.api.ApiFactory.RateLimits.API_KEY_REST_ORDER_RULE;
import static io.contek.invoker.ftx.api.ApiFactory.RateLimits.IP_REST_REQUEST_RULE;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostConditionalOrders extends UserRestRequest<PostConditionalOrders.Response> {

  public static final ImmutableList<TypedPermitRequest> ONE_REST_ORDER_REQUEST =
      ImmutableList.of(IP_REST_REQUEST_RULE.forPermits(1), API_KEY_REST_ORDER_RULE.forPermits(1));

  private String market;
  private String side;
  private Double size;
  private String type;
  private Boolean reduceOnly;
  private Boolean retryUntilFilled;
  private Double triggerPrice;
  private Double orderPrice;
  private Double trailValue;

  PostConditionalOrders(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostConditionalOrders setMarket(String market) {
    this.market = market;
    return this;
  }

  public PostConditionalOrders setSide(String side) {
    this.side = side;
    return this;
  }

  public PostConditionalOrders setSize(Double size) {
    this.size = size;
    return this;
  }

  public PostConditionalOrders setType(String type) {
    this.type = type;
    return this;
  }

  public PostConditionalOrders setReduceOnly(Boolean reduceOnly) {
    this.reduceOnly = reduceOnly;
    return this;
  }

  public PostConditionalOrders setRetryUntilFilled(Boolean retryUntilFilled) {
    this.retryUntilFilled = retryUntilFilled;
    return this;
  }

  public PostConditionalOrders setTriggerPrice(Double triggerPrice) {
    this.triggerPrice = triggerPrice;
    return this;
  }

  public PostConditionalOrders setOrderPrice(Double orderPrice) {
    this.orderPrice = orderPrice;
    return this;
  }

  public PostConditionalOrders setTrailValue(Double trailValue) {
    this.trailValue = trailValue;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
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

    if (reduceOnly != null) {
      builder.add("reduceOnly", reduceOnly);
    }

    if (retryUntilFilled != null) {
      builder.add("retryUntilFilled", retryUntilFilled);
    }

    if (triggerPrice != null) {
      builder.add("triggerPrice", triggerPrice);
    }

    if (orderPrice != null) {
      builder.add("orderPrice", orderPrice);
    }

    if (trailValue != null) {
      builder.add("trailValue", trailValue);
    }

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_ORDER_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<_ConditionalOrder> {}
}
