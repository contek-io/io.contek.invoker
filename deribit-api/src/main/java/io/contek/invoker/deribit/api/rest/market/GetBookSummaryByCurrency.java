package io.contek.invoker.deribit.api.rest.market;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.deribit.api.common._BookSummary;
import io.contek.invoker.deribit.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static io.contek.invoker.deribit.api.ApiFactory.RateLimits.ONE_NON_MATCHING_ENGINE_REQUEST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetBookSummaryByCurrency
    extends MarketRestRequest<GetBookSummaryByCurrency.Response> {

  private String currency;
  private String kind;

  GetBookSummaryByCurrency(IActor actor, RestContext context) {
    super(actor, context);
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v2/public/get_book_summary_by_currency";
  }

  public GetBookSummaryByCurrency setCurrency(String currency) {
    this.currency = currency;
    return this;
  }

  public GetBookSummaryByCurrency setKind(String kind) {
    this.kind = kind;
    return this;
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(currency);
    builder.add("currency", currency);
    if (kind != null) {
      builder.add("kind", kind);
    }

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_NON_MATCHING_ENGINE_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<List<_BookSummary>> {}
}
