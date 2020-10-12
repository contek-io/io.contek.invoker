package io.contek.invoker.bybit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.common._Order;
import io.contek.invoker.bybit.api.rest.common.RestResponse;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.rest.RestMethod;
import io.contek.invoker.commons.api.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybit.api.ApiFactory.RateLimits.ONE_REST_POST_REQUEST;
import static io.contek.invoker.bybit.api.rest.user.PostOrderCancel.Response;
import static io.contek.invoker.commons.api.rest.RestMethod.GET;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostOrderCancel extends UserRestRequest<Response> {

  public String order_id;
  public String order_link_id;
  public String symbol;

  PostOrderCancel(IActor actor, RestContext context, String symbol) {
    super(actor, context);
    this.symbol = symbol;
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
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/v2/private/order/cancel";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (order_id == null || order_link_id == null) {
      throw new IllegalArgumentException();
    }
    builder.add("order_id", order_id);
    builder.add("order_link_id", order_link_id);

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_REST_POST_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<_Order> {}
}
