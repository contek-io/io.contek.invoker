package io.contek.invoker.commons.api.actor.security;

import com.google.common.io.BaseEncoding;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;

@Immutable
public final class SimpleCredentialFactory extends BaseCredentialFactory {

  private SimpleCredentialFactory(SecretKeyAlgorithm algorithm, BaseEncoding encoding) {
    super(algorithm, encoding);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  @NotThreadSafe
  public static final class Builder {

    private SecretKeyAlgorithm algorithm;
    private BaseEncoding encoding;

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

    private Builder() {}
  }
}
