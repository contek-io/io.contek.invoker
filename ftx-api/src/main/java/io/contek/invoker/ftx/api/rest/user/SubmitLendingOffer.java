package io.contek.invoker.ftx.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Objects;

@NotThreadSafe
public final class SubmitLendingOffer extends SpotMarginRestRequest<SubmitLendingOffer.Response> {

  private String coin;
  private Double size;
  private Double rate;

  SubmitLendingOffer(IActor actor, RestContext context) {
    super(actor, context);
  }

  public String getCoin() {
    return coin;
  }

  public SubmitLendingOffer setCoin(String coin) {
    this.coin = coin;
    return this;
  }

  public Double getSize() {
    return size;
  }

  public SubmitLendingOffer setSize(Double size) {
    this.size = size;
    return this;
  }

  public Double getRate() {
    return rate;
  }

  public SubmitLendingOffer setRate(Double rate) {
    this.rate = rate;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return RestMethod.POST;
  }

  @Override
  protected String getEndpointPathSpotMargin() {
    return "offers";
  }

  @Override
  protected RestParams getParams() {
    Objects.requireNonNull(coin);
    Objects.requireNonNull(size);
    Objects.requireNonNull(rate);
    return RestParams.newBuilder().add("coin", coin).add("size", size).add("rate", rate).build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse {}
}
