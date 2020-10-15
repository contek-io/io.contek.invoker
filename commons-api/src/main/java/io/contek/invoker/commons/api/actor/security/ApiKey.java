package io.contek.invoker.commons.api.actor.security;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;

@Immutable
public final class ApiKey {

  private final String id;
  private final String secret;

  private ApiKey(String id, String secret) {
    this.id = id;
    this.secret = secret;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public String getId() {
    return id;
  }

  public String getSecret() {
    return secret;
  }

  @NotThreadSafe
  public static final class Builder {

    private String id;
    private String secret;

    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    public Builder setSecret(String secret) {
      this.secret = secret;
      return this;
    }

    public ApiKey build() {
      if (id == null) {
        throw new IllegalArgumentException("No API-Key ID specified");
      }
      if (secret == null) {
        throw new IllegalArgumentException("No API-Key secret specified");
      }
      return new ApiKey(id, secret);
    }

    private Builder() {}
  }
}
