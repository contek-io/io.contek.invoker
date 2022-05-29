package io.contek.invoker.bitmex.api.rest.user;

import io.contek.invoker.bitmex.api.common._Order;
import io.contek.invoker.bitmex.api.rest.user.PostOrder.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import static com.google.common.base.Preconditions.checkNotNull;
import static io.contek.invoker.commons.rest.RestMethod.POST;

public final class PostOrder extends UserRestRequest<Response> {

  private String symbol;
  private String clOrdID;
  private String side;
  private String ordType;
  private String timeInForce;
  private String execInst;
  private Double orderQty;
  private Double price;

  PostOrder(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostOrder setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public PostOrder setClOrdID(String clOrdID) {
    this.clOrdID = clOrdID;
    return this;
  }

  public PostOrder setSide(String side) {
    this.side = side;
    return this;
  }

  public PostOrder setOrdType(String ordType) {
    this.ordType = ordType;
    return this;
  }

  public PostOrder setTimeInForce(String timeInForce) {
    this.timeInForce = timeInForce;
    return this;
  }

  public PostOrder setExecInst(String execInst) {
    this.execInst = execInst;
    return this;
  }

  public PostOrder setOrderQty(Double orderQty) {
    this.orderQty = orderQty;
    return this;
  }

  public PostOrder setPrice(Double price) {
    this.price = price;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return POST;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v1/order";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    checkNotNull(symbol);
    builder.add("symbol", symbol);
    if (clOrdID != null) {
      builder.add("clOrdID", clOrdID);
    }
    if (ordType != null) {
      builder.add("ordType", ordType);
    }
    if (side != null) {
      builder.add("side", side);
    }
    if (orderQty != null) {
      builder.add("orderQty", orderQty);
    }
    if (price != null) {
      builder.add("price", price);
    }
    if (timeInForce != null) {
      builder.add("timeInForce", timeInForce);
    }
    if (execInst != null) {
      builder.add("execInst", execInst);
    }

    return builder.build();
  }

  public static final class Response extends _Order {}
}
