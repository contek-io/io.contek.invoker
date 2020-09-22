package io.contek.invoker.commons.api.actor.security;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class ApiKey {

  private final String id;
  private final String secret;

  public ApiKey(String id, String secret) {
    this.id = id;
    this.secret = secret;
  }

  public String getId() {
    return id;
  }

  public String getSecret() {
    return secret;
  }
}
