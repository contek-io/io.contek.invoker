package io.contek.invoker.ftx.api.rest.otc;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._Payment;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;
import java.util.Objects;

@NotThreadSafe
public class GetDeferCostPayments extends OTCQuotesRestRequest<GetDeferCostPayments.Response> {

  private Integer quoteId;
  private Integer before;
  private String limit;

  public GetDeferCostPayments(IActor actor, RestContext context) {
    super(actor, context);
  }

  public Integer getQuoteId() {
    return quoteId;
  }

  public GetDeferCostPayments setQuoteId(Integer quoteId) {
    this.quoteId = quoteId;
    return this;
  }

  public Integer getBefore() {
    return before;
  }

  public GetDeferCostPayments setBefore(Integer before) {
    this.before = before;
    return this;
  }

  public String getLimit() {
    return limit;
  }

  public GetDeferCostPayments setLimit(String limit) {
    this.limit = limit;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return RestMethod.GET;
  }

  @Override
  protected String getEndpointPathOTC() {
    return "/defer_cost_payments";
  }

  @Override
  protected RestParams getParams() {
    Objects.requireNonNull(limit);

    RestParams.Builder builder = RestParams.newBuilder().add("limit", limit);

    if (before != null) {
      builder.add("before", before);
    }

    if (limit != null) {
      builder.add("limit", limit);
    }

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<List<_Payment>> {}
}
