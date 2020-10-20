package io.contek.invoker.bitmex.api.rest.user;

import io.contek.invoker.bitmex.api.common._Position;
import io.contek.invoker.bitmex.api.rest.user.PostPositionLeverage.Response;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.rest.RestMethod;
import io.contek.invoker.commons.api.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.contek.invoker.commons.api.rest.RestMethod.POST;

@NotThreadSafe
public final class PostPositionLeverage extends UserRestRequest<Response> {

  private String symbol;
  private Double leverage;

  PostPositionLeverage(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostPositionLeverage setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public PostPositionLeverage setLeverage(Double leverage) {
    this.leverage = leverage;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/position/leverage";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    checkNotNull(symbol);
    builder.add("symbol", symbol);

    checkNotNull(leverage);
    builder.add("leverage", leverage);

    return builder.build();
  }

  @NotThreadSafe
  public static final class Response extends _Position {}
}
