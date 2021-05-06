package io.contek.invoker.hbdminverse.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.hbdminverse.api.common._AvailableLevelRate;
import io.contek.invoker.hbdminverse.api.rest.common.RestDataResponse;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.hbdminverse.api.ApiFactory.RateLimits.ONE_API_KEY_REST_PRIVATE_READ_REQUEST;

@NotThreadSafe
public final class PostSwapCrossAvailableLevelRate
    extends UserRestRequest<PostSwapCrossAvailableLevelRate.Response> {

  private String contract_code;

  PostSwapCrossAvailableLevelRate(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostSwapCrossAvailableLevelRate setContractCode(String contract_code) {
    this.contract_code = contract_code;
    return this;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/swap-api/v1/swap_cross_available_level_rate";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (contract_code != null) {
      builder.add("contract_code", contract_code);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_API_KEY_REST_PRIVATE_READ_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends RestDataResponse<_AvailableLevelRate> {}
}
