package io.contek.invoker.bybit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.ApiFactory;
import io.contek.invoker.bybit.api.common._WalletBalance;
import io.contek.invoker.bybit.api.rest.common.ListResult;
import io.contek.invoker.bybit.api.rest.common.ResponseWrapper;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.Objects;

import static io.contek.invoker.bybit.api.rest.user.GetWalletBalance.Response;
import static io.contek.invoker.commons.rest.RestMethod.GET;

@NotThreadSafe
public final class GetWalletBalance extends UserRestRequest<Response> {

  private String accountType;
  private String coin;

  GetWalletBalance(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetWalletBalance setAccountType(String accountType) {
    this.accountType = accountType;
    return this;
  }

  public GetWalletBalance setCoin(@Nullable String coin) {
    this.coin = coin;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected String getEndpointPath() {
    return "/v5/account/wallet-balance";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    Objects.requireNonNull(accountType);
    builder.add("accountType", accountType);

    if (coin != null) {
      builder.add("coin", coin);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ApiFactory.RateLimits.ONE_REST_PRIVATE_POSITION_READ_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends ResponseWrapper<Result> {}

  @NotThreadSafe
  public static final class Result extends ListResult<_WalletBalance> {}
}
