package io.contek.invoker.security;

import com.google.common.io.BaseEncoding;

public final class SimpleCredentialFactory extends BaseCredentialFactory {

  private SimpleCredentialFactory(SecretKeyAlgorithm algorithm, BaseEncoding encoding) {
    super(algorithm, encoding);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static final class Builder {

    private SecretKeyAlgorithm algorithm;
    private BaseEncoding encoding;

    private Builder() {}

    public Builder setAlgorithm(SecretKeyAlgorithm algorithm) {
      this.algorithm = algorithm;
      return this;
    }

    public Builder setEncoding(BaseEncoding encoding) {
      this.encoding = encoding;
      return this;
    }

    public SimpleCredentialFactory build() {
      if (algorithm == null) {
        throw new IllegalArgumentException("No algorithm specified");
      }
      if (encoding == null) {
        throw new IllegalArgumentException("No binary encoding scheme specified");
      }
      return new SimpleCredentialFactory(algorithm, encoding);
    }
  }
}
