package io.contek.invoker.bybit.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bybit.api.ApiFactory;
import io.contek.invoker.bybit.api.common._OrderRef;
import io.contek.invoker.bybit.api.rest.common.ResponseWrapper;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.bybit.api.rest.user.PostOrderCreate.Response;
import static io.contek.invoker.commons.rest.RestMethod.POST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostOrderCreate extends UserRestRequest<Response> {

  private String category;
  private String symbol;
  private Integer isLeverage;
  private String side;
  private String orderType;
  private String qty;
  private String price;
  private String triggerDirection;
  private String orderFilter;
  private String triggerPrice;
  private String triggerBy;
  private String orderIv;
  private String timeInForce;
  private String positionIdx;
  private String orderLinkId;
  private String takeProfit;
  private String stopLoss;
  private String tpTriggerBy;
  private String slTriggerBy;
  private String reduceOnly;
  private String closeOnTrigger;
  private String smpType;
  private Boolean mmp;
  private String tpslMode;
  private String tpLimitPrice;
  private String slLimitPrice;
  private String tpOrderType;
  private String slOrderType;

  PostOrderCreate(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostOrderCreate setCategory(String category) {
    this.category = category;
    return this;
  }

  public PostOrderCreate setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public PostOrderCreate setIsLeverage(@Nullable Integer isLeverage) {
    this.isLeverage = isLeverage;
    return this;
  }

  public PostOrderCreate setSide(String side) {
    this.side = side;
    return this;
  }

  public PostOrderCreate setOrderType(String orderType) {
    this.orderType = orderType;
    return this;
  }

  public PostOrderCreate setQty(String qty) {
    this.qty = qty;
    return this;
  }

  public PostOrderCreate setPrice(@Nullable String price) {
    this.price = price;
    return this;
  }

  public PostOrderCreate setTriggerDirection(@Nullable String triggerDirection) {
    this.triggerDirection = triggerDirection;
    return this;
  }

  public PostOrderCreate setOrderFilter(@Nullable String orderFilter) {
    this.orderFilter = orderFilter;
    return this;
  }

  public PostOrderCreate setTriggerPrice(@Nullable String triggerPrice) {
    this.triggerPrice = triggerPrice;
    return this;
  }

  public PostOrderCreate setTriggerBy(@Nullable String triggerBy) {
    this.triggerBy = triggerBy;
    return this;
  }

  public PostOrderCreate setOrderIv(@Nullable String orderIv) {
    this.orderIv = orderIv;
    return this;
  }

  public PostOrderCreate setTimeInForce(@Nullable String timeInForce) {
    this.timeInForce = timeInForce;
    return this;
  }

  public PostOrderCreate setPositionIdx(@Nullable String positionIdx) {
    this.positionIdx = positionIdx;
    return this;
  }

  public PostOrderCreate setOrderLinkId(@Nullable String orderLinkId) {
    this.orderLinkId = orderLinkId;
    return this;
  }

  public PostOrderCreate setTakeProfit(@Nullable String takeProfit) {
    this.takeProfit = takeProfit;
    return this;
  }

  public PostOrderCreate setStopLoss(@Nullable String stopLoss) {
    this.stopLoss = stopLoss;
    return this;
  }

  public PostOrderCreate setTpTriggerBy(@Nullable String tpTriggerBy) {
    this.tpTriggerBy = tpTriggerBy;
    return this;
  }

  public PostOrderCreate setSlTriggerBy(@Nullable String slTriggerBy) {
    this.slTriggerBy = slTriggerBy;
    return this;
  }

  public PostOrderCreate setReduceOnly(@Nullable String reduceOnly) {
    this.reduceOnly = reduceOnly;
    return this;
  }

  public PostOrderCreate setCloseOnTrigger(@Nullable String closeOnTrigger) {
    this.closeOnTrigger = closeOnTrigger;
    return this;
  }

  public PostOrderCreate setSmpType(@Nullable String smpType) {
    this.smpType = smpType;
    return this;
  }

  public PostOrderCreate setMmp(@Nullable Boolean mmp) {
    this.mmp = mmp;
    return this;
  }

  public PostOrderCreate setTpslMode(@Nullable String tpslMode) {
    this.tpslMode = tpslMode;
    return this;
  }

  public PostOrderCreate setTpLimitPrice(@Nullable String tpLimitPrice) {
    this.tpLimitPrice = tpLimitPrice;
    return this;
  }

  public PostOrderCreate setSlLimitPrice(@Nullable String slLimitPrice) {
    this.slLimitPrice = slLimitPrice;
    return this;
  }

  public PostOrderCreate setTpOrderType(@Nullable String tpOrderType) {
    this.tpOrderType = tpOrderType;
    return this;
  }

  public PostOrderCreate setSlOrderType(@Nullable String slOrderType) {
    this.slOrderType = slOrderType;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/v5/order/create";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(category);
    builder.add("category", category);

    requireNonNull(symbol);
    builder.add("symbol", symbol);

    requireNonNull(side);
    builder.add("side", side);

    requireNonNull(orderType);
    builder.add("orderType", orderType);

    requireNonNull(qty);
    builder.add("qty", qty);

    if (isLeverage != null) {
      builder.add("isLeverage", isLeverage);
    }
    if (price != null) {
      builder.add("price", price);
    }
    if (triggerDirection != null) {
      builder.add("triggerDirection", triggerDirection);
    }
    if (orderFilter != null) {
      builder.add("orderFilter", orderFilter);
    }
    if (triggerPrice != null) {
      builder.add("triggerPrice", triggerPrice);
    }
    if (triggerBy != null) {
      builder.add("triggerBy", triggerBy);
    }
    if (orderIv != null) {
      builder.add("orderIv", orderIv);
    }
    if (timeInForce != null) {
      builder.add("timeInForce", timeInForce);
    }
    if (positionIdx != null) {
      builder.add("positionIdx", positionIdx);
    }
    if (orderLinkId != null) {
      builder.add("orderLinkId", orderLinkId);
    }
    if (takeProfit != null) {
      builder.add("takeProfit", takeProfit);
    }
    if (stopLoss != null) {
      builder.add("stopLoss", stopLoss);
    }
    if (tpTriggerBy != null) {
      builder.add("tpTriggerBy", tpTriggerBy);
    }
    if (slTriggerBy != null) {
      builder.add("slTriggerBy", slTriggerBy);
    }
    if (reduceOnly != null) {
      builder.add("reduceOnly", reduceOnly);
    }
    if (closeOnTrigger != null) {
      builder.add("closeOnTrigger", closeOnTrigger);
    }
    if (smpType != null) {
      builder.add("smpType", smpType);
    }
    if (mmp != null) {
      builder.add("mmp", mmp);
    }
    if (tpslMode != null) {
      builder.add("tpslMode", tpslMode);
    }
    if (tpLimitPrice != null) {
      builder.add("tpLimitPrice", tpLimitPrice);
    }
    if (slLimitPrice != null) {
      builder.add("slLimitPrice", slLimitPrice);
    }
    if (tpOrderType != null) {
      builder.add("tpOrderType", tpOrderType);
    }
    if (slOrderType != null) {
      builder.add("slOrderType", slOrderType);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ApiFactory.RateLimits.ONE_REST_PRIVATE_ORDER_WRITE_REQUEST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends ResponseWrapper<Result> {}

  @NotThreadSafe
  public static final class Result extends _OrderRef {}
}
