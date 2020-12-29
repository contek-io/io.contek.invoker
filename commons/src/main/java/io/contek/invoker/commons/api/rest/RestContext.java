package io.contek.invoker.commons.api.rest;

import io.contek.invoker.commons.api.actor.http.BaseHttpContext;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import java.time.Duration;

@Immutable
public final class RestContext extends BaseHttpContext {

  private final Duration connectionTimeout;
  private final Duration readTimeout;
  private final Duration writeTimeout;

  private RestContext(
      String baseUrl,
      @Nullable Duration connectionTimeout,
      @Nullable Duration readTimeout,
      @Nullable Duration writeTimeout) {
    super(baseUrl);
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
    private Duration connectionTimeout;
    private Duration readTimeout;
    private Duration writeTimeout;

    public Builder setBaseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
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
      return new RestContext(baseUrl, connectionTimeout, readTimeout, writeTimeout);
    }

    private Builder() {}
  }
}
