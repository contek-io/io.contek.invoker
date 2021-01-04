package io.contek.invoker.bitmex.api.rest.user;

import com.google.gson.Gson;
import io.contek.invoker.bitmex.api.common._Order;
import io.contek.invoker.bitmex.api.rest.user.GetOrder.Response;
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
public final class GetOrder extends UserRestRequest<Response> {

  private static final Gson gson = new Gson();

  private String symbol;
  private Integer count;
  private Integer start;
  private Boolean reverse;
  private String startTime;
  private String endTime;
  private final Map<String, String> filter = new HashMap<>();

  GetOrder(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetOrder setSymbol(@Nullable String symbol) {
    this.symbol = symbol;
    return this;
  }

  public GetOrder setCount(@Nullable Integer count) {
    this.count = count;
    return this;
  }

  public GetOrder setStart(@Nullable Integer start) {
    this.start = start;
    return this;
  }

  public GetOrder setReverse(@Nullable Boolean reverse) {
    this.reverse = reverse;
    return this;
  }

  public GetOrder setStartTime(@Nullable String startTime) {
    this.startTime = startTime;
    return this;
  }

  public GetOrder setEndTime(@Nullable String endTime) {
    this.endTime = endTime;
    return this;
  }

  public GetOrder addFilter(String key, String value) {
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
    return "/api/v1/order";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (symbol != null) {
      builder.add("symbol", symbol);
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
    if (startTime != null) {
      builder.add("startTime", startTime);
    }
    if (endTime != null) {
      builder.add("endTime", endTime);
    }
    if (!filter.isEmpty()) {
      builder.add("filter", gson.toJson(filter, Map.class));
    }

    return builder.build();
  }

  @NotThreadSafe
  public static final class Response extends ArrayList<_Order> {}
}
