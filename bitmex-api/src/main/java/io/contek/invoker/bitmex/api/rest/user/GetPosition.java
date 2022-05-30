package io.contek.invoker.bitmex.api.rest.user;

import io.contek.invoker.bitmex.api.common._Position;
import io.contek.invoker.bitmex.api.rest.user.GetPosition.Response;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.RestContext;
import io.contek.invoker.commons.rest.RestMethod;
import io.contek.invoker.commons.rest.RestParams;
import io.vertx.core.json.Json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.contek.invoker.commons.rest.RestMethod.GET;

public final class GetPosition extends UserRestRequest<Response> {

  private final Map<String, String> filter = new HashMap<>();
  private Integer count;

  GetPosition(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetPosition setCount(Integer count) {
    this.count = count;
    return this;
  }

  public GetPosition addFilter(String key, String value) {
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
    return "/api/v1/position";
  }

  @Override
  protected RestParams getParams() {
    RestParams.Builder builder = RestParams.newBuilder();

    if (count != null) {
      builder.add("count", count);
    }
    if (!filter.isEmpty()) {
//      builder.add("filter", gson.toJson(filter, Map.class));
      builder.add("filter", Json.encode(filter));
      // TODO
    }

    return builder.build();
  }

  public static final class Response extends ArrayList<_Position> {}
}
