package io.contek.invoker.okx.api.rest.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;
import io.contek.invoker.okx.api.common._Instrument;
import io.contek.invoker.okx.api.rest.common.RestResponse;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

import static io.contek.invoker.okx.api.common.constants.InstrumentTypeKeys.*;
import static java.util.Objects.requireNonNull;

@NotThreadSafe
public final class GetPublicInstruments extends MarketRestRequest<GetPublicInstruments.Response> {

  private String instType;
  private String uly;
  private String instId;

  GetPublicInstruments(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetPublicInstruments setInstType(String instType) {
    this.instType = instType;
    return this;
  }

  public GetPublicInstruments setUly(@Nullable String uly) {
    this.uly = uly;
    return this;
  }

  public GetPublicInstruments setInstId(@Nullable String instId) {
    this.instId = instId;
    return this;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v5/public/instruments";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    requireNonNull(instType);
    builder.add("instType", instType);

    switch (instType) {
      case _OPTION:
        requireNonNull(uly);
      case _FUTURES:
      case _SWAP:
        if (uly != null) {
          builder.add("uly", uly);
        }
        break;
      default:
    }

    if (instId != null) {
      builder.add("instId", instId);
    }

    return RestParams.empty();
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @NotThreadSafe
  public static final class Response extends RestResponse<List<_Instrument>> {}
}
