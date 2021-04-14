package io.contek.invoker.hbdmlinear.api.rest;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.BaseEncoding;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.rest.*;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.NotThreadSafe;
import java.net.URI;
import java.time.Clock;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static com.google.common.net.UrlEscapers.urlPathSegmentEscaper;
import static io.contek.invoker.commons.rest.RestMediaType.JSON;
import static java.time.ZoneOffset.UTC;
import static java.time.format.DateTimeFormatter.ISO_DATE;

@NotThreadSafe
public abstract class RestRequest<R> extends BaseRestRequest<R> {

  private static final DateTimeFormatter FORMATTER = ISO_DATE.withZone(UTC);

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
        return RestCall.newBuilder()
            .setUrl(generateUrl(method, getEndpointPath(), getParams(), credential))
            .setMethod(method)
            .build();
      case POST:
      case PUT:
        RestMediaBody body = JSON.createBody(getParams());
        return RestCall.newBuilder()
            .setUrl(generateUrl(method, getEndpointPath(), RestParams.empty(), credential))
            .setMethod(method)
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
    String payload =
        timestamp + "\n" + nonce + "\n" + method + "\n" + uri + "\n" + bodyString + "\n";
    String signature = credential.sign(payload);
    String authorizationValue =
        String.format(
            "deri-hmac-sha256 id=%s,ts=%s,sig=%s,nonce=%s", clientId, timestamp, signature, nonce);
    return ImmutableMap.<String, String>builder().put("Authorization", authorizationValue).build();
  }

  private static String generateNounce() {
    byte[] randomBytes = new byte[8];
    (new Random()).nextBytes(randomBytes);
    return BaseEncoding.base32().encode(randomBytes);
  }

  private String generateUrl(
      RestMethod method, String endpointPath, RestParams endpointParams, ICredential credential) {
    String paramString =
        credential.isAnonymous()
            ? generateParamString(endpointParams)
            : generateSignedParamString(method, endpointPath, endpointParams, credential);
    return context.getBaseUrl() + endpointPath + paramString;
  }

  private String generateParamString(RestParams params) {
    if (params.isEmpty()) {
      return "";
    }
    return params.getQueryString(urlPathSegmentEscaper());
  }

  private String generateSignedParamString(
      RestMethod method, String path, RestParams params, ICredential credential) {
    RestParams.Builder builder = params.toBuilder();
    builder.add("AccessKeyId", credential.getApiKeyId());
    builder.add("SignatureMethod", credential.getAlgorithm().name());
    builder.add("SignatureVersion", 2);
    builder.add("Timestamp", FORMATTER.format(clock.instant()));
    RestParams withIdentity = builder.build(true);

    String payload =
        String.join(
            "\n",
            method.name(),
            URI.create(context.getBaseUrl()).getHost(),
            path,
            withIdentity.getQueryString(urlPathSegmentEscaper()));
    String sign = credential.sign(payload);
    return "?" + withIdentity.toBuilder().add("Signature", sign).build().getQueryString();
  }
}
