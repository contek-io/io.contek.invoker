package io.contek.invoker.commons.rest;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.util.function.Function;

@Immutable
public enum RestMethod {
  GET(
      body -> {
        if (body != null) {
          throw new IllegalArgumentException();
        }
        return new Request.Builder().get();
      }),
  POST(
      body -> {
        if (body == null) {
          throw new IllegalArgumentException();
        }
        return new Request.Builder().post(body);
      }),
  PUT(
      body -> {
        if (body == null) {
          throw new IllegalArgumentException();
        }
        return new Request.Builder().put(body);
      }),
  DELETE(body -> new Request.Builder().delete(body));

  private final Function<RequestBody, Request.Builder> builder;

  RestMethod(Function<RequestBody, Request.Builder> builder) {
    this.builder = builder;
  }

  Request createRequest(String url, Headers headers, @Nullable RequestBody body) {
    return builder.apply(body).url(url).headers(headers).build();
  }
}
