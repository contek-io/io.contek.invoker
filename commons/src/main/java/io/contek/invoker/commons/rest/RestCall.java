package io.contek.invoker.commons.rest;

import com.google.common.collect.ImmutableMap;
import io.contek.invoker.commons.actor.http.HttpConnectionException;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

import java.util.Map;

public record RestCall(RestMethod method, String url, ImmutableMap<String, String> headers, RestMediaBody mediaBody) {

  public static Builder newBuilder() {
    return new Builder();
  }

  HttpRequest<Buffer> createHttpRequestWithoutBody(WebClient webClient) {
    final HttpRequest<Buffer> request = webClient.requestAbs(method.getVertxHttpMethod(), url);
    request.headers().addAll(headers);
    return request;
  }

  Future<HttpResponse<Buffer>> sendRequestWithoutBody(WebClient webClient) {
    return createHttpRequestWithoutBody(webClient)
      .send();
  }

  Future<HttpResponse<Buffer>> sendRequestWithBody(WebClient webClient) {
    return createHttpRequestWithoutBody(webClient)
      .putHeader("Content-Type", mediaBody.type().toString())
      .sendBuffer(mediaBody.body());
  }

  Future<HttpResponse<Buffer>> submit(WebClient webClient) throws RestErrorException, HttpConnectionException {
    return switch (method) {
      case GET -> {
        if (mediaBody != null) {
          throw new IllegalArgumentException("mediaBody must to be null");
        }
        yield sendRequestWithoutBody(webClient);
      }
      case POST, PUT -> {
        if (mediaBody == null) {
          throw new IllegalArgumentException("mediaBody must not to be null");
        }
        yield sendRequestWithBody(webClient);
      }
      case DELETE -> {
        if (mediaBody == null) {
          yield sendRequestWithoutBody(webClient);
        } else {
          yield sendRequestWithBody(webClient);
        }
      }
    };
  }

  public static final class Builder {

    private RestMethod method;
    private String url;
    private Map<String, String> headers;
    private RestMediaBody body;

    private Builder() {
    }

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
