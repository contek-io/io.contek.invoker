package io.contek.invoker.ftx.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import static io.contek.invoker.ftx.api.ApiFactory.RateLimits.ONE_REST_REQUEST;
import static java.util.Objects.requireNonNull;

public final class PostLendingOffer extends UserRestRequest<PostLendingOffer.Response> {

  public String coin;
  public Double rate;
  public Double size;

  PostLendingOffer(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostLendingOffer setCoin(String coin) {
    this.coin = coin;
    return this;
  }

  public PostLendingOffer setRate(Double rate) {
    this.rate = rate;
    return this;
  }

  public PostLendingOffer setSize(Double size) {
    this.size = size;
    return this;
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_REQUEST;
  }

  @Override
  protected Class<PostLendingOffer.Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected RestMethod getMethod() {
    return RestMethod.POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/spot_margin/offers";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(coin);
    builder.add("coin", coin);

    requireNonNull(rate);
    builder.add("rate", rate);

    requireNonNull(size);
    builder.add("size", size);

    return builder.build();
  }

  public static final class Response extends RestResponse<String> {}
}
