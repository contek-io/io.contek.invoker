package io.contek.invoker.binancefutures.api.rest;

import com.google.common.collect.ImmutableMap;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.actor.security.ICredential;
import io.contek.invoker.commons.api.rest.*;

import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.api.rest.RestMediaType.FORM;

@ThreadSafe
public abstract class RestRequest<R> extends BaseRestRequest<R> {

  private static final String X_MBX_API_KEY = "X-MBX-APIKEY";
  private static final String SIGNATURE = "signature";

  private final RestContext context;

  protected RestRequest(IActor actor, RestContext context) {
    super(actor);
    this.context = context;
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
            .setHeaders(buildHeaders(credential))
            .build();
      case POST:
      case PUT:
        RestMediaBody body = FORM.createBody(getParams());
        return RestCall.newBuilder()
            .setUrl(buildUrlWithoutParams(body.getStringValue(), credential))
            .setMethod(method)
            .setHeaders(buildHeaders(credential))
            .setBody(body)
            .build();
      default:
        throw new IllegalStateException(getMethod().name());
    }
  }

  private ImmutableMap<String, String> buildHeaders(ICredential credential) {
    if (credential.isAnonymous()) {
      return ImmutableMap.of();
    }
    return ImmutableMap.<String, String>builder()
        .put(X_MBX_API_KEY, credential.getApiKeyId())
        .build();
  }

  private String buildUrlWithParams(ICredential credential) {
    String url = context.getBaseUrl() + getEndpointPath();
    String params = buildUrlParamsString(credential);
    if (!params.isEmpty()) {
      url += "?" + params;
    }
    return url;
  }

  private String buildUrlParamsString(ICredential credential) {
    RestParams params = getParams();
    if (credential.isAnonymous()) {
      return params.getUrlEncodedString();
    }
    return addSignature(params, "", credential).getUrlEncodedString();
  }

  private String buildUrlWithoutParams(String bodyString, ICredential credential) {
    String url = context.getBaseUrl() + getEndpointPath();
    if (credential.isAnonymous()) {
      return url;
    }
    return url
        + "?"
        + addSignature(RestParams.empty(), bodyString, credential).getUrlEncodedString();
  }

  private RestParams addSignature(RestParams params, String bodyString, ICredential credential) {
    String payload = params.getUrlEncodedString() + bodyString;
    String signature = credential.sign(payload);
    return RestParams.newBuilder().addAll(params.getValues()).add(SIGNATURE, signature).build();
  }
}
