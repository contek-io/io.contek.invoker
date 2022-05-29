package io.contek.invoker.security;

import com.google.common.io.BaseEncoding;

public abstract class BaseCredentialFactory implements ICredentialFactory {

  private final SecretKeyAlgorithm algorithm;
  private final BaseEncoding encoding;

  protected BaseCredentialFactory(SecretKeyAlgorithm algorithm, BaseEncoding encoding) {
    this.algorithm = algorithm;
    this.encoding = encoding;
  }

  public final ICredential create(ApiKey apiKey) {
    return new SimpleCredential(apiKey, algorithm, encoding);
  }
}
