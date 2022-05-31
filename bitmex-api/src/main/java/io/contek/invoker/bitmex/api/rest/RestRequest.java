package io.contek.invoker.bitmex.api.rest;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.*;
import io.contek.invoker.security.ICredential;
import io.vertx.core.buffer.Buffer;

import java.time.Clock;
import java.time.Duration;

import static com.google.common.net.UrlEscapers.urlPathSegmentEscaper;
import static io.contek.invoker.bitmex.api.ApiFactory.RateLimits.ONE_ANONYMOUS_REST_REQUEST;
import static io.contek.invoker.bitmex.api.ApiFactory.RateLimits.ONE_AUTHENTICATED_REST_REQUEST;
import static io.contek.invoker.commons.rest.RestMediaType.JSON;
import static java.time.temporal.ChronoUnit.MINUTES;

public abstract class RestRequest<R> extends BaseRestRequest<R> {

  private static final String API_EXPIRES = "api-expires";
  private static final String API_KEY = "api-key";
  private static final String API_SIGNATURE = "api-signature";

  private final RestContext context;
  private final boolean anonymous;
  private final Clock clock;

  protected RestRequest(IActor actor, RestContext context) {
    super(actor);
    this.context = context;
    anonymous = actor.credential().isAnonymous();
    clock = actor.clock();
  }

  protected abstract RestMethod getMethod();

  protected abstract String getEndpointPath();

  protected abstract RestParams getParams();

  @Override
  protected final ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return anonymous ? ONE_ANONYMOUS_REST_REQUEST : ONE_AUTHENTICATED_REST_REQUEST;
  }

  @Override
  protected final RestCall createCall(ICredential credential) {
    RestMethod method = getMethod();
    return switch (method) {
      case GET, DELETE -> {
        String paramsString = buildParamsString();
        yield RestCall.newBuilder()
          .setUrl(buildUrlString(paramsString))
          .setMethod(method)
          .setHeaders(generateHeaders(paramsString, Buffer.buffer(""), credential))
          .build();
      }
      case POST, PUT -> {
        RestMediaBody body = JSON.create(getParams());
        yield RestCall.newBuilder()
          .setUrl(buildUrlString(""))
          .setMethod(method)
          .setHeaders(generateHeaders("", body.body(), credential))
          .setBody(body)
          .build();
      }
    };
  }

  private ImmutableMap<String, String> generateHeaders(
      String paramsString, Buffer body, ICredential credential) {
    if (credential.isAnonymous()) {
      return ImmutableMap.of();
    }
    long expires = clock.instant().plus(Duration.of(1, MINUTES)).getEpochSecond();
    String payload = getMethod() + getEndpointPath() + paramsString + expires + body.toString();
    String signature = credential.sign(payload);
    return ImmutableMap.<String, String>builder()
        .put(API_EXPIRES, Long.toString(expires))
        .put(API_KEY, credential.getApiKeyId())
        .put(API_SIGNATURE, signature)
        .build();
  }

  private String buildParamsString() {
    RestParams params = getParams();
    if (params.isEmpty()) {
      return "";
    }
    return "?" + params.getQueryString(urlPathSegmentEscaper());
  }

  private String buildUrlString(String paramsString) {
    return context.baseUrl() + getEndpointPath() + paramsString;
  }
}
