package io.contek.invoker.commons.actor.http;

import okhttp3.*;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import org.slf4j.Logger;

import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public final class HttpLoggingInterceptor implements Interceptor {

  private static final Logger log = getLogger(HttpLoggingInterceptor.class);

  private HttpLoggingInterceptor() {}

  public static HttpLoggingInterceptor getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private static void logRequest(Request request) {
    RequestBody body = request.body();
    if (body == null) {
      log.info("Sending {} request to {}.", request.method(), request.url());
    } else {
      String bodyString = readString(body);
      log.info(
          "Sending {} request to {} with payload {}.", request.method(), request.url(), bodyString);
    }
  }

  private static void logResponse(Request request, Response response) {
    ResponseBody body = response.body();
    if (body == null) {
      log.info("Received {} response from {}.", request.method(), request.url());
    } else {
      String bodyString = readString(body);
      log.info(
          "Received {} response from {} with payload {}.",
          request.method(),
          request.url(),
          bodyString);
    }
  }

  private static String readString(RequestBody body) {
    try {
      Buffer buffer = new Buffer();
      body.writeTo(buffer);
      return buffer.readUtf8();
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }

  private static String readString(ResponseBody body) {
    try {
      BufferedSource source = body.source();
      source.request(Integer.MAX_VALUE);
      ByteString bytes = source.getBuffer().snapshot();
      return bytes.utf8();
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();
    logRequest(request);

    Response response = chain.proceed(request);
    logResponse(request, response);
    return response;
  }

  private static final class InstanceHolder {

    private static final HttpLoggingInterceptor INSTANCE = new HttpLoggingInterceptor();
  }
}
