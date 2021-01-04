package io.contek.invoker.bitmex.api.rest.market;

import io.contek.invoker.bitmex.api.common._TradeBin;
import io.contek.invoker.bitmex.api.rest.market.GetTradeBucketed.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@NotThreadSafe
public final class GetTradeBucketed extends MarketRestRequest<Response> {

  public static final int MAX_COUNT = 750;

  private String symbol;
  private String binSize;
  private Boolean partial;
  private Integer start;
  private Integer count;
  private Boolean reverse;
  private String startTime;
  private String endTime;

  GetTradeBucketed(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetTradeBucketed setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetTradeBucketed setBinSize(String binSize) {
    this.binSize = binSize;
    return this;
  }

  public GetTradeBucketed setPartial(@Nullable Boolean partial) {
    this.partial = partial;
    return this;
  }

  public GetTradeBucketed setStart(@Nullable Integer start) {
    this.start = start;
    return this;
  }

  public GetTradeBucketed setCount(@Nullable Integer count) {
    this.count = count;
    return this;
  }

  public GetTradeBucketed setReverse(@Nullable Boolean reverse) {
    this.reverse = reverse;
    return this;
  }

  public GetTradeBucketed setStartTime(@Nullable String startTime) {
    this.startTime = startTime;
    return this;
  }

  public GetTradeBucketed setEndTime(@Nullable String endTime) {
    this.endTime = endTime;
    return this;
  }

  @Override
  protected Class<Response> getResponseType() {
    return Response.class;
  }

  @Override
  protected String getEndpointPath() {
    return "/api/v1/trade/bucketed";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    checkNotNull(symbol);
    checkNotNull(binSize);
    builder.add("symbol", symbol);
    builder.add("binSize", binSize);
    if (partial != null) {
      builder.add("partial", partial);
    }
    if (start != null) {
      builder.add("start", start);
    }
    if (count != null) {
      checkArgument(count <= MAX_COUNT);
      builder.add("count", count);
    }
    if (reverse != null) {
      builder.add("reverse", reverse);
    }
    if (startTime != null) {
      builder.add("startTime", startTime);
    }
    if (endTime != null) {
      builder.add("endTime", endTime);
    }

    return builder.build();
  }

  @NotThreadSafe
  public static final class Response extends ArrayList<_TradeBin> {}
}
