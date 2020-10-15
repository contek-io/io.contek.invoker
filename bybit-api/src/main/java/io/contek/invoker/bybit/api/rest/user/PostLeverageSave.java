package io.contek.invoker.bybit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.rest.common.RestResponse;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.rest.RestMethod;
import io.contek.invoker.commons.api.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybit.api.ApiFactory.RateLimits.ONE_REST_PRIVATE_POSITION_WRITE_REQUEST;
import static io.contek.invoker.bybit.api.rest.user.PostLeverageSave.Response;
import static io.contek.invoker.commons.api.rest.RestMethod.POST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostLeverageSave extends UserRestRequest<Response> {

  private String symbol;
  private Double leverage;

  PostLeverageSave(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostLeverageSave setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public PostLeverageSave setLeverage(Double leverage) {
    this.leverage = leverage;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/user/leverage/save";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    requireNonNull(leverage);
    builder.add("leverage", leverage);

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_REST_PRIVATE_POSITION_WRITE_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<Double> {}
}
