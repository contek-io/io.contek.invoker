package io.contek.invoker.bybit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.ApiFactory;
import io.contek.invoker.bybit.api.rest.common.ResponseWrapper;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybit.api.rest.user.PostPositionSwitchMode.Response;
import static io.contek.invoker.commons.rest.RestMethod.POST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostPositionSwitchMode extends UserRestRequest<Response> {

  private String category;
  private String symbol;
  private String coin;
  private Integer mode;

  PostPositionSwitchMode(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostPositionSwitchMode setCategory(String category) {
    this.category = category;
    return this;
  }

  public PostPositionSwitchMode setSymbol(@Nullable String symbol) {
    this.symbol = symbol;
    return this;
  }

  public PostPositionSwitchMode setCoin(@Nullable String coin) {
    this.coin = coin;
    return this;
  }

  public PostPositionSwitchMode setMode(Integer mode) {
    this.mode = mode;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/v5/position/switch-mode";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(category);
    builder.add("category", category);

    requireNonNull(mode);
    builder.add("mode", mode);

    if (symbol != null) {
      builder.add("symbol", symbol);
    } else if (coin != null) {
      builder.add("coin", coin);
    } else {
      throw new IllegalArgumentException();
    }

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
