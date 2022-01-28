package io.contek.invoker.ftx.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.commons.rest.RestMethod.DELETE;
import static io.contek.invoker.ftx.api.ApiFactory.RateLimits.ONE_REST_REQUEST;

@NotThreadSafe
public final class DeleteAllOrders extends UserRestRequest<DeleteAllOrders.Response> {

  private String market;
  private String side;
  private Boolean conditionalOrdersOnly;
  private Boolean limitOrdersOnly;

  DeleteAllOrders(IActor actor, RestContext context) {
    super(actor, context);
  }

  public DeleteAllOrders setMarket(String market) {
    this.market = market;
    return this;
  }

  public DeleteAllOrders setSide(String side) {
    this.side = side;
    return this;
  }

  public DeleteAllOrders setConditionalOrdersOnly(Boolean conditionalOrdersOnly) {
    this.conditionalOrdersOnly = conditionalOrdersOnly;
    return this;
  }

  public DeleteAllOrders setLimitOrdersOnly(Boolean limitOrdersOnly) {
    this.limitOrdersOnly = limitOrdersOnly;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return DELETE;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/orders";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (market != null) {
      builder.add("market", market);
    }

    if (side != null) {
      builder.add("side", side);
    }

    if (conditionalOrdersOnly != null) {
      builder.add("conditionalOrdersOnly", conditionalOrdersOnly);
    }

    if (limitOrdersOnly != null) {
      builder.add("limitOrdersOnly", limitOrdersOnly);
    }

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<String> {}
}
