package io.contek.invoker.bybit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.rest.common.RestResponse;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybit.api.ApiFactory.RateLimits.ONE_REST_PRIVATE_POSITION_WRITE_REQUEST;
import static io.contek.invoker.bybit.api.rest.user.PostPositionSwitchIsolated.Response;
import static io.contek.invoker.commons.rest.RestMethod.POST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostPositionSwitchIsolated extends UserRestRequest<Response> {

  private String symbol;
  private Boolean is_isolated;
  private Double buy_leverage;
  private Double sell_leverage;

  PostPositionSwitchIsolated(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostPositionSwitchIsolated setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public PostPositionSwitchIsolated setIsIsolated(Boolean is_isolated) {
    this.is_isolated = is_isolated;
    return this;
  }

  public PostPositionSwitchIsolated setBuyLeverage(Double buy_leverage) {
    this.buy_leverage = buy_leverage;
    return this;
  }

  public PostPositionSwitchIsolated setSellLeverage(Double sell_leverage) {
    this.sell_leverage = sell_leverage;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/v2/private/position/switch-isolated";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    requireNonNull(is_isolated);
    builder.add("is_isolated", is_isolated);

    requireNonNull(buy_leverage);
    builder.add("buy_leverage", buy_leverage);

    requireNonNull(sell_leverage);
    builder.add("sell_leverage", sell_leverage);

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_PRIVATE_POSITION_WRITE_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<Double> {}
}
