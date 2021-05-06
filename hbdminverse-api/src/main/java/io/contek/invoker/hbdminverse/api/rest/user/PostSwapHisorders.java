package io.contek.invoker.hbdminverse.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.hbdminverse.api.common._OrderInfo;
import io.contek.invoker.hbdminverse.api.rest.common.PaginatedData;
import io.contek.invoker.hbdminverse.api.rest.common.RestDataResponse;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static io.contek.invoker.hbdminverse.api.ApiFactory.RateLimits.ONE_API_KEY_REST_PRIVATE_WRITE_REQUEST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostSwapHisorders extends UserRestRequest<PostSwapHisorders.Response> {

  private String contract_code;
  private Integer trade_type;
  private Integer type;
  private String status;
  private Integer create_date;
  private Integer page_index;
  private Integer page_size;
  private String sort_by;

  PostSwapHisorders(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostSwapHisorders setContractCode(String contract_code) {
    this.contract_code = contract_code;
    return this;
  }

  public PostSwapHisorders setTradeType(Integer trade_type) {
    this.trade_type = trade_type;
    return this;
  }

  public PostSwapHisorders setType(Integer type) {
    this.type = type;
    return this;
  }

  public PostSwapHisorders setStatus(String status) {
    this.status = status;
    return this;
  }

  public PostSwapHisorders setCreateDate(Integer create_date) {
    this.create_date = create_date;
    return this;
  }

  public PostSwapHisorders setPageIndex(@Nullable Integer page_index) {
    this.page_index = page_index;
    return this;
  }

  public PostSwapHisorders setPageSize(@Nullable Integer page_size) {
    this.page_size = page_size;
    return this;
  }

  public PostSwapHisorders setSortBy(@Nullable String sort_by) {
    this.sort_by = sort_by;
    return this;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/swap-api/v1/swap_hisorders";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(contract_code);
    builder.add("contract_code", contract_code);

    requireNonNull(trade_type);
    builder.add("trade_type", trade_type);

    requireNonNull(type);
    builder.add("type", type);

    requireNonNull(status);
    builder.add("status", status);

    requireNonNull(create_date);
    builder.add("create_date", create_date);

    if (page_index != null) {
      builder.add("page_index", page_index);
    }

    if (page_size != null) {
      builder.add("page_size", page_size);
    }

    if (sort_by != null) {
      builder.add("sort_by", sort_by);
    }

    if (trade_type != null) {
      builder.add("trade_type", trade_type);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_API_KEY_REST_PRIVATE_WRITE_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends RestDataResponse<Data> {}

  @NotThreadSafe
  public static final class Data extends PaginatedData {

    public List<_OrderInfo> orders;
  }
}
