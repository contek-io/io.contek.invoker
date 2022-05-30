package io.contek.invoker.bitmex.api.rest.user;

import io.contek.invoker.bitmex.api.common._Execution;
import io.contek.invoker.bitmex.api.rest.user.GetExecutionTradeHistory.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.vertx.core.json.Json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.contek.invoker.commons.rest.RestMethod.GET;

public final class GetExecutionTradeHistory extends UserRestRequest<Response> {

  private final Map<String, String> filter = new HashMap<>();
  private String symbol;
  private String startTime;
  private String endTime;
  private Integer count;
  private Integer start;
  private Boolean reverse;

  GetExecutionTradeHistory(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetExecutionTradeHistory setSymbol(String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetExecutionTradeHistory setStartTime(String startTime) {
    this.startTime = startTime;
    return this;
  }

  public GetExecutionTradeHistory setEndTime(String endTime) {
    this.endTime = endTime;
    return this;
  }

  public GetExecutionTradeHistory setCount(Integer count) {
    this.count = count;
    return this;
  }

  public GetExecutionTradeHistory setStart(Integer start) {
    this.start = start;
    return this;
  }

  public GetExecutionTradeHistory setReverse(Boolean reverse) {
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
      builder.add("filter", Json.encode(filter));
      // TODO
//      builder.add("filter", gson.toJson(filter, Map.class));
    }

    return builder.build();
  }

  public static final class Response extends ArrayList<_Execution> {}
}
