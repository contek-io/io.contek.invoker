package io.contek.invoker.ftx.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._Leverage;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostAccountLeverage extends UserRestRequest<PostAccountLeverage.Response> {

  private Double leverage;

  public PostAccountLeverage(IActor actor, RestContext context) {
    super(actor, context);
  }

  public Double getLeverage() {
    return leverage;
  }

  public PostAccountLeverage setLeverage(Double leverage) {
    this.leverage = leverage;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return RestMethod.POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/account/leverage";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(leverage);
    builder.add("leverage", leverage);

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<_Leverage> {}
}
