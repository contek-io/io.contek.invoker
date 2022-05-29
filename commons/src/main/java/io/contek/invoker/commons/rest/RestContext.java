package io.contek.invoker.commons.rest;

import io.contek.invoker.commons.actor.http.BaseHttpContext;

import java.time.Duration;

public final class RestContext extends BaseHttpContext {

  private final Duration connectionTimeout;
  private final Duration readTimeout;
  private final Duration writeTimeout;

  private RestContext(
      String baseUrl, Duration connectionTimeout, Duration readTimeout, Duration writeTimeout) {
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

  @Override
  public Duration getConnectionTimeout() {
    return connectionTimeout;
  }

  @Override
  public Duration getReadTimeout() {
    return readTimeout;
  }

  @Override
  public Duration getWriteTimeout() {
    return writeTimeout;
  }

  public static final class Builder {

    private String baseUrl;
    private Duration connectionTimeout;
    private Duration readTimeout;
    private Duration writeTimeout;

    private Builder() {}

    public Builder setBaseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
      return this;
    }

    public Builder setConnectionTimeout(Duration connectionTimeout) {
      this.connectionTimeout = connectionTimeout;
      return this;
    }

    public Builder setReadTimeout(Duration readTimeout) {
      this.readTimeout = readTimeout;
      return this;
    }

    public Builder setWriteTimeout(Duration writeTimeout) {
      this.writeTimeout = writeTimeout;
      return this;
    }

    public RestContext build() {
      if (baseUrl == null) {
        throw new IllegalArgumentException("No base URL specified");
      }
      return new RestContext(baseUrl, connectionTimeout, readTimeout, writeTimeout);
    }
  }
}
