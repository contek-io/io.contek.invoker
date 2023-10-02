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

import static io.contek.invoker.commons.rest.RestMethod.POST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostPositionSetLeverage
    extends UserRestRequest<PostPositionSetLeverage.Response> {

  private String category;
  private String symbol;
  private Double buyLeverage;
  private Double sellLeverage;

  PostPositionSetLeverage(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostPositionSetLeverage setCategory(String category) {
    this.category = category;
    return this;
  }

  public PostPositionSetLeverage setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public PostPositionSetLeverage setBuyLeverage(Double buyLeverage) {
    this.buyLeverage = buyLeverage;
    return this;
  }

  public PostPositionSetLeverage setSellLeverage(Double sellLeverage) {
    this.sellLeverage = sellLeverage;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/v5/position/set-leverage";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(category);
    builder.add("category", category);

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    requireNonNull(buyLeverage);
    builder.add("buyLeverage", buyLeverage);

    requireNonNull(sellLeverage);
    builder.add("sellLeverage", sellLeverage);

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ApiFactory.RateLimits.ONE_REST_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends ResponseWrapper<Object> {}
}
