package io.contek.invoker.deribit.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.deribit.api.common._Instrument;
import io.contek.invoker.deribit.api.rest.common.RestResponse;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetInstruments extends MarketRestRequest<GetInstruments.Response> {

  private String currency;
  // can only be either `future` or `option`.
  private String kind;
  private boolean expired;

  GetInstruments(IActor actor, RestContext context) {
    super(actor, context);
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v2/public/get_instruments";
  }

  public GetInstruments setCurrency(String currency) {
    this.currency = currency;
    return this;
  }

  public GetInstruments setKind(String kind) {
    this.kind = kind;
    return this;
  }

  public GetInstruments setExpired(boolean expired) {
    this.expired = expired;
    return this;
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(currency);
    builder.add("currency", currency);
    if (kind != null) {
      builder.add("kind", kind);
    }
    if (expired) {
      builder.add("expired", true);
    }

    return builder.build();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<List<_Instrument>> {}
}
