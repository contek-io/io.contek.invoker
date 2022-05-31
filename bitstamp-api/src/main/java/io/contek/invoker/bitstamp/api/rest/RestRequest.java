package io.contek.invoker.bitstamp.api.rest;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.*;
import io.contek.invoker.security.ICredential;
import io.vertx.core.buffer.Buffer;

import java.net.URI;
import java.time.Clock;
import java.util.UUID;

import static com.google.common.net.UrlEscapers.urlPathSegmentEscaper;
import static io.contek.invoker.bitstamp.api.ApiFactory.RateLimits.ONE_REST_REQUEST;
import static io.contek.invoker.commons.rest.RestMediaType.FORM;

public abstract class RestRequest<R> extends BaseRestRequest<R> {

  private static final String AUTH_VERSION = "v2";

  private final RestContext context;
  private final String urlHost;
  private final Clock clock;

  protected RestRequest(IActor actor, RestContext context) {
    super(actor);
    this.context = context;
    urlHost = URI.create(context.baseUrl()).getHost();
    clock = actor.clock();
  }

  protected abstract RestMethod getMethod();

  protected abstract String getEndpointPath();

  protected abstract RestParams getParams();

  @Override
  protected final ImmutableList<TypedPermitRequest> getRequiredQuotas() {
    return ONE_REST_REQUEST;
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
          .setHeaders(generateHeaders(method, paramsString, Buffer.buffer(""), credential))
          .build();
      }
      case POST, PUT -> {
        RestMediaBody body = FORM.create(getParams());
        yield RestCall.newBuilder()
          .setUrl(buildUrlString(""))
          .setMethod(method)
          .setHeaders(generateHeaders(method, "", body.body(), credential))
          .setBody(body)
          .build();
      }
    };
  }

  private ImmutableMap<String, String> generateHeaders(
      RestMethod method, String paramsString, Buffer body, ICredential credential) {
    if (credential.isAnonymous()) {
      return ImmutableMap.of();
    }
    String auth = "BITSTAMP" + credential.getApiKeyId();
    String timestamp = String.valueOf(clock.millis());
    String nonce = UUID.randomUUID().toString();
    final String bodyString = body.toString();
    String contentType = bodyString.isEmpty() ? "" : FORM.getValue();
    String payload =
        auth
            + method
            + urlHost
            + getEndpointPath()
            + paramsString
            + contentType
            + nonce
            + timestamp
            + AUTH_VERSION
            + bodyString;

    String signature = credential.sign(payload);
    return ImmutableMap.<String, String>builder()
        .put("X-Auth", auth)
        .put("X-Auth-Signature", signature)
        .put("X-Auth-Nonce", nonce)
        .put("X-Auth-Timestamp", timestamp)
        .put("X-Auth-Version", AUTH_VERSION)
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
