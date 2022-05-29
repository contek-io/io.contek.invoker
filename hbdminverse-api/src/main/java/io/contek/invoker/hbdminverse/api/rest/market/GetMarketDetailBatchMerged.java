package io.contek.invoker.hbdminverse.api.rest.market;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.hbdminverse.api.common._MarketDetail;
import io.contek.invoker.hbdminverse.api.rest.common.RestTicksResponse;

import static io.contek.invoker.hbdminverse.api.ApiFactory.RateLimits.ONE_IP_REST_PUBLIC_MARKET_DATA_REQUEST;

public final class GetMarketDetailBatchMerged
    extends MarketRestRequest<GetMarketDetailBatchMerged.Response> {

  private String contract_code;

  GetMarketDetailBatchMerged(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetMarketDetailBatchMerged setContractCode(String contract_code) {
    this.contract_code = contract_code;
    return this;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/swap-ex/market/detail/batch_merged";
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

  public static final class Response extends RestTicksResponse<_MarketDetail> {}
}
