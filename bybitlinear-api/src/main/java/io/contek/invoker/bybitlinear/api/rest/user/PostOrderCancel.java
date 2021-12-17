package io.contek.invoker.bybitlinear.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybitlinear.api.common._Order;
import io.contek.invoker.bybitlinear.api.rest.common.RestResponse;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybitlinear.api.ApiFactory.RateLimits.ONE_REST_PRIVATE_ORDER_WRITE_REQUEST;
import static io.contek.invoker.bybitlinear.api.rest.user.PostOrderCancel.Response;
import static io.contek.invoker.commons.rest.RestMethod.POST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostOrderCancel extends UserRestRequest<Response> {

  private String order_id;
  private String order_link_id;
  private String symbol;

  PostOrderCancel(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostOrderCancel setOrderId(String order_id) {
    this.order_id = order_id;
    return this;
  }

  public PostOrderCancel setOrderLinkId(String order_link_id) {
    this.order_link_id = order_link_id;
    return this;
  }

  public PostOrderCancel setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/v2/private/order/cancel";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (order_id == null && order_link_id == null) {
      throw new IllegalArgumentException();
    }
    if (order_id != null) {
      builder.add("order_id", order_id);
    }
    if (order_link_id != null) {
      builder.add("order_link_id", order_link_id);
    }

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_PRIVATE_ORDER_WRITE_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<_Order> {}
}
