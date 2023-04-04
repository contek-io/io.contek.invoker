package io.contek.invoker.hbdminverse.api.rest;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.http.AnyHttpException;
import io.contek.invoker.commons.actor.http.ParsedHttpException;
import io.contek.invoker.commons.actor.ratelimit.TypedPermitRequest;
import io.contek.invoker.commons.rest.*;
import io.contek.invoker.hbdminverse.api.rest.common.ResponseWrapper;
import io.contek.invoker.security.ICredential;

import javax.annotation.concurrent.NotThreadSafe;
import java.net.URI;
import java.time.Clock;
import java.time.format.DateTimeFormatter;

import static com.google.common.net.UrlEscapers.urlFormParameterEscaper;
import static io.contek.invoker.commons.rest.RestMediaType.JSON;
import static java.time.ZoneOffset.UTC;
import static java.time.temporal.ChronoField.MILLI_OF_SECOND;

@NotThreadSafe
public abstract class RestRequest<R extends ResponseWrapper> extends BaseRestRequest<R> {

  private static final DateTimeFormatter FORMATTER =
      DateTimeFormatter.ISO_LOCAL_DATE_TIME.withZone(UTC);

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

  protected abstract ImmutableList<TypedPermitRequest> getRequiredQuotas();

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

  @Override
  protected final void checkResult(R result, RestResponse response) throws AnyHttpException {
    if (result.err_code == null) {
      return;
    }

    throw new ParsedHttpException(response.getCode(), result, result.err_msg);
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
    return "?" + params.getQueryString(urlFormParameterEscaper());
  }

  private String generateSignedParamString(
      RestMethod method, String path, RestParams params, ICredential credential) {
    RestParams.Builder builder = params.toBuilder();
    builder.add("AccessKeyId", credential.getApiKeyId());
    builder.add("SignatureMethod", credential.getAlgorithm().getAlgorithmName());
    builder.add("SignatureVersion", 2);
    builder.add("Timestamp", FORMATTER.format(clock.instant().with(MILLI_OF_SECOND, 0)));
    RestParams withIdentity = builder.build(true);
    String queryString = withIdentity.getQueryString(urlFormParameterEscaper());
    String payload =
        String.join(
            "\n", method.name(), URI.create(context.getBaseUrl()).getHost(), path, queryString);
    String sign = credential.sign(payload);
    return "?" + queryString + "&Signature=" + urlFormParameterEscaper().escape(sign);
  }
}
