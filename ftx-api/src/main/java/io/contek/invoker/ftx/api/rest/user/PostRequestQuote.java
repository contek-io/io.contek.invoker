package io.contek.invoker.ftx.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._RequestQuote;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Objects;

@NotThreadSafe
public class PostRequestQuote extends UserRestRequest<PostRequestQuote.Response> {

  private String fromCoin;
  private String toCoin;
  private Double size;

  PostRequestQuote(IActor actor, RestContext context) {
    super(actor, context);
  }

  public String getFromCoin() {
    return fromCoin;
  }

  public PostRequestQuote setFromCoin(String fromCoin) {
    this.fromCoin = fromCoin;
    return this;
  }

  public String getToCoin() {
    return toCoin;
  }

  public PostRequestQuote setToCoin(String toCoin) {
    this.toCoin = toCoin;
    return this;
  }

  public double getSize() {
    return size;
  }

  public PostRequestQuote setSize(double size) {
    this.size = size;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return RestMethod.POST;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/otc/quotes";
  }

  @Override
  protected RestParams getParams() {
    Objects.requireNonNull(fromCoin);
    Objects.requireNonNull(toCoin);
    Objects.requireNonNull(size);

    return RestParams.newBuilder()
        .add("fromCoin", fromCoin)
        .add("toCoin", toCoin)
        .add("size", size)
        .build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<_RequestQuote> {}
}
