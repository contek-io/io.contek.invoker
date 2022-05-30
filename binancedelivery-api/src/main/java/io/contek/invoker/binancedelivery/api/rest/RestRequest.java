package io.contek.invoker.binancedelivery.api.rest;

import com.google.common.collect.ImmutableMap;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.rest.*;
import io.contek.invoker.security.ICredential;
import io.vertx.core.buffer.Buffer;

import static io.contek.invoker.commons.rest.RestMediaType.FORM;

public abstract class RestRequest<R> extends BaseRestRequest<R> {

  private static final String X_MBX_API_KEY = "X-MBX-APIKEY";
  private static final String SIGNATURE = "signature";

  private final RestContext context;

  protected RestRequest(IActor actor, RestContext context) {
    super(actor);
    this.context = context;
  }

  private static RestParams addSignature(RestParams params, Buffer buffer, ICredential credential) {
    String payload = params.getQueryString() + buffer.toString();
    String signature = credential.sign(payload);
    return RestParams.newBuilder().addAll(params.values()).add(SIGNATURE, signature).build();
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
        RestMediaBody body = FORM.create(getParams());
        return RestCall.newBuilder()
            .setUrl(buildUrlWithoutParams(body.body(), credential))
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
    String url = context.baseUrl() + getEndpointPath();
    String params = buildUrlParamsString(credential);
    if (!params.isEmpty()) {
      url += "?" + params;
    }
    return url;
  }

  private String buildUrlParamsString(ICredential credential) {
    RestParams params = getParams();
    if (credential.isAnonymous()) {
      return params.getQueryString();
    }
    return addSignature(params, Buffer.buffer(""), credential).getQueryString();
  }

  private String buildUrlWithoutParams(Buffer body, ICredential credential) {
    String url = context.baseUrl() + getEndpointPath();
    if (credential.isAnonymous()) {
      return url;
    }
    return url + "?" + addSignature(RestParams.empty(), body, credential).getQueryString();
  }
}
