package io.contek.invoker.commons.rest;

import io.contek.invoker.commons.actor.http.BaseHttpContext;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import java.time.Duration;

@Immutable
public final class RestContext extends BaseHttpContext {

  private final boolean logHeaders;
  private final boolean logPayload;
  private final boolean logTimestamps;
  private final Duration connectionTimeout;
  private final Duration readTimeout;
  private final Duration writeTimeout;

  private RestContext(
      String baseUrl,
      boolean logHeaders,
      boolean logPayload,
      boolean logTimestamps,
      @Nullable Duration connectionTimeout,
      @Nullable Duration readTimeout,
      @Nullable Duration writeTimeout) {
    super(baseUrl);
    this.logHeaders = logHeaders;
    this.logPayload = logPayload;
    this.logTimestamps = logTimestamps;
    this.connectionTimeout = connectionTimeout;
    this.readTimeout = readTimeout;
    this.writeTimeout = writeTimeout;
  }

  public static RestContext forBaseUrl(String baseUrl) {
    return RestContext.newBuilder().setBaseUrl(baseUrl).build();
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  @Override
  public boolean getLogHeaders() {
    return logHeaders;
  }

  @Override
  public boolean getLogPayload() {
    return logPayload;
  }

  @Override
  public boolean getLogTimestamps() {
    return logTimestamps;
  }

  @Nullable
  @Override
  public Duration getConnectionTimeout() {
    return connectionTimeout;
  }

  @Nullable
  @Override
  public Duration getReadTimeout() {
    return readTimeout;
  }

  @Nullable
  @Override
  public Duration getWriteTimeout() {
    return writeTimeout;
  }

  @NotThreadSafe
  public static final class Builder {

    private String baseUrl;
    private boolean logHeaders = false;
    private boolean logPayload = true;
    private boolean logTimestamps = false;
    private Duration connectionTimeout;
    private Duration readTimeout;
    private Duration writeTimeout;

    public Builder setBaseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
      return this;
    }

    public Builder setLogHeaders(boolean logHeaders) {
      this.logHeaders = logHeaders;
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

    public Builder setConnectionTimeout(@Nullable Duration connectionTimeout) {
      this.connectionTimeout = connectionTimeout;
      return this;
    }

    public Builder setReadTimeout(@Nullable Duration readTimeout) {
      this.readTimeout = readTimeout;
      return this;
    }

    public Builder setWriteTimeout(@Nullable Duration writeTimeout) {
      this.writeTimeout = writeTimeout;
      return this;
    }

    public RestContext build() {
      if (baseUrl == null) {
        throw new IllegalArgumentException("No base URL specified");
      }
      return new RestContext(
          baseUrl,
          logHeaders,
          logPayload,
          logTimestamps,
          connectionTimeout,
          readTimeout,
          writeTimeout);
    }

    private Builder() {}
  }
}
