package io.contek.invoker.commons.actor.http;

import com.google.common.base.Joiner;
import okhttp3.*;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import org.slf4j.Logger;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

@ThreadSafe
public final class HttpLoggingInterceptor implements Interceptor {

  private static final Logger log = getLogger(HttpLoggingInterceptor.class);

  private static final Joiner HEADER_JOINER = Joiner.on(',');

  private final boolean logHeader;
  private final boolean logPayload;
  private final boolean logTimestamps;

  private final AtomicInteger count = new AtomicInteger(0);

  private HttpLoggingInterceptor(boolean logHeader, boolean logPayload, boolean logTimestamps) {
    this.logHeader = logHeader;
    this.logPayload = logPayload;
    this.logTimestamps = logTimestamps;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    int id = count.incrementAndGet();

    Request request = chain.request();
    logRequest(request, id);
    Response response = chain.proceed(request);
    logResponse(response, id);

    if (logTimestamps) {
      log.info(
          "Request #{} life cycle started at {}ms and completed at {}ms.",
          id,
          response.sentRequestAtMillis(),
          response.receivedResponseAtMillis());
    }
    return response;
  }

  private void logRequest(Request request, int id) throws IOException {
    RequestBody body = request.body();
    if (body == null || !logPayload) {
      if (logHeader) {
        log.info(
            "Sending {} request #{} to {} with headers {}.",
            request.method(),
            id,
            request.url(),
            HEADER_JOINER.join(request.headers()));
      } else {
        log.info("Sending {} request #{} to {}.", request.method(), id, request.url());
      }
    } else {
      String bodyString = readString(body);
      if (logHeader) {
        log.info(
            "Sending {} request #{} to {} with headers {} and payload {}.",
            request.method(),
            id,
            request.url(),
            HEADER_JOINER.join(request.headers()),
            bodyString);
      } else {
        log.info(
            "Sending {} request #{} to {} with payload {}.",
            request.method(),
            id,
            request.url(),
            bodyString);
      }
    }
  }

  private void logResponse(Response response, int id) throws IOException {
    ResponseBody body = response.body();
    if (body == null || !logPayload) {
      if (logHeader) {
        log.info(
            "Received response #{} with headers {}.", id, HEADER_JOINER.join(response.headers()));
      } else {
        log.info("Received response #{}.", id);
      }
    } else {
      String bodyString = readString(body);
      if (logHeader) {
        log.info(
            "Received response #{} with headers {} and payload {}.",
            id,
            HEADER_JOINER.join(response.headers()),
            bodyString);
      } else {
        log.info("Received response #{} with payload {}.", id, bodyString);
      }
    }
  }

  private static String readString(RequestBody body) throws IOException {
    Buffer buffer = new Buffer();
    body.writeTo(buffer);
    return buffer.readUtf8();
  }

  private static String readString(ResponseBody body) throws IOException {
    BufferedSource source = body.source();
    source.request(Integer.MAX_VALUE);
    ByteString bytes = source.getBuffer().snapshot();
    return bytes.utf8();
  }

  @NotThreadSafe
  public static final class Builder {

    private boolean logHeader;
    private boolean logPayload;
    private boolean logTimestamps;

    public Builder setLogHeader(boolean logHeader) {
      this.logHeader = logHeader;
      return this;
    }

    public Builder setLogPayload(boolean logPayload) {
      this.logPayload = logPayload;
      return this;
    }

    public Builder setLogTimestamps(boolean logTimestamps) {
      this.logTimestamps = logTimestamps;
      return this;
    }

    public HttpLoggingInterceptor build() {
      return new HttpLoggingInterceptor(logHeader, logPayload, logTimestamps);
    }
  }
}
