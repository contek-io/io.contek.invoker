package io.contek.invoker.okx.api.rest;

import com.google.common.collect.ImmutableMap;
import com.google.common.net.UrlEscapers;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.http.AnyHttpException;
import io.contek.invoker.commons.actor.http.ParsedHttpException;
import io.contek.invoker.commons.rest.*;
import io.contek.invoker.okx.api.rest.common.ResponseWrapper;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.NotThreadSafe;
import java.time.Clock;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import static io.contek.invoker.commons.rest.RestMediaType.JSON;
import static java.time.ZoneOffset.UTC;

@NotThreadSafe
public abstract class RestRequest<R extends ResponseWrapper<?>> extends BaseRestRequest<R> {

  public static final String OK_ACCESS_PASSPHRASE = "OK-ACCESS-PASSPHRASE";

  private static final DateTimeFormatter FORMATTER =
      new DateTimeFormatterBuilder()
          .parseCaseInsensitive()
          .appendInstant(3)
          .toFormatter()
          .withZone(UTC);

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

  @Override
  protected final RestCall createCall(ICredential credential) {
    RestMethod method = getMethod();
    switch (method) {
      case GET -> {
        String paramsString = buildParamsString();
        return RestCall.newBuilder()
            .setUrl(buildUrlString(paramsString))
            .setMethod(method)
            .setHeaders(generateHeaders(paramsString, "", credential))
            .build();
      }
      case POST -> {
        RestMediaBody body = JSON.createBody(getParams());
        return RestCall.newBuilder()
            .setUrl(buildUrlString(""))
            .setMethod(method)
            .setHeaders(generateHeaders("", body.getStringValue(), credential))
            .setBody(body)
            .build();
      }
      default -> throw new IllegalStateException(getMethod().name());
    }
  }

  @Override
  protected final void checkResult(R result, RestResponse response) throws AnyHttpException {
    if (result.code.equals("0")) {
      return;
    }

    throw new ParsedHttpException(response.getCode(), result, result.msg);
  }

  private ImmutableMap<String, String> generateHeaders(
      String paramsString, String bodyString, ICredential credential) {
    if (credential.isAnonymous()) {
      return ImmutableMap.of();
    }
    String ts = FORMATTER.format(clock.instant());
    String payload = ts + getMethod() + getEndpointPath() + paramsString + bodyString;
    String signature = credential.sign(payload);

    ImmutableMap.Builder<String, String> result =
        ImmutableMap.<String, String>builder()
            .put("OK-ACCESS-KEY", credential.getApiKeyId())
            .put("OK-ACCESS-SIGN", signature)
            .put("OK-ACCESS-TIMESTAMP", ts);
    credential.getProperties().forEach(result::put);

    return result.build();
  }

  private String buildParamsString() {
    RestParams params = getParams();
    if (params.isEmpty()) {
      return "";
    }
    return "?" + params.getQueryString(UrlEscapers.urlPathSegmentEscaper());
  }

  private String buildUrlString(String paramsString) {
    return context.getBaseUrl() + getEndpointPath() + paramsString;
  }
}
