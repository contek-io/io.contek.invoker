package io.contek.invoker.bitmex.api.rest;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.*;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.NotThreadSafe;
import java.time.Clock;
import java.time.Duration;

import static com.google.common.net.UrlEscapers.urlFragmentEscaper;
import static io.contek.invoker.bitmex.api.ApiFactory.RateLimits.ONE_ANONYMOUS_REST_REQUEST;
import static io.contek.invoker.bitmex.api.ApiFactory.RateLimits.ONE_AUTHENTICATED_REST_REQUEST;
import static io.contek.invoker.commons.rest.RestMediaType.JSON;
import static java.time.temporal.ChronoUnit.MINUTES;

@NotThreadSafe
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
    anonymous = actor.getCredential().isAnonymous();
    clock = actor.getClock();
  }

  protected abstract RestMethod getMethod();

  protected abstract String getEndpointPath();

  protected abstract RestParams getParams();

  @Override
  protected final ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return anonymous ? ONE_ANONYMOUS_REST_REQUEST : ONE_AUTHENTICATED_REST_REQUEST;
  }

  @Override
  protected final RestCall createCall(ICredential credential) {
    RestMethod method = getMethod();
    switch (method) {
      case GET:
      case DELETE:
        String paramsString = buildParamsString();
        return RestCall.newBuilder()
            .setUrl(buildUrlString(paramsString))
            .setMethod(method)
            .setHeaders(generateHeaders(paramsString, "", credential))
            .build();
      case POST:
      case PUT:
        RestMediaBody body = JSON.createBody(getParams());
        return RestCall.newBuilder()
            .setUrl(buildUrlString(""))
            .setMethod(method)
            .setHeaders(generateHeaders("", body.getStringValue(), credential))
            .setBody(body)
            .build();
      default:
        throw new IllegalStateException(getMethod().name());
    }
  }

  private ImmutableMap<String, String> generateHeaders(
      String paramsString, String bodyString, ICredential credential) {
    if (credential.isAnonymous()) {
      return ImmutableMap.of();
    }
    long expires = clock.instant().plus(Duration.of(1, MINUTES)).getEpochSecond();
    String payload = getMethod() + getEndpointPath() + paramsString + expires + bodyString;
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
    return "?" + urlFragmentEscaper().escape(params.getQueryString());
  }

  private String buildUrlString(String paramsString) {
    return context.getBaseUrl() + getEndpointPath() + paramsString;
  }
}
