package io.contek.invoker.binancedelivery.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancedelivery.api.common._InitialLeverageInfo;
import io.contek.invoker.binancedelivery.api.rest.user.PostLeverage.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.contek.invoker.binancedelivery.api.ApiFactory.RateLimits.ONE_REST_REQUEST;
import static io.contek.invoker.commons.rest.RestMethod.POST;

public final class PostLeverage extends UserRestRequest<Response> {

  private String symbol;
  private Integer leverage;

  PostLeverage(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostLeverage setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public PostLeverage setLeverage(Integer leverage) {
    this.leverage = leverage;
    return this;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/dapi/v1/leverage";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    checkNotNull(symbol);
    builder.add("symbol", symbol);

    checkNotNull(leverage);
    builder.add("leverage", leverage);

    builder.add("timestamp", getMillis());

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_REQUEST;
  }

  public static final class Response extends _InitialLeverageInfo {}
}
