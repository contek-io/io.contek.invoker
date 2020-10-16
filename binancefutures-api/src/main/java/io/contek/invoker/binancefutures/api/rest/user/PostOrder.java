package io.contek.invoker.binancefutures.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.binancefutures.api.common._Order;
import io.contek.invoker.binancefutures.api.rest.user.PostOrder.Response;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.rest.RestMethod;
import io.contek.invoker.commons.api.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.contek.invoker.binancefutures.api.ApiFactory.RateLimits.ONE_REST_ORDER_REQUEST;
import static io.contek.invoker.commons.api.rest.RestMethod.POST;

@NotThreadSafe
public final class PostOrder extends UserRestRequest<Response> {

  private String symbol;
  private String side;
  private String positionSide;
  private String type;
  private String timeInForce;
  private Double quantity;
  private Double price;
  private String newClientOrderId;

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

  public PostOrder setPositionSide(String positionSide) {
    this.positionSide = positionSide;
    return this;
  }

  public PostOrder setType(String type) {
    this.type = type;
    return this;
  }

  public PostOrder setTimeInForce(@Nullable String timeInForce) {
    this.timeInForce = timeInForce;
    return this;
  }

  public PostOrder setQuantity(double quantity) {
    this.quantity = quantity;
    return this;
  }

  public PostOrder setPrice(double price) {
    this.price = price;
    return this;
  }

  public PostOrder setNewClientOrderId(@Nullable String newClientOrderId) {
    this.newClientOrderId = newClientOrderId;
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
    return "/fapi/v1/order";
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

    checkNotNull(quantity);
    builder.add("quantity", quantity);

    checkNotNull(price);
    builder.add("price", price);

    if (positionSide != null) {
      builder.add("positionSide", positionSide);
    }

    if (timeInForce != null) {
      builder.add("timeInForce", timeInForce);
    }

    if (newClientOrderId != null) {
      builder.add("newClientOrderId", newClientOrderId);
    }

    builder.add("timestamp", getMillis());

    return builder.build();
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_REST_ORDER_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends _Order {}
}
