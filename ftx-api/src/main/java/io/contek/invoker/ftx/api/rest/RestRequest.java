package io.contek.invoker.ftx.api.rest;

import com.google.common.collect.ImmutableMap;
import com.google.common.net.UrlEscapers;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.*;
import io.contek.invoker.security.ICredential;
import io.vertx.core.buffer.Buffer;

import java.time.Clock;

import static io.contek.invoker.commons.rest.RestMediaType.JSON;

public abstract class RestRequest<R> extends BaseRestRequest<R> {

  public static final String FTX_SUBACCOUNT_KEY = "FTX-SUBACCOUNT";

  private final RestContext context;
  private final Clock clock;

  protected RestRequest(IActor actor, RestContext context) {
    super(actor);
    this.context = context;
    clock = actor.clock();
  }

  protected abstract RestMethod getMethod();

  protected abstract String getEndpointPath();

  protected abstract RestParams getParams();

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
            .setHeaders(generateHeaders(paramsString, Buffer.buffer(""), credential))
            .build();
      case POST:
      case PUT:
        RestMediaBody mediaBody = JSON.create(getParams());
        return RestCall.newBuilder()
            .setUrl(buildUrlString(""))
            .setMethod(method)
            .setHeaders(generateHeaders("", mediaBody.body(), credential))
            .setBody(mediaBody)
            .build();
      default:
        throw new IllegalStateException(getMethod().name());
    }
  }

  private ImmutableMap<String, String> generateHeaders(
      String paramsString, Buffer body, ICredential credential) {
    if (credential.isAnonymous()) {
      return ImmutableMap.of();
    }
    String ts = Long.toString(clock.millis());
    String payload = ts + getMethod() + getEndpointPath() + paramsString + body.toString();
    String signature = credential.sign(payload);

    ImmutableMap.Builder<String, String> result =
        ImmutableMap.<String, String>builder()
            .put("FTX-KEY", credential.getApiKeyId())
            .put("FTX-SIGN", signature)
            .put("FTX-TS", ts);
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
    return context.baseUrl() + getEndpointPath() + paramsString;
  }
}
