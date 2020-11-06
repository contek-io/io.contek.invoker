package io.contek.invoker.commons.api.actor.security;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;

@Immutable
public final class ApiKey {

  private final String id;
  private final String secret;
  private final String subAccount;

  private ApiKey(String id, String secret, @Nullable String subAccount) {
    this.id = id;
    this.secret = secret;
    this.subAccount = subAccount;
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

  @Nullable
  public String getSubAccount() {
    return subAccount;
  }

  @NotThreadSafe
  public static final class Builder {

    private String id;
    private String secret;
    private String subAccount;

    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    public Builder setSecret(String secret) {
      this.secret = secret;
      return this;
    }

    public Builder setSubAccount(String subAccount) {
      this.subAccount = subAccount;
      return this;
    }

    public ApiKey build() {
      if (id == null) {
        throw new IllegalArgumentException("No API-Key ID specified");
      }
      if (secret == null) {
        throw new IllegalArgumentException("No API-Key secret specified");
      }
      return new ApiKey(id, secret, subAccount);
    }

    private Builder() {}
  }
}
