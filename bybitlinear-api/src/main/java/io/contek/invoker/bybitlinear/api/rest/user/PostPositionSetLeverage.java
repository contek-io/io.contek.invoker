package io.contek.invoker.bybitlinear.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybitlinear.api.rest.common.ResponseWrapper;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.Empty;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybitlinear.api.ApiFactory.RateLimits.ONE_REST_PRIVATE_POSITION_WRITE_REQUEST;
import static io.contek.invoker.bybitlinear.api.rest.user.PostPositionSetLeverage.Response;
import static io.contek.invoker.commons.rest.RestMethod.POST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostPositionSetLeverage extends UserRestRequest<Response> {

  private String symbol;
  private Double buy_leverage;
  private Double sell_leverage;

  PostPositionSetLeverage(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostPositionSetLeverage setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public PostPositionSetLeverage setBuyLeverage(Double buy_leverage) {
    this.buy_leverage = buy_leverage;
    return this;
  }

  public PostPositionSetLeverage setSellLeverage(Double sell_leverage) {
    this.sell_leverage = sell_leverage;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/private/linear/position/set-leverage";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(symbol);
    builder.add("symbol", symbol);

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
  public static final class Response extends ResponseWrapper<Empty> {}
}
