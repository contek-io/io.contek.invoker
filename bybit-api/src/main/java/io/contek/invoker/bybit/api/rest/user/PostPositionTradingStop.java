package io.contek.invoker.bybit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.common._Position;
import io.contek.invoker.bybit.api.rest.common.RestResponse;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybit.api.ApiFactory.RateLimits.ONE_REST_PRIVATE_POSITION_READ_REQUEST;
import static io.contek.invoker.bybit.api.rest.user.PostPositionTradingStop.Response;
import static io.contek.invoker.commons.rest.RestMethod.POST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostPositionTradingStop extends UserRestRequest<Response> {

  private String symbol;
  private Double take_profit;
  private Double stop_loss;
  private Double trailing_stop;
  private String tp_trigger_by;
  private String sl_trigger_by;
  private Double new_trailing_active;
  private Integer sl_size;
  private Integer tp_size;

  PostPositionTradingStop(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostPositionTradingStop setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public PostPositionTradingStop setTakeProfit(Double take_profit) {
    this.take_profit = take_profit;
    return this;
  }

  public PostPositionTradingStop setStopLoss(Double stop_loss) {
    this.stop_loss = stop_loss;
    return this;
  }

  public PostPositionTradingStop setTrailingStop(Double trailing_stop) {
    this.trailing_stop = trailing_stop;
    return this;
  }

  public PostPositionTradingStop setTpTriggerBy(String tp_trigger_by) {
    this.tp_trigger_by = tp_trigger_by;
    return this;
  }

  public PostPositionTradingStop setSlTriggerBy(String sl_trigger_by) {
    this.sl_trigger_by = sl_trigger_by;
    return this;
  }

  public PostPositionTradingStop setNewTrailingActive(Double new_trailing_active) {
    this.new_trailing_active = new_trailing_active;
    return this;
  }

  public PostPositionTradingStop setSlSize(Integer sl_size) {
    this.sl_size = sl_size;
    return this;
  }

  public PostPositionTradingStop setTpSize(Integer tp_size) {
    this.tp_size = tp_size;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/v2/private/position/trading-stop";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    if (take_profit != null) {
      builder.add("take_profit", take_profit);
    }

    if (stop_loss != null) {
      builder.add("stop_loss", stop_loss);
    }

    if (trailing_stop != null) {
      builder.add("trailing_stop", trailing_stop);
    }

    if (tp_trigger_by != null) {
      builder.add("tp_trigger_by", tp_trigger_by);
    }

    if (sl_trigger_by != null) {
      builder.add("sl_trigger_by", sl_trigger_by);
    }

    if (new_trailing_active != null) {
      builder.add("new_trailing_active", new_trailing_active);
    }

    if (sl_size != null) {
      builder.add("sl_size", sl_size);
    }

    if (tp_size != null) {
      builder.add("tp_size", tp_size);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_PRIVATE_POSITION_READ_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<_Position> {}
}
