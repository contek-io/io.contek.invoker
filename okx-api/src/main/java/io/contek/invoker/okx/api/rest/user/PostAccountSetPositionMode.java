package io.contek.invoker.okx.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.okx.api.common._PositionMode;
import io.contek.invoker.okx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.commons.rest.RestMethod.POST;
import static io.contek.invoker.okx.api.ApiFactory.RateLimits.ONE_REST_REQUEST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostAccountSetPositionMode
    extends UserRestRequest<PostAccountSetPositionMode.Response> {

  private String posMode;

  PostAccountSetPositionMode(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostAccountSetPositionMode setPosMode(String posMode) {
    this.posMode = posMode;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v5/account/set-position-mode";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(posMode);
    builder.add("posMode", posMode);

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<_PositionMode> {}
}
