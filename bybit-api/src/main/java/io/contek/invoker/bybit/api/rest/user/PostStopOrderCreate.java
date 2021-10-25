package io.contek.invoker.bybit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.common._StopOrder;
import io.contek.invoker.bybit.api.rest.common.RestResponse;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybit.api.ApiFactory.RateLimits.ONE_REST_PRIVATE_ORDER_WRITE_REQUEST;
import static io.contek.invoker.bybit.api.rest.user.PostStopOrderCreate.Response;
import static io.contek.invoker.commons.rest.RestMethod.POST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostStopOrderCreate extends UserRestRequest<Response> {

  private String side;
  private String symbol;
  private String order_type;
  private Integer qty;
  private Double price;
  private Double base_price;
  private Double stop_px;
  private String time_in_force;
  private String trigger_by;
  private Boolean close_on_trigger;
  private String order_link_id;
  private Double take_profit;
  private Double stop_loss;
  private String tp_trigger_by;
  private String sl_trigger_by;

  PostStopOrderCreate(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostStopOrderCreate setSide(String side) {
    this.side = side;
    return this;
  }

  public PostStopOrderCreate setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public PostStopOrderCreate setOrderType(String order_type) {
    this.order_type = order_type;
    return this;
  }

  public PostStopOrderCreate setQty(Integer qty) {
    this.qty = qty;
    return this;
  }

  public PostStopOrderCreate setPrice(Double price) {
    this.price = price;
    return this;
  }

  public PostStopOrderCreate setBasePrice(Double base_price) {
    this.base_price = base_price;
    return this;
  }

  public PostStopOrderCreate setStopPx(Double stop_px) {
    this.stop_px = stop_px;
    return this;
  }

  public PostStopOrderCreate setTimeInForce(String time_in_force) {
    this.time_in_force = time_in_force;
    return this;
  }

  public PostStopOrderCreate setTriggerBy(String trigger_by) {
    this.trigger_by = trigger_by;
    return this;
  }

  public PostStopOrderCreate setCloseOnTrigger(Boolean close_on_trigger) {
    this.close_on_trigger = close_on_trigger;
    return this;
  }

  public PostStopOrderCreate setOrderLinkId(String order_link_id) {
    this.order_link_id = order_link_id;
    return this;
  }

  public PostStopOrderCreate setTakeProfit(Double take_profit) {
    this.take_profit = take_profit;
    return this;
  }

  public PostStopOrderCreate setStopLoss(Double stop_loss) {
    this.stop_loss = stop_loss;
    return this;
  }

  public PostStopOrderCreate setTpTriggerBy(String tp_trigger_by) {
    this.tp_trigger_by = tp_trigger_by;
    return this;
  }

  public PostStopOrderCreate setSlTriggerBy(String sl_trigger_by) {
    this.sl_trigger_by = sl_trigger_by;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/v2/private/stop-order/create";
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

    requireNonNull(base_price);
    builder.add("base_price", base_price);

    requireNonNull(stop_px);
    builder.add("stop_px", stop_px);

    requireNonNull(time_in_force);
    builder.add("time_in_force", time_in_force);

    if (price != null) {
      builder.add("price", price);
    }

    if (trigger_by != null) {
      builder.add("trigger_by", trigger_by);
    }

    if (close_on_trigger != null) {
      builder.add("close_on_trigger", close_on_trigger);
    }

    if (order_link_id != null) {
      builder.add("order_link_id", order_link_id);
    }

    if (take_profit != null) {
      builder.add("take_profit", take_profit);
    }

    if (stop_loss != null) {
      builder.add("stop_loss", stop_loss);
    }

    if (tp_trigger_by != null) {
      builder.add("tp_trigger_by", tp_trigger_by);
    }

    if (sl_trigger_by != null) {
      builder.add("sl_trigger_by", sl_trigger_by);
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
  public static final class Response extends RestResponse<_StopOrder> {}
}
