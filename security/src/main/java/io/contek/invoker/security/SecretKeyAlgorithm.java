package io.contek.invoker.security;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import static com.google.common.base.Charsets.UTF_8;

public enum SecretKeyAlgorithm {
  HMAC_MD5("HmacMD5"),
  HMAC_SHA256("HmacSHA256"),
  HMAC_SHA384("HmacSHA384"),
  HMAC_SHA512("HmacSHA512"),
  ;

  private final String algorithmName;

  SecretKeyAlgorithm(String algorithmName) {
    this.algorithmName = algorithmName;
  }

  public String getAlgorithmName() {
    return algorithmName;
  }

  public Mac setupMac(String secret) {
    return setupMac(secret.getBytes(UTF_8));
  }

  public Mac setupMac(byte[] secret) {
    try {
      Mac mac = Mac.getInstance(algorithmName);
      Key spec = new SecretKeySpec(secret, algorithmName);
      mac.init(spec);
      return mac;
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException(e);
    } catch (InvalidKeyException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
