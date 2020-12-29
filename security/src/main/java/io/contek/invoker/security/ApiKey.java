package io.contek.invoker.security;

import com.google.common.collect.ImmutableMap;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.HashMap;
import java.util.Map;

@Immutable
public final class ApiKey {

  private final String id;
  private final String secret;
  private final ImmutableMap<String, String> properties;

  private ApiKey(String id, String secret, ImmutableMap<String, String> properties) {
    this.id = id;
    this.secret = secret;
    this.properties = properties;
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

  public ImmutableMap<String, String> getProperties() {
    return properties;
  }

  @NotThreadSafe
  public static final class Builder {

    private String id;
    private String secret;
    private Map<String, String> properties = new HashMap<>();

    public Builder setId(String id) {
      this.id = id;
      return this;
    }

    public Builder setSecret(String secret) {
      this.secret = secret;
      return this;
    }

    public Builder setProperties(Map<String, String> properties) {
      this.properties = properties;
      return this;
    }

    public Builder addProperty(String key, String value) {
      properties.put(key, value);
      return this;
    }

    public ApiKey build() {
      if (id == null) {
        throw new IllegalArgumentException("No API-Key ID specified");
      }
      if (secret == null) {
        throw new IllegalArgumentException("No API-Key secret specified");
      }
      return new ApiKey(id, secret, ImmutableMap.copyOf(properties));
    }

    private Builder() {}
  }
}
