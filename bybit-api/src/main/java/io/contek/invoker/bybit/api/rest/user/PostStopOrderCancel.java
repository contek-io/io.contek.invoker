package io.contek.invoker.bybit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.common._StopOrderCancel;
import io.contek.invoker.bybit.api.rest.common.RestResponse;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybit.api.ApiFactory.RateLimits.ONE_REST_PRIVATE_ORDER_WRITE_REQUEST;
import static io.contek.invoker.bybit.api.rest.user.PostStopOrderCancel.Response;
import static io.contek.invoker.commons.rest.RestMethod.POST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostStopOrderCancel extends UserRestRequest<Response> {

  private String symbol;
  private String stop_order_id;
  private String order_link_id;

  PostStopOrderCancel(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostStopOrderCancel setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public PostStopOrderCancel setStopOrderId(String stop_order_id) {
    this.stop_order_id = stop_order_id;
    return this;
  }

  public PostStopOrderCancel setOrderLinkId(String order_link_id) {
    this.order_link_id = order_link_id;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/v2/private/stop-order/cancel";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    if (stop_order_id == null && order_link_id == null) {
      throw new IllegalArgumentException();
    }
    if (stop_order_id != null) {
      builder.add("stop_order_id", stop_order_id);
    }
    if (order_link_id != null) {
      builder.add("order_link_id", order_link_id);
    }

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
  public static final class Response extends RestResponse<_StopOrderCancel> {}
}
