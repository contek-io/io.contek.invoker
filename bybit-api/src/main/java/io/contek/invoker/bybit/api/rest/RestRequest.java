package io.contek.invoker.bybit.api.rest;

import io.contek.invoker.bybit.api.rest.common.ResponseWrapper;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.http.AnyHttpException;
import io.contek.invoker.commons.actor.http.ParsedHttpException;
import io.contek.invoker.commons.rest.*;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.ThreadSafe;
import java.time.Clock;

import static io.contek.invoker.commons.rest.RestMediaType.JSON;

@ThreadSafe
public abstract class RestRequest<R extends ResponseWrapper<?>> extends BaseRestRequest<R> {

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
    return switch (method) {
      case GET, DELETE -> RestCall.newBuilder()
          .setUrl(buildUrlWithParams(credential))
          .setMethod(method)
          .build();
      case POST, PUT -> RestCall.newBuilder()
          .setUrl(buildUrlWithoutParams())
          .setMethod(method)
          .setBody(buildBody(credential))
          .build();
    };
  }

  @Override
  protected final void checkResult(R result, RestResponse response) throws AnyHttpException {
    if (result.ret_code == 0) {
      return;
    }

    throw new ParsedHttpException(response.getCode(), result, result.ret_msg);
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
    RestParams.Builder builder = params.toBuilder();
    builder.add(API_KEY, credential.getApiKeyId());
    builder.add(TIMESTAMP, Long.toString(clock.millis()));
    RestParams withIdentity = builder.build(true);

    String payload = withIdentity.getQueryString();
    String sign = credential.sign(payload);
    return withIdentity.toBuilder().add(SIGN, sign).build();
  }
}
