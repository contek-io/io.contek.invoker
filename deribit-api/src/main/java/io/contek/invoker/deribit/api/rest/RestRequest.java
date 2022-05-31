package io.contek.invoker.deribit.api.rest;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.BaseEncoding;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.*;
import io.contek.invoker.security.ICredential;
import io.vertx.core.buffer.Buffer;

import java.time.Clock;
import java.util.Random;

import static com.google.common.net.UrlEscapers.urlPathSegmentEscaper;
import static io.contek.invoker.commons.rest.RestMediaType.JSON;

public abstract class RestRequest<R> extends BaseRestRequest<R> {

  private final RestContext context;
  private final Clock clock;

  protected RestRequest(IActor actor, RestContext context) {
    super(actor);
    this.context = context;
    clock = actor.clock();
  }

  private static String generateNounce() {
    byte[] randomBytes = new byte[8];
    (new Random()).nextBytes(randomBytes);
    return BaseEncoding.base32().encode(randomBytes);
  }

  protected abstract RestMethod getMethod();

  protected abstract String getEndpointPath();

  protected abstract RestParams getParams();

  protected abstract ImmutableList<TypedPermitRequest> getRequiredQuotas();

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
        RestMediaBody body = JSON.create(getParams());
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
    String clientId = credential.getApiKeyId();
    String timestamp = String.valueOf(clock.millis());
    String nonce = generateNounce();
    String uri = getEndpointPath() + paramsString;
    String payload =
        timestamp + "\n" + nonce + "\n" + method + "\n" + uri + "\n" + body.toString() + "\n";
    String signature = credential.sign(payload);
    String authorizationValue =
        String.format(
            "deri-hmac-sha256 id=%s,ts=%s,sig=%s,nonce=%s", clientId, timestamp, signature, nonce);
    return ImmutableMap.<String, String>builder().put("Authorization", authorizationValue).build();
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
