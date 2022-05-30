package io.contek.ursa.cache;

import java.time.Duration;

public class PermitRequest {
  private final String ruleName;

  private final String key;

  private final int permits;

  private final Duration timeout;

  private PermitRequest(String ruleName, String key, int permits, Duration timeout) {
    this.ruleName = ruleName;
    this.key = key;
    this.permits = permits;
    this.timeout = timeout;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  String getRuleName() {
    return this.ruleName;
  }

  String getKey() {
    return this.key;
  }

  int getPermits() {
    return this.permits;
  }

  Duration getTimeout() {
    return this.timeout;
  }

  public static final class Builder {
    private String name;

    private String key;

    private Integer permits;

    private Duration timeout;

    private Builder() {}

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setKey(String key) {
      this.key = key;
      return this;
    }

    public Builder setPermits(Integer permits) {
      this.permits = permits;
      return this;
    }

    public Builder setTimeout(Duration timeout) {
      this.timeout = timeout;
      return this;
    }

    public PermitRequest build() throws InvalidPermitRequestException {
      if (this.name == null) throw new InvalidPermitRequestException("name");
      if (this.key == null) throw new InvalidPermitRequestException("key");
      if (this.permits == null) throw new InvalidPermitRequestException("permits");
      return new PermitRequest(this.name, this.key, this.permits, this.timeout);
    }
  }
}
