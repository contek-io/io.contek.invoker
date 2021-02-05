package io.contek.invoker.deribit.api.rest;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.BaseEncoding;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.*;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.NotThreadSafe;
import java.time.Clock;
import java.util.Random;

import static com.google.common.net.UrlEscapers.urlFragmentEscaper;
import static io.contek.invoker.commons.rest.RestMediaType.JSON;

@NotThreadSafe
public abstract class RestRequest<R> extends BaseRestRequest<R> {

  private final RestContext context;
  private final Clock clock;

  protected RestRequest(IActor actor, RestContext context) {
    super(actor);
    this.context = context;
    clock = actor.getClock();
  }

  protected abstract RestMethod getMethod();

  protected abstract String getEndpointPath();

  protected abstract RestParams getParams();

  protected abstract ImmutableList<RateLimitQuota> getRequiredQuotas();

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
          .setHeaders(generateHeaders(method, paramsString, "", credential))
          .build();
      case POST:
      case PUT:
        RestMediaBody body = JSON.createBody(getParams());
        return RestCall.newBuilder()
          .setUrl(buildUrlString(""))
          .setMethod(method)
          .setHeaders(generateHeaders(method, "", body.getStringValue(), credential))
          .setBody(body)
          .build();
      default:
        throw new IllegalStateException(getMethod().name());
    }
  }

  private ImmutableMap<String, String> generateHeaders(
    RestMethod method, String paramsString, String bodyString, ICredential credential) {

    if (credential.isAnonymous()) {
      return ImmutableMap.of();
    }
    String clientId = credential.getApiKeyId();
    String timestamp = String.valueOf(clock.millis());
    String nonce = generateNounce();
    String uri = getEndpointPath() + paramsString;
    String payload = timestamp + "\n"
      + nonce + "\n"
      + method + "\n"
      + uri + "\n"
      + bodyString + "\n";
    String signature = credential.sign(payload);
    String authorizationValue = String.format(
      "deri-hmac-sha256 id=%s,ts=%s,sig=%s,nonce=%s", clientId, timestamp, signature, nonce);
    return ImmutableMap.<String, String>builder()
      .put("Authorization", authorizationValue)
      .build();
  }

  private static String generateNounce() {
    byte[] randomBytes = new byte[8];
    (new Random()).nextBytes(randomBytes);
    return BaseEncoding.base32().encode(randomBytes);
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
