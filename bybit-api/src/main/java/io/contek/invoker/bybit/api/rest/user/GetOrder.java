package io.contek.invoker.bybit.api.rest.user;

import static io.contek.invoker.bybit.api.ApiFactory.RateLimits.ONE_REST_GET_REQUEST;
import static io.contek.invoker.bybit.api.rest.user.GetOrder.Response;
import static io.contek.invoker.commons.api.rest.RestMethod.GET;
import static java.util.Objects.requireNonNull;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.common._Order;
import io.contek.invoker.bybit.api.rest.common.RestResponse;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.rest.RestMethod;
import io.contek.invoker.commons.api.rest.RestParams;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class GetOrder extends UserRestRequest<Response> {

  private String order_id;
  private String order_link_id;
  private String symbol;

  GetOrder(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetOrder setOrderId(String order_id) {
    this.order_id = order_id;
    return this;
  }

  public GetOrder setOrderLinkId(String order_link_id) {
    this.order_link_id = order_link_id;
    return this;
  }

  public GetOrder setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/v2/private/order";
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
    return ONE_REST_GET_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<_Order> {}
}
