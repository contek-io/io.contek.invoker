package io.contek.invoker.deribit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _AuthResult {

  public String access_token;
  public Long expires_in;
  public String refresh_token;
  public String scope;
  public String token_type;
}
