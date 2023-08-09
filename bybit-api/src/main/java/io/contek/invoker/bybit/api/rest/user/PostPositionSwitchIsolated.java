package io.contek.invoker.bybit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.ApiFactory;
import io.contek.invoker.bybit.api.rest.common.ResponseWrapper;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybit.api.rest.user.PostPositionSwitchIsolated.Response;
import static io.contek.invoker.commons.rest.RestMethod.POST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostPositionSwitchIsolated extends UserRestRequest<Response> {

  private String category;
  private String symbol;
  private Integer tradeMode;
  private Double buyLeverage;
  private Double sellLeverage;

  PostPositionSwitchIsolated(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostPositionSwitchIsolated setCategory(String category) {
    this.category = category;
    return this;
  }

  public PostPositionSwitchIsolated setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public PostPositionSwitchIsolated setTradeMode(Integer tradeMode) {
    this.tradeMode = tradeMode;
    return this;
  }

  public PostPositionSwitchIsolated setBuyLeverage(Double buyLeverage) {
    this.buyLeverage = buyLeverage;
    return this;
  }

  public PostPositionSwitchIsolated setSellLeverage(Double sellLeverage) {
    this.sellLeverage = sellLeverage;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/v5/position/switch-isolated";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(category);
    builder.add("category", category);

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    requireNonNull(tradeMode);
    builder.add("tradeMode", tradeMode);

    requireNonNull(buyLeverage);
    builder.add("buyLeverage", buyLeverage);

    requireNonNull(sellLeverage);
    builder.add("sellLeverage", sellLeverage);

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ApiFactory.RateLimits.ONE_REST_PRIVATE_POSITION_WRITE_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends ResponseWrapper<Object> {}
}
