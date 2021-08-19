package io.contek.invoker.ftx.api.rest.user;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.common._TransferBetweenSubAccounts;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;
import java.util.Objects;

public final class PostTransferBetweenSubAccounts
    extends SubAccountRestRequest<PostTransferBetweenSubAccounts.Response> {

  private String coin;
  private Double size;
  private String source;
  private String destination;

  PostTransferBetweenSubAccounts(IActor actor, RestContext context) {
    super(actor, context);
  }

  public String getCoin() {
    return coin;
  }

  public PostTransferBetweenSubAccounts setCoin(String coin) {
    this.coin = coin;
    return this;
  }

  public Double getSize() {
    return size;
  }

  public PostTransferBetweenSubAccounts setSize(double size) {
    this.size = size;
    return this;
  }

  public String getSource() {
    return source;
  }

  public PostTransferBetweenSubAccounts setSource(String source) {
    this.source = source;
    return this;
  }

  public String getDestination() {
    return destination;
  }

  public PostTransferBetweenSubAccounts setDestination(String destination) {
    this.destination = destination;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return RestMethod.POST;
  }

  @Override
  protected String getEndpointPathSubAccount() {
    return "/transfer";
  }

  @Override
  protected RestParams getParams() {
    Objects.requireNonNull(coin);
    Objects.requireNonNull(size);
    return RestParams.newBuilder()
        .add("coin", coin)
        .add("size", size)
        .add("source", source)
        .add("destination", destination)
        .build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<List<_TransferBetweenSubAccounts>> {}
}
