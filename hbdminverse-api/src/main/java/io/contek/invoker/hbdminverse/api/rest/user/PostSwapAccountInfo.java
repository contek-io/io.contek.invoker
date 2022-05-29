package io.contek.invoker.hbdminverse.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.hbdminverse.api.common._AccountInfo;
import io.contek.invoker.hbdminverse.api.rest.common.RestDataResponse;

import java.util.List;

import static io.contek.invoker.hbdminverse.api.ApiFactory.RateLimits.ONE_API_KEY_REST_PRIVATE_READ_REQUEST;

public final class PostSwapAccountInfo extends UserRestRequest<PostSwapAccountInfo.Response> {

  private String margin_account;

  PostSwapAccountInfo(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostSwapAccountInfo setMarginAccount(String margin_account) {
    this.margin_account = margin_account;
    return this;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/swap-api/v1/swap_account_info";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (margin_account != null) {
      builder.add("margin_account", margin_account);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_API_KEY_REST_PRIVATE_READ_REQUEST;
  }

  public static final class Response extends RestDataResponse<List<_AccountInfo>> {}
}
