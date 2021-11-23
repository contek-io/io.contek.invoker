package io.contek.invoker.bitmex.api.rest.user;

import com.google.gson.Gson;
import io.contek.invoker.bitmex.api.common._Execution;
import io.contek.invoker.bitmex.api.rest.user.GetExecution.Response;
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
public final class GetExecution extends UserRestRequest<Response> {

  private static final Gson gson = new Gson();

  private String symbol;
  private String startTime;
  private String endTime;
  private Integer count;
  private Integer start;
  private Boolean reverse;
  private final Map<String, String> filter = new HashMap<>();

  GetExecution(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetExecution setSymbol(@Nullable String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetExecution setStartTime(@Nullable String startTime) {
    this.startTime = startTime;
    return this;
  }

  public GetExecution setEndTime(@Nullable String endTime) {
    this.endTime = endTime;
    return this;
  }

  public GetExecution setCount(@Nullable Integer count) {
    this.count = count;
    return this;
  }

  public GetExecution setStart(@Nullable Integer start) {
    this.start = start;
    return this;
  }

  public GetExecution setReverse(@Nullable Boolean reverse) {
    this.reverse = reverse;
    return this;
  }

  public GetExecution addFilter(String key, String value) {
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
    return "/api/v1/execution";
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
