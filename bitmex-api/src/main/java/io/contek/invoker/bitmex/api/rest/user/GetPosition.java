package io.contek.invoker.bitmex.api.rest.user;

import com.google.gson.Gson;
import io.contek.invoker.bitmex.api.common._Position;
import io.contek.invoker.bitmex.api.rest.user.GetPosition.Response;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.rest.RestContext;
import io.contek.invoker.commons.api.rest.RestMethod;
import io.contek.invoker.commons.api.rest.RestParams;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.contek.invoker.commons.api.rest.RestMethod.GET;

@NotThreadSafe
public final class GetPosition extends UserRestRequest<Response> {

  private static final Gson gson = new Gson();

  private Integer count;
  private final Map<String, String> filter = new HashMap<>();

  GetPosition(IActor actor, RestContext context) {
    super(actor, context);
  }

  public GetPosition setCount(@Nullable Integer count) {
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
      builder.add("filter", gson.toJson(filter, Map.class));
    }

    return builder.build();
  }

  @NotThreadSafe
  public static final class Response extends ArrayList<_Position> {}
}
