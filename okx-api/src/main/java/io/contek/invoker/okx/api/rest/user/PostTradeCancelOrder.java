package io.contek.invoker.okx.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.okx.api.common._CancelOrderAck;
import io.contek.invoker.okx.api.rest.common.RestResponse;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.commons.rest.RestMethod.POST;
import static io.contek.invoker.okx.api.ApiFactory.RateLimits.ONE_REST_REQUEST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostTradeCancelOrder extends UserRestRequest<PostTradeCancelOrder.Response> {

  private String instId;
  private String ordId;
  private String clOrdId;

  PostTradeCancelOrder(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostTradeCancelOrder setInstId(String instId) {
    this.instId = instId;
    return this;
  }

  public PostTradeCancelOrder setOrdId(@Nullable String ordId) {
    this.ordId = ordId;
    return this;
  }

  public PostTradeCancelOrder setClOrdId(@Nullable String clOrdId) {
    this.clOrdId = clOrdId;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v5/trade/cancel-order";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(instId);
    builder.add("instId", instId);

    if (ordId != null) {
      builder.add("ordId", ordId);
    } else {
      requireNonNull(clOrdId);
      builder.add("clOrdId", clOrdId);
    }

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
  public static final class Response extends RestResponse<_CancelOrderAck> {}
}
