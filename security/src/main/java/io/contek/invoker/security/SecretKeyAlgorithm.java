package io.contek.invoker.security;

import javax.annotation.concurrent.Immutable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import static com.google.common.base.Charsets.UTF_8;

@Immutable
public enum SecretKeyAlgorithm {
  HMAC_MD5("HmacMD5"),
  HMAC_SHA256("HmacSHA256"),
  HMAC_SHA384("HmacSHA384"),
  HMAC_SHA512("HmacSHA512"),
  ;

  private final String value;

  SecretKeyAlgorithm(String value) {
    this.value = value;
  }

  public Mac setupMac(String secret) {
    return setupMac(secret.getBytes(UTF_8));
  }

  public Mac setupMac(byte[] secret) {
    try {
      Mac mac = Mac.getInstance(value);
      Key spec = new SecretKeySpec(secret, value);
      mac.init(spec);
      return mac;
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException(e);
    } catch (InvalidKeyException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
