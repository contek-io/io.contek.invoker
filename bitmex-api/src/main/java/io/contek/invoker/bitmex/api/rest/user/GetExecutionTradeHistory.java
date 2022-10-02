package io.contek.invoker.bitmex.api.rest.user;

import com.google.gson.Gson;
import io.contek.invoker.bitmex.api.common._Execution;
import io.contek.invoker.bitmex.api.rest.user.GetExecutionTradeHistory.Response;
import io.contek.invoker.commons.GsonFactory;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.contek.invoker.commons.rest.RestMethod.GET;

@NotThreadSafe
public final class GetExecutionTradeHistory extends UserRestRequest<Response> {

  private static final Gson gson = GsonFactory.buildGson();

  private String symbol;
  private String startTime;
  private String endTime;
  private Integer count;
  private Integer start;
  private Boolean reverse;
  private final Map<String, String> filter = new HashMap<>();

  GetExecutionTradeHistory(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetExecutionTradeHistory setSymbol(@Nullable String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetExecutionTradeHistory setStartTime(@Nullable String startTime) {
    this.startTime = startTime;
    return this;
  }

  public GetExecutionTradeHistory setEndTime(@Nullable String endTime) {
    this.endTime = endTime;
    return this;
  }

  public GetExecutionTradeHistory setCount(@Nullable Integer count) {
    this.count = count;
    return this;
  }

  public GetExecutionTradeHistory setStart(@Nullable Integer start) {
    this.start = start;
    return this;
  }

  public GetExecutionTradeHistory setReverse(@Nullable Boolean reverse) {
    this.reverse = reverse;
    return this;
  }

  public GetExecutionTradeHistory addFilter(String key, String value) {
    filter.put(key, value);
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
    return "/api/v1/execution/tradeHistory";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (symbol != null) {
      builder.add("symbol", symbol);
    }
    if (startTime != null) {
      builder.add("startTime", startTime);
    }
    if (endTime != null) {
      builder.add("endTime", endTime);
    }
    if (count != null) {
      builder.add("count", count);
    }
    if (start != null) {
      builder.add("start", start);
    }
    if (reverse != null) {
      builder.add("reverse", reverse);
    }
    if (!filter.isEmpty()) {
      builder.add("filter", gson.toJson(filter, Map.class));
    }

    return builder.build();
  }

  @NotThreadSafe
  public static final class Response extends ArrayList<_Execution> {}
}
