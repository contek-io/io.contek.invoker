package io.contek.invoker.ftx.api.rest.otc;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.ftx.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Objects;

@NotThreadSafe
public class CancelRequestedWithdrawal
    extends OTCWithdrawalsRestRequest<CancelRequestedWithdrawal.Response> {

  private String id;

  public CancelRequestedWithdrawal(IActor actor, RestContext context) {
    super(actor, context);
  }

  public String getId() {
    return id;
  }

  public CancelRequestedWithdrawal setId(String id) {
    this.id = id;
    return this;
  }

  @Override
  protected RestMethod getMethod() {
    return RestMethod.DELETE;
  }

  @Override
  protected String getEndpointPathOTC() {
    return "";
  }

  @Override
  protected RestParams getParams() {
    Objects.requireNonNull(id);
    return RestParams.newBuilder().add("id", id).build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse {}
}
