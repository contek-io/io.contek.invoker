package io.contek.invoker.bybitlinear.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybitlinear.api.rest.common.ResponseWrapper;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.Empty;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybitlinear.api.ApiFactory.RateLimits.ONE_REST_PRIVATE_POSITION_WRITE_REQUEST;
import static io.contek.invoker.bybitlinear.api.rest.user.PostPositionSwitchMode.Response;
import static io.contek.invoker.commons.rest.RestMethod.POST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostPositionSwitchMode extends UserRestRequest<Response> {

  private String symbol;
  private String coin;
  private String mode;

  PostPositionSwitchMode(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostPositionSwitchMode setSymbol(@Nullable String symbol) {
    this.symbol = symbol;
    return this;
  }

  public PostPositionSwitchMode setCoin(@Nullable String coin) {
    this.coin = coin;
    return this;
  }

  public PostPositionSwitchMode setMode(String mode) {
    this.mode = mode;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/private/linear/position/switch-mode";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (symbol != null) {
      builder.add("symbol", symbol);
    } else if (coin != null) {
      builder.add("coin", coin);
    } else {
      throw new IllegalArgumentException();
    }

    requireNonNull(mode);
    builder.add("mode", mode);

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
