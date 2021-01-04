package io.contek.invoker.bitstamp.api.rest.market;

import io.contek.invoker.bitstamp.api.common._OrderBook;
import io.contek.invoker.bitstamp.api.rest.RestRequest;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.commons.rest.RestMethod.GET;

@NotThreadSafe
public final class GetOrderBook extends RestRequest<GetOrderBook.Response> {

  private String currencyPair;
  private Integer group;

  GetOrderBook(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetOrderBook setCurrencyPair(String currencyPair) {
    this.currencyPair = currencyPair;
    return this;
  }

  public GetOrderBook setGroup(Integer group) {
    this.group = group;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return GET;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v2/order_book/" + currencyPair;
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (group != null) {
      builder.add("group", group);
    }

    return builder.build();
  }

  @NotThreadSafe
  public static final class Response extends _OrderBook {}
}
