package io.contek.invoker.okx.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitRule;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.okx.api.common._PlaceOrderAck;
import io.contek.invoker.okx.api.rest.common.RestResponse;

import java.time.Duration;

import static io.contek.invoker.commons.actor.ratelimit.LimitType.API_KEY;
import static io.contek.invoker.commons.rest.RestMethod.POST;
import static java.util.Objects.requireNonNull;

public final class PostTradeOrder extends UserRestRequest<PostTradeOrder.Response> {

  public static final RateLimitRule RATE_LIMIT_RULE =
      RateLimitRule.newBuilder()
          .setName("api_key_rest_post_trade_order")
          .setType(API_KEY)
          .setMaxPermits(60)
          .setResetPeriod(Duration.ofSeconds(2))
          .build();

  private static final ImmutableList<TypedPermitRequest> REQUIRED_QUOTA =
      ImmutableList.of(RATE_LIMIT_RULE.forPermits(1));

  private String instId;
  private String tdMode;
  private String ccy;
  private String clOrdId;
  private String tag;
  private String side;
  private String posSide;
  private String ordType;
  private String sz;
  private String px;
  private Boolean reduceOnly;
  private String tgtCcy;

  PostTradeOrder(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostTradeOrder setInstId(String instId) {
    this.instId = instId;
    return this;
  }

  public PostTradeOrder setTdMode(String tdMode) {
    this.tdMode = tdMode;
    return this;
  }

  public PostTradeOrder setCcy(String ccy) {
    this.ccy = ccy;
    return this;
  }

  public PostTradeOrder setClOrdId(String clOrdId) {
    this.clOrdId = clOrdId;
    return this;
  }

  public PostTradeOrder setTag(String tag) {
    this.tag = tag;
    return this;
  }

  public PostTradeOrder setSide(String side) {
    this.side = side;
    return this;
  }

  public PostTradeOrder setPosSide(String posSide) {
    this.posSide = posSide;
    return this;
  }

  public PostTradeOrder setOrdType(String ordType) {
    this.ordType = ordType;
    return this;
  }

  public PostTradeOrder setSz(String sz) {
    this.sz = sz;
    return this;
  }

  public PostTradeOrder setPx(String px) {
    this.px = px;
    return this;
  }

  public PostTradeOrder setReduceOnly(Boolean reduceOnly) {
    this.reduceOnly = reduceOnly;
    return this;
  }

  public PostTradeOrder setTgtCcy(String tgtCcy) {
    this.tgtCcy = tgtCcy;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v5/trade/order";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(instId);
    builder.add("instId", instId);

    requireNonNull(tdMode);
    builder.add("tdMode", tdMode);

    requireNonNull(side);
    builder.add("side", side);

    requireNonNull(ordType);
    builder.add("ordType", ordType);

    requireNonNull(sz);
    builder.add("sz", sz);

    if (ccy != null) {
      builder.add("ccy", ccy);
    }

    if (clOrdId != null) {
      builder.add("clOrdId", clOrdId);
    }

    if (tag != null) {
      builder.add("tag", tag);
    }

    if (posSide != null) {
      builder.add("posSide", posSide);
    }

    if (px != null) {
      builder.add("px", px);
    }

    if (reduceOnly != null) {
      builder.add("reduceOnly", reduceOnly);
    }

    if (tgtCcy != null) {
      builder.add("tgtCcy", tgtCcy);
    }

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return REQUIRED_QUOTA;
  }

  public static final class Response extends RestResponse<_PlaceOrderAck> {}
}
