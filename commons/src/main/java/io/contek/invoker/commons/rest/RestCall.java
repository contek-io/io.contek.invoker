package io.contek.invoker.commons.rest;

import com.google.common.collect.ImmutableMap;
import io.contek.invoker.commons.actor.http.HttpConnectionException;
import io.contek.invoker.commons.actor.http.IHttpClient;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.Map;

public final class RestCall {

  private final RestMethod method;
  private final String url;
  private final ImmutableMap<String, String> headers;
  private final RestMediaBody body;

  private RestCall(
      RestMethod method, String url, ImmutableMap<String, String> headers, RestMediaBody body) {
    this.method = method;
    this.url = url;
    this.headers = headers;
    this.body = body;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  RestResponse submit(IHttpClient client) throws RestErrorException, HttpConnectionException {
    Request request = createRequest();
    Response response = client.submit(request);

    try {
      ResponseBody body = response.body();
      String bodyString = body == null ? null : body.string();
      RestResponse result = new RestResponse(response.code(), bodyString);
      if (response.isSuccessful()) {
        return result;
      } else {
        throw new RestErrorException(result);
      }
    } catch (IOException e) {
      throw new HttpConnectionException(e);
    }
  }

  private Request createRequest() {
    final Headers h = Headers.of(headers);
    return method.createRequest(
        url, h, body == null ? null : body.createRequestBody());
  }

  public static final class Builder {

    private RestMethod method;
    private String url;
    private Map<String, String> headers;
    private RestMediaBody body;

    private Builder() {}

    public Builder setMethod(RestMethod method) {
      this.method = method;
      return this;
    }

    public Builder setUrl(String url) {
      this.url = url;
      return this;
    }

    public Builder setHeaders(Map<String, String> headers) {
      this.headers = headers;
      return this;
    }

    public Builder setBody(RestMediaBody body) {
      this.body = body;
      return this;
    }

    public RestCall build() {
      return new RestCall(
          method, url, headers == null ? ImmutableMap.of() : ImmutableMap.copyOf(headers), body);
    }
  }
}
