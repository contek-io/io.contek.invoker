package io.contek.invoker.bybitlinear.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybitlinear.api.common._WalletFundRecord;
import io.contek.invoker.bybitlinear.api.rest.common.RestResponse;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static io.contek.invoker.bybitlinear.api.ApiFactory.RateLimits.ONE_REST_PRIVATE_FUND_READ_REQUEST;
import static io.contek.invoker.bybitlinear.api.rest.user.GetWalletFundRecords.Response;
import static io.contek.invoker.commons.rest.RestMethod.GET;

@NotThreadSafe
public final class GetWalletFundRecords extends UserRestRequest<Response> {

  private String start_date;
  private String end_date;
  private String currency;
  private String coin;
  private String wallet_fund_type;
  private Integer page;
  private Integer limit;

  GetWalletFundRecords(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetWalletFundRecords setStartDate(String start_date) {
    this.start_date = start_date;
    return this;
  }

  public GetWalletFundRecords setEndDate(String end_date) {
    this.end_date = end_date;
    return this;
  }

  public GetWalletFundRecords setCurrency(String currency) {
    this.currency = currency;
    return this;
  }

  public GetWalletFundRecords setCoin(String coin) {
    this.coin = coin;
    return this;
  }

  public GetWalletFundRecords setWalletFundType(String wallet_fund_type) {
    this.wallet_fund_type = wallet_fund_type;
    return this;
  }

  public GetWalletFundRecords setPage(Integer page) {
    this.page = page;
    return this;
  }

  public GetWalletFundRecords setLimit(Integer limit) {
    this.limit = limit;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/open-api/wallet/fund/records";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (start_date != null) {
      builder.add("start_date", start_date);
    }

    if (end_date != null) {
      builder.add("end_date", end_date);
    }

    if (currency != null) {
      builder.add("currency", currency);
    }

    if (coin != null) {
      builder.add("coin", coin);
    }

    if (wallet_fund_type != null) {
      builder.add("wallet_fund_type", wallet_fund_type);
    }

    if (page != null) {
      builder.add("page", page);
    }

    if (limit != null) {
      builder.add("limit", limit);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_PRIVATE_FUND_READ_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<List<_WalletFundRecord>> {}
}
