package io.contek.invoker.bybit.api.rest;

import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.actor.security.ICredential;
import io.contek.invoker.commons.api.rest.*;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Clock;
import java.util.Map;
import java.util.TreeMap;

import static io.contek.invoker.commons.api.rest.RestMediaType.JSON;
import static io.contek.invoker.commons.api.rest.RestParams.toQueryString;

@ThreadSafe
public abstract class RestRequest<R> extends BaseRestRequest<R> {

  private static final String API_KEY = "api_key";
  private static final String TIMESTAMP = "timestamp";
  private static final String SIGN = "sign";

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
      case GET:
      case DELETE:
        return RestCall.newBuilder()
            .setUrl(buildUrlWithParams(credential))
            .setMethod(method)
            .build();
      case POST:
      case PUT:
        return RestCall.newBuilder()
            .setUrl(buildUrlWithoutParams())
            .setMethod(method)
            .setBody(buildBody(credential))
            .build();
      default:
        throw new IllegalStateException(getMethod().name());
    }
  }

  private String buildUrlWithParams(ICredential credential) {
    String url = buildUrlWithoutParams();
    RestParams params = getParams();
    if (!credential.isAnonymous()) {
      params = addSignature(params, credential);
    }
    if (!params.isEmpty()) {
      url += "?" + params.getQueryString();
    }
    return url;
  }

  private String buildUrlWithoutParams() {
    return context.getBaseUrl() + getEndpointPath();
  }

  private RestMediaBody buildBody(ICredential credential) {
    RestParams params = getParams();
    if (!credential.isAnonymous()) {
      params = addSignature(params, credential);
    }
    return JSON.createBody(params);
  }

  private RestParams addSignature(RestParams params, ICredential credential) {
    Map<String, String> paramMap = new TreeMap<>(params.getValues());
    paramMap.put(API_KEY, credential.getApiKeyId());
    paramMap.put(TIMESTAMP, Long.toString(clock.millis()));

    String queryString = toQueryString(paramMap);
    String sign = credential.sign(queryString);
    return RestParams.newBuilder().addAll(paramMap).add(SIGN, sign).build();
  }
}
