package io.contek.invoker.hbdminverse.api.rest.user;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.hbdminverse.api.rest.common.RestDataResponse;
import io.contek.invoker.hbdminverse.api.rest.common.RestError;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static io.contek.invoker.hbdminverse.api.ApiFactory.RateLimits.ONE_API_KEY_REST_PRIVATE_WRITE_REQUEST;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class PostSwapCancel extends UserRestRequest<PostSwapCancel.Response> {

  private String order_id;
  private String client_order_id;
  private String contract_code;

  PostSwapCancel(IActor actor, RestContext context) {
    super(actor, context);
  }

  public PostSwapCancel setOrderId(String order_id) {
    this.order_id = order_id;
    return this;
  }

  public PostSwapCancel setClientOrderId(String client_order_id) {
    this.client_order_id = client_order_id;
    return this;
  }

  public PostSwapCancel setContractCode(String contract_code) {
    this.contract_code = contract_code;
    return this;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/swap-api/v1/swap_cancel";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(contract_code);
    builder.add("contract_code", contract_code);

    if (order_id != null) {
      builder.add("order_id", order_id);
    }

    if (client_order_id != null) {
      builder.add("client_order_id", client_order_id);
    }

    return builder.build();
  }

  @Override
  protected ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_API_KEY_REST_PRIVATE_WRITE_REQUEST;
  }

  @NotThreadSafe
  public static final class Response extends RestDataResponse<Data> {}

  @NotThreadSafe
  public static final class Data {

    public List<Error> errors;
    public String successes;
  }

  @NotThreadSafe
  public static final class Error extends RestError {

    public String order_id;
  }
}
