package io.contek.invoker.binancespot.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancespot.api.common._Order;
import io.contek.invoker.binancespot.api.rest.user.PostOrder.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.contek.invoker.binancespot.api.ApiFactory.RateLimits.ONE_REST_ORDER_REQUEST;
import static io.contek.invoker.commons.rest.RestMethod.POST;

public final class PostOrder extends UserRestRequest<Response> {

  private String symbol;
  private String side;
  private String type;
  private String timeInForce;
  private Double quantity;
  private Double quoteOrderQty;
  private Double price;
  private String newClientOrderId;
  private Double stopPrice;
  private Double icebergQty;
  private String newOrderRespType;

  PostOrder(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostOrder setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public PostOrder setSide(String side) {
    this.side = side;
    return this;
  }

  public PostOrder setType(String type) {
    this.type = type;
    return this;
  }

  public PostOrder setTimeInForce(String timeInForce) {
    this.timeInForce = timeInForce;
    return this;
  }

  public PostOrder setQuantity(Double quantity) {
    this.quantity = quantity;
    return this;
  }

  public PostOrder setQuoteOrderQty(Double quoteOrderQty) {
    this.quoteOrderQty = quoteOrderQty;
    return this;
  }

  public PostOrder setPrice(Double price) {
    this.price = price;
    return this;
  }

  public PostOrder setNewClientOrderId(String newClientOrderId) {
    this.newClientOrderId = newClientOrderId;
    return this;
  }

  public PostOrder setStopPrice(Double stopPrice) {
    this.stopPrice = stopPrice;
    return this;
  }

  public PostOrder setIcebergQty(Double icebergQty) {
    this.icebergQty = icebergQty;
    return this;
  }

  public PostOrder setNewOrderRespType(String newOrderRespType) {
    this.newOrderRespType = newOrderRespType;
    return this;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v3/order";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    checkNotNull(symbol);
    builder.add("symbol", symbol);

    checkNotNull(side);
    builder.add("side", side);

    checkNotNull(type);
    builder.add("type", type);

    if (quantity != null) {
      builder.add("quantity", quantity);
    }

    if (quoteOrderQty != null) {
      builder.add("quoteOrderQty", quoteOrderQty);
    }

    if (price != null) {
      builder.add("price", price);
    }

    if (timeInForce != null) {
      builder.add("timeInForce", timeInForce);
    }

    if (newClientOrderId != null) {
      builder.add("newClientOrderId", newClientOrderId);
    }

    if (stopPrice != null) {
      builder.add("stopPrice", stopPrice);
    }

    if (icebergQty != null) {
      builder.add("icebergQty", icebergQty);
    }

    if (newOrderRespType != null) {
      builder.add("newOrderRespType", newOrderRespType);
    }

    builder.add("timestamp", getMillis());

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_ORDER_REQUEST;
  }

  public static final class Response extends _Order {}
}
