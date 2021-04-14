package io.contek.invoker.security;

import com.google.common.collect.ImmutableMap;
import com.google.common.io.BaseEncoding;

import javax.annotation.concurrent.Immutable;

import static com.google.common.base.Charsets.UTF_8;

@Immutable
public final class SimpleCredential implements ICredential {

  private final ApiKey apiKey;
  private final SecretKeyAlgorithm algorithm;
  private final BaseEncoding encoding;

  protected SimpleCredential(ApiKey apiKey, SecretKeyAlgorithm algorithm, BaseEncoding encoding) {
    this.apiKey = apiKey;
    this.algorithm = algorithm;
    this.encoding = encoding;
  }

  @Override
  public final boolean isAnonymous() {
    return false;
  }

  @Override
  public final String getApiKeyId() {
    return apiKey.getId();
  }

  @Override
  public SecretKeyAlgorithm getAlgorithm() {
    return algorithm;
  }

  @Override
  public ImmutableMap<String, String> getProperties() {
    return apiKey.getProperties();
  }

  @Override
  public final String sign(String payload) {
    return encoding.encode(algorithm.setupMac(apiKey.getSecret()).doFinal(payload.getBytes(UTF_8)));
  }
}
