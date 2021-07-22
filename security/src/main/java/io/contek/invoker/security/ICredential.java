package io.contek.invoker.security;

import com.google.common.collect.ImmutableMap;

import javax.annotation.concurrent.Immutable;

@Immutable
public interface ICredential {

  static Anonymous anonymous() {
    return Anonymous.INSTANCE;
  }

  boolean isAnonymous();

  String getApiKeyId();

  ImmutableMap<String, String> getProperties();

  String sign(String payload);

  default String getSubAccount() {
    return this.getProperties().get("subAccount");
  }

  @Immutable
  final class Anonymous implements ICredential {

    private static final Anonymous INSTANCE = new Anonymous();

    private Anonymous() {}

    @Override
    public boolean isAnonymous() {
      return true;
    }

    @Override
    public String getApiKeyId() {
      throw new UnsupportedOperationException();
    }

    @Override
    public ImmutableMap<String, String> getProperties() {
      throw new UnsupportedOperationException();
    }

    @Override
    public String sign(String payload) {
      throw new UnsupportedOperationException();
    }
  }
}
