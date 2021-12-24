package io.contek.invoker.bybit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.rest.common.RestResponse;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybit.api.ApiFactory.RateLimits.ONE_REST_PRIVATE_POSITION_WRITE_REQUEST;
import static io.contek.invoker.commons.rest.RestMethod.POST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostPositionLeverageSave
    extends UserRestRequest<PostPositionLeverageSave.Response> {

  private String symbol;
  private Double leverage;
  private Boolean leverage_only;

  PostPositionLeverageSave(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostPositionLeverageSave setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public PostPositionLeverageSave setLeverage(Double leverage) {
    this.leverage = leverage;
    return this;
  }

  public PostPositionLeverageSave setLeverageOnly(@Nullable Boolean leverage_only) {
    this.leverage_only = leverage_only;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/v2/private/position/leverage/save";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    requireNonNull(leverage);
    builder.add("leverage", leverage);

    if (leverage_only != null) {
      builder.add("leverage_only", leverage_only);
    }

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
