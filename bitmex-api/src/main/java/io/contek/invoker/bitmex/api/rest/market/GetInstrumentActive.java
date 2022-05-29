package io.contek.invoker.bitmex.api.rest.market;

import io.contek.invoker.bitmex.api.common._Instrument;
import io.contek.invoker.bitmex.api.rest.market.GetInstrumentActive.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;

import java.util.ArrayList;

public final class GetInstrumentActive extends MarketRestRequest<Response> {

  GetInstrumentActive(IActor actor, RestContext context) {
    super(actor, context);
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v1/instrument/active";
  }

  @Override
  protected RestParams getParams() {
    return RestParams.empty();
  }

  public static final class Response extends ArrayList<_Instrument> {}
}
