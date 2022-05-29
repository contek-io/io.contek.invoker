package io.contek.invoker.hbdmlinear.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.hbdmlinear.api.common._OrderInfo;
import io.contek.invoker.hbdmlinear.api.rest.common.RestDataResponse;

import java.util.List;

import static io.contek.invoker.hbdmlinear.api.ApiFactory.RateLimits.ONE_API_KEY_REST_PRIVATE_WRITE_REQUEST;
import static java.util.Objects.requireNonNull;

public final class PostSwapCrossOrderInfo extends UserRestRequest<PostSwapCrossOrderInfo.Response> {

  private String order_id;
  private String client_order_id;
  private String contract_code;

  PostSwapCrossOrderInfo(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostSwapCrossOrderInfo setOrderId(String order_id) {
    this.order_id = order_id;
    return this;
  }

  public PostSwapCrossOrderInfo setClientOrderId(String client_order_id) {
    this.client_order_id = client_order_id;
    return this;
  }

  public PostSwapCrossOrderInfo setContractCode(String contract_code) {
    this.contract_code = contract_code;
    return this;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/linear-swap-api/v1/swap_cross_order_info";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(contract_code);
    builder.add("contract_code", contract_code);

    if (order_id != null) {
      builder.add("order_id", order_id);
    }

    if (client_order_id != null) {
      builder.add("client_order_id", client_order_id);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_API_KEY_REST_PRIVATE_WRITE_REQUEST;
  }

  public static final class Response extends RestDataResponse<List<_OrderInfo>> {}
}
