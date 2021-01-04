package io.contek.invoker.bitmex.api.rest.user;

import io.contek.invoker.bitmex.api.common._Position;
import io.contek.invoker.bitmex.api.rest.user.PostPositionIsolate.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.contek.invoker.commons.rest.RestMethod.POST;

@NotThreadSafe
public final class PostPositionIsolate extends UserRestRequest<Response> {

  private String symbol;
  private Boolean enabled;

  PostPositionIsolate(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostPositionIsolate setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public PostPositionIsolate setEnabled(Boolean enabled) {
    this.enabled = enabled;
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
    return "/api/v1/position/isolate";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    checkNotNull(symbol);
    builder.add("symbol", symbol);

    if (enabled != null) {
      builder.add("enabled", enabled);
    }

    return builder.build();
  }

  @NotThreadSafe
  public static final class Response extends _Position {}
}
