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
import static io.contek.invoker.bybitlinear.api.rest.user.PostOrderCreate.Response;
import static io.contek.invoker.commons.rest.RestMethod.POST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostOrderCreate extends UserRestRequest<Response> {

  private String side;
  private String symbol;
  private String order_type;
  private Integer qty;
  private Double price;
  private String time_in_force;
  private Double take_profit;
  private Double stop_loss;
  private Boolean reduce_only;
  private Boolean close_on_trigger;
  private String order_link_id;

  PostOrderCreate(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostOrderCreate setSide(String side) {
    this.side = side;
    return this;
  }

  public PostOrderCreate setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public PostOrderCreate setOrderType(String order_type) {
    this.order_type = order_type;
    return this;
  }

  public PostOrderCreate setQty(Integer qty) {
    this.qty = qty;
    return this;
  }

  public PostOrderCreate setPrice(Double price) {
    this.price = price;
    return this;
  }

  public PostOrderCreate setTimeInForce(String time_in_force) {
    this.time_in_force = time_in_force;
    return this;
  }

  public PostOrderCreate setTakeProfit(Double take_profit) {
    this.take_profit = take_profit;
    return this;
  }

  public PostOrderCreate setStopLoss(Double stop_loss) {
    this.stop_loss = stop_loss;
    return this;
  }

  public PostOrderCreate setReduceOnly(Boolean reduce_only) {
    this.reduce_only = reduce_only;
    return this;
  }

  public PostOrderCreate setCloseOnTrigger(Boolean close_on_trigger) {
    this.close_on_trigger = close_on_trigger;
    return this;
  }

  public PostOrderCreate setOrderLinkId(String order_link_id) {
    this.order_link_id = order_link_id;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/v2/private/order/create";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(side);
    builder.add("side", side);

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    requireNonNull(order_type);
    builder.add("order_type", order_type);

    requireNonNull(qty);
    builder.add("qty", qty);

    requireNonNull(time_in_force);
    builder.add("time_in_force", time_in_force);

    if (price != null) {
      builder.add("price", price);
    }

    if (take_profit != null) {
      builder.add("take_profit", take_profit);
    }

    if (stop_loss != null) {
      builder.add("stop_loss", stop_loss);
    }

    if (reduce_only != null) {
      builder.add("reduce_only", reduce_only);
    }

    if (close_on_trigger != null) {
      builder.add("close_on_trigger", close_on_trigger);
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
  public static final class Response extends RestResponse<_Order> {}
}
