package io.contek.invoker.hbdminverse.api.rest.market;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.hbdminverse.api.common._ContractInfo;
import io.contek.invoker.hbdminverse.api.rest.common.RestDataResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static io.contek.invoker.hbdminverse.api.ApiFactory.RateLimits.ONE_IP_REST_PUBLIC_MARKET_DATA_REQUEST;

@NotThreadSafe
public final class GetSwapContractInfo extends MarketRestRequest<GetSwapContractInfo.Response> {

  private String contract_code;

  GetSwapContractInfo(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetSwapContractInfo setContractCode(String contract_code) {
    this.contract_code = contract_code;
    return this;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/swap-api/v1/swap_contract_info";
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
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_IP_REST_PUBLIC_MARKET_DATA_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends RestDataResponse<List<_ContractInfo>> {}
}
